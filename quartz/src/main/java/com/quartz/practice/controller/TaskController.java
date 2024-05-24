package com.quartz.practice.controller;

import com.quartz.practice.entity.Task;
import com.quartz.practice.entity.TaskForm;
import com.quartz.practice.repository.TaskRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/list")
    public String listTasks(Model model) {
    	Map<String, String> statusMapping = new HashMap<>();
    	statusMapping.put("NONE", "未配置");
    	statusMapping.put("NORMAL", "正常");
    	statusMapping.put("PAUSED", "暂停");
    	statusMapping.put("COMPLETE", "完成");
    	statusMapping.put("ERROR", "错误");
    	statusMapping.put("BLOCKED", "阻塞");
    	
        try {
        	 List<Task> taskList = new ArrayList<Task>();
        	 taskRepository.findAll().forEach(task->{
        		// 获取触发器状态
        		    TriggerKey triggerKey = new TriggerKey(task.getName() + "-trigger", task.getGroup());
        		    Trigger.TriggerState triggerState;
					try {
						triggerState = scheduler.getTriggerState(triggerKey);
						String englishStatus = triggerState.name(); // 获取英文状态描述
						 // 使用映射将英文状态描述转换为中文描述
	        		    String chineseStatus = statusMapping.get(englishStatus);
	        		    task.setStatus(chineseStatus);
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		   
        		 taskList.add(task);
        	 });
            model.addAttribute("tasks", taskList);
        } catch (Exception e) {
            model.addAttribute("error", "获取任务列表时出现错误");
        }
        return "task-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        return "create-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute @Validated TaskForm taskForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-task";
        }

        try {
        		
            // 动态创建任务类实例
            Class<?> jobClass = Class.forName(taskForm.getClassName());
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) jobClass)
//            		.storeDurably() // 将任务设置为 durable
                    .withIdentity(taskForm.getName(), taskForm.getGroup())
                    .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(taskForm.getName() + "-trigger", taskForm.getGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(taskForm.getCronExpression()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

            // 保存任务信息到数据库
            Task task = new Task();
            task.setName(taskForm.getName());
            task.setGroup(taskForm.getGroup());
            task.setCronExpression(taskForm.getCronExpression());
            task.setClassName(taskForm.getClassName());
            taskRepository.save(task);

            return "redirect:/tasks/list";
        } catch (Exception e) {
            model.addAttribute("error", "创建任务时出现错误");
            return "create-task";
        }
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("taskId") Long taskId, Model model) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            model.addAttribute("error", "任务不存在");
            return "task-list";
        }

        TaskForm taskForm = new TaskForm();
        taskForm.setName(task.getName());
        taskForm.setGroup(task.getGroup());
        taskForm.setCronExpression(task.getCronExpression());
        taskForm.setClassName(task.getClassName());

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("taskId", taskId);

        return "edit-task";
    }

    @PostMapping("/edit")
    public String editTask(HttpServletRequest request, @ModelAttribute @Validated TaskForm taskForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-task";
        }

        try {
        	Long taskId = Long.parseLong(request.getParameter("taskId"));
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task == null) {
                model.addAttribute("error", "任务不存在");
                return "task-list";
            }

            JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(task.getName() + "-trigger", task.getGroup());

            // 更新任务调度信息
            Class<?> jobClass = Class.forName(taskForm.getClassName());
            JobDetail updatedJobDetail = JobBuilder.newJob((Class<? extends Job>) jobClass)
//            		.storeDurably() // 将任务设置为 durable
                    .withIdentity(taskForm.getName(), taskForm.getGroup())
                    .build();
            CronTrigger updatedTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(taskForm.getName() + "-trigger", taskForm.getGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(taskForm.getCronExpression()))
                    .build();

            scheduler.addJob(updatedJobDetail, true);
            scheduler.rescheduleJob(triggerKey, updatedTrigger);

            // 更新数据库中任务信息
            task.setName(taskForm.getName());
            task.setGroup(taskForm.getGroup());
            task.setCronExpression(taskForm.getCronExpression());
            task.setClassName(taskForm.getClassName());
            taskRepository.save(task);

            return "redirect:/tasks/list";
        } catch (Exception e) {
        	e.printStackTrace();
            model.addAttribute("error", "编辑任务时出现错误");
            return "edit-task";
        }
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam("taskId") Long taskId, Model model) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            model.addAttribute("error", "任务不存在");
            return "task-list";
        }

        try {
            JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(task.getName() + "-trigger", task.getGroup());

            scheduler.deleteJob(jobKey);
            taskRepository.delete(task);
        } catch (SchedulerException e) {
            model.addAttribute("error", "删除任务时出现错误");
        }

        return "redirect:/tasks/list";
    }
    
    @PostMapping("/start")
    public String startTask(@RequestParam("taskId") Long taskId) {
        try {
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task != null) {
                JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
                scheduler.triggerJob(jobKey);
                scheduler.start();
            }
        } catch (SchedulerException e) {
        	e.printStackTrace();
            // 处理异常
        }
        return "redirect:/tasks/list";
    }
    @PostMapping("/startOnce")
    public String startOnceTask(@RequestParam("taskId") Long taskId) {
    	try {
    		Task task = taskRepository.findById(taskId).orElse(null);
    		if (task != null) {
    			JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
    			scheduler.triggerJob(jobKey);
    		}
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    		// 处理异常
    	}
    	return "redirect:/tasks/list";
    }

    @PostMapping("/stop")
    public String stopTask(@RequestParam("taskId") Long taskId) {
        try {
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task != null) {
                JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail != null) {
                    // 暂停任务的执行
                	scheduler.pauseJob(jobKey);
                }
            }
        } catch (SchedulerException e) {
        	e.printStackTrace();
            // 处理异常
        }
        return "redirect:/tasks/list";
    }
    
    @PostMapping("/resume")
    public String resumeTask(@RequestParam("taskId") Long taskId) {
    	try {
    		Task task = taskRepository.findById(taskId).orElse(null);
    		if (task != null) {
    			JobKey jobKey = JobKey.jobKey(task.getName(), task.getGroup());
    			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    			if (jobDetail != null) {
    				// 恢复任务的执行
    				scheduler.resumeJob(jobKey);
    			}
    		}
    	} catch (SchedulerException e) {
    		e.printStackTrace();
    		// 处理异常
    	}
    	return "redirect:/tasks/list";
    }
}