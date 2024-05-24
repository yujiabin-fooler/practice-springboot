

使用 Springboot+ Quartz + Thymeleaf + Bootstrap 定时任务功能

Quartz是一个开源的任务调度框架，它允许您创建和管理定时任务、计划任务、周期性任务等。Quartz提供了丰富的功能，允许您按照各种时间表执行任务，从简单的每日任务到复杂的业务工作流程。以下是Quartz框架的详细介绍：

### 核心概念：

1. **Job（任务）**：表示要执行的工作单元。任务是一个接口，您需要实现`execute`方法，该方法包含任务的实际逻辑。
2. **Trigger（触发器）**：表示何时执行任务。触发器可以基于时间表、日期或其他条件触发任务的执行。Quartz提供了多种内置触发器，如`SimpleTrigger`和`CronTrigger`。
3. **Scheduler（调度器）**：用于执行任务的主要引擎。它接收触发器的请求，计划任务的执行，管理任务的状态，并触发任务的执行。
4. **JobDetail（任务细节）**：包含任务的详细信息，如任务的类、任务的名称和任务的分组等。
5. **JobDataMap**：可用于传递任务执行时所需的参数。
6. **Cron表达式**：一种字符串表示法，用于定义任务的执行时间表，非常灵活，可以表示几乎任何时间间隔。

### Quartz的特性：

1. **强大的灵活性**：Quartz支持各种任务的执行时间表，包括一次性、周期性和复杂的时间表。
2. **任务持久性**：Quartz允许将任务和触发器存储在数据库中，以便任务状态持久化。
3. **集群支持**：Quartz可以在多个节点上运行，实现任务的分布式调度。
4. **错过执行处理**：Quartz允许您定义任务错过执行时的处理方式，如立即执行、丢弃错过的执行或等待下一个触发时间。
5. **任务依赖关系**：您可以定义任务之间的依赖关系，以确保一个任务在另一个任务执行之前完成。
6. **监听器**：Quartz提供了任务和触发器的监听器，以便监视和记录任务的执行情况。

### Quartz的用途：

Quartz可用于各种应用场景，包括：

1. **定时任务**：执行周期性任务，如数据备份、日志清理、报表生成等。
2. **调度器**：创建复杂的任务调度，如任务队列、工作流程、批处理等。
3. **定时提醒**：发送定时提醒、通知和提醒。
4. **集成应用**：集成到Java应用中，以实现任务的动态调度和管理。

以下是使用Spring Boot、Quartz、Thymeleaf和Bootstrap创建定时任务应用的示例项目配置和核心代码。这个示例将展示如何创建一个简单的定时任务管理应用，包括任务列表、创建任务和编辑任务、开始及停止任务功能。

###  创建Spring Boot项目

Quartz 自带有数据库模式，脚本都是现成的：

下载这个脚本：

https://gitee.com/qianwei4712/code-of-shiva/blob/master/quartz/quartz.sql
建表语句如下：
DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

-- ----------------------------
-- 1、存储每一个已配置的 jobDetail 的详细信息
-- ----------------------------
create table QRTZ_JOB_DETAILS (
sched_name           varchar(120)    not null            comment '调度名称',
job_name             varchar(200)    not null            comment '任务名称',
job_group            varchar(200)    not null            comment '任务组名',
description          varchar(250)    null                comment '相关介绍',
job_class_name       varchar(250)    not null            comment '执行任务类名称',
is_durable           varchar(1)      not null            comment '是否持久化',
is_nonconcurrent     varchar(1)      not null            comment '是否并发',
is_update_data       varchar(1)      not null            comment '是否更新数据',
requests_recovery    varchar(1)      not null            comment '是否接受恢复执行',
job_data             blob            null                comment '存放持久化job对象',
primary key (sched_name, job_name, job_group)
) engine=innodb comment = '任务详细信息表';

-- ----------------------------
-- 2、 存储已配置的 Trigger 的信息
-- ----------------------------
create table QRTZ_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_name         varchar(200)    not null            comment '触发器的名字',
trigger_group        varchar(200)    not null            comment '触发器所属组的名字',
job_name             varchar(200)    not null            comment 'qrtz_job_details表job_name的外键',
job_group            varchar(200)    not null            comment 'qrtz_job_details表job_group的外键',
description          varchar(250)    null                comment '相关介绍',
next_fire_time       bigint(13)      null                comment '上一次触发时间（毫秒）',
prev_fire_time       bigint(13)      null                comment '下一次触发时间（默认为-1表示不触发）',
priority             integer         null                comment '优先级',
trigger_state        varchar(16)     not null            comment '触发器状态',
trigger_type         varchar(8)      not null            comment '触发器的类型',
start_time           bigint(13)      not null            comment '开始时间',
end_time             bigint(13)      null                comment '结束时间',
calendar_name        varchar(200)    null                comment '日程表名称',
misfire_instr        smallint(2)     null                comment '补偿执行的策略',
job_data             blob            null                comment '存放持久化job对象',
primary key (sched_name, trigger_name, trigger_group),
foreign key (sched_name, job_name, job_group) references QRTZ_JOB_DETAILS(sched_name, job_name, job_group)
) engine=innodb comment = '触发器详细信息表';

-- ----------------------------
-- 3、 存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
-- ----------------------------
create table QRTZ_SIMPLE_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
repeat_count         bigint(7)       not null            comment '重复的次数统计',
repeat_interval      bigint(12)      not null            comment '重复的间隔时间',
times_triggered      bigint(10)      not null            comment '已经触发的次数',
primary key (sched_name, trigger_name, trigger_group),
foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = '简单触发器的信息表';

-- ----------------------------
-- 4、 存储 Cron Trigger，包括 Cron 表达式和时区信息
-- ---------------------------- 
create table QRTZ_CRON_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
cron_expression      varchar(200)    not null            comment 'cron表达式',
time_zone_id         varchar(80)                         comment '时区',
primary key (sched_name, trigger_name, trigger_group),
foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Cron类型的触发器表';

-- ----------------------------
-- 5、 Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)
-- ---------------------------- 
create table QRTZ_BLOB_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
blob_data            blob            null                comment '存放持久化Trigger对象',
primary key (sched_name, trigger_name, trigger_group),
foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = 'Blob类型的触发器表';

-- ----------------------------
-- 6、 以 Blob 类型存储存放日历信息， quartz可配置一个日历来指定一个时间范围
-- ---------------------------- 
create table QRTZ_CALENDARS (
sched_name           varchar(120)    not null            comment '调度名称',
calendar_name        varchar(200)    not null            comment '日历名称',
calendar             blob            not null            comment '存放持久化calendar对象',
primary key (sched_name, calendar_name)
) engine=innodb comment = '日历信息表';

-- ----------------------------
-- 7、 存储已暂停的 Trigger 组的信息
-- ---------------------------- 
create table QRTZ_PAUSED_TRIGGER_GRPS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
primary key (sched_name, trigger_group)
) engine=innodb comment = '暂停的触发器表';

-- ----------------------------
-- 8、 存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息
-- ---------------------------- 
create table QRTZ_FIRED_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
entry_id             varchar(95)     not null            comment '调度器实例id',
trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
instance_name        varchar(200)    not null            comment '调度器实例名',
fired_time           bigint(13)      not null            comment '触发的时间',
sched_time           bigint(13)      not null            comment '定时器制定的时间',
priority             integer         not null            comment '优先级',
state                varchar(16)     not null            comment '状态',
job_name             varchar(200)    null                comment '任务名称',
job_group            varchar(200)    null                comment '任务组名',
is_nonconcurrent     varchar(1)      null                comment '是否并发',
requests_recovery    varchar(1)      null                comment '是否接受恢复执行',
primary key (sched_name, entry_id)
) engine=innodb comment = '已触发的触发器表';

-- ----------------------------
-- 9、 存储少量的有关 Scheduler 的状态信息，假如是用于集群中，可以看到其他的 Scheduler 实例
-- ---------------------------- 
create table QRTZ_SCHEDULER_STATE (
sched_name           varchar(120)    not null            comment '调度名称',
instance_name        varchar(200)    not null            comment '实例名称',
last_checkin_time    bigint(13)      not null            comment '上次检查时间',
checkin_interval     bigint(13)      not null            comment '检查间隔时间',
primary key (sched_name, instance_name)
) engine=innodb comment = '调度器状态表';

-- ----------------------------
-- 10、 存储程序的悲观锁的信息(假如使用了悲观锁)
-- ---------------------------- 
create table QRTZ_LOCKS (
sched_name           varchar(120)    not null            comment '调度名称',
lock_name            varchar(40)     not null            comment '悲观锁名称',
primary key (sched_name, lock_name)
) engine=innodb comment = '存储的悲观锁信息表';

-- ----------------------------
-- 11、 Quartz集群实现同步机制的行锁表
-- ---------------------------- 
create table QRTZ_SIMPROP_TRIGGERS (
sched_name           varchar(120)    not null            comment '调度名称',
trigger_name         varchar(200)    not null            comment 'qrtz_triggers表trigger_name的外键',
trigger_group        varchar(200)    not null            comment 'qrtz_triggers表trigger_group的外键',
str_prop_1           varchar(512)    null                comment 'String类型的trigger的第一个参数',
str_prop_2           varchar(512)    null                comment 'String类型的trigger的第二个参数',
str_prop_3           varchar(512)    null                comment 'String类型的trigger的第三个参数',
int_prop_1           int             null                comment 'int类型的trigger的第一个参数',
int_prop_2           int             null                comment 'int类型的trigger的第二个参数',
long_prop_1          bigint          null                comment 'long类型的trigger的第一个参数',
long_prop_2          bigint          null                comment 'long类型的trigger的第二个参数',
dec_prop_1           numeric(13,4)   null                comment 'decimal类型的trigger的第一个参数',
dec_prop_2           numeric(13,4)   null                comment 'decimal类型的trigger的第二个参数',
bool_prop_1          varchar(1)      null                comment 'Boolean类型的trigger的第一个参数',
bool_prop_2          varchar(1)      null                comment 'Boolean类型的trigger的第二个参数',
primary key (sched_name, trigger_name, trigger_group),
foreign key (sched_name, trigger_name, trigger_group) references QRTZ_TRIGGERS(sched_name, trigger_name, trigger_group)
) engine=innodb comment = '同步机制的行锁表';

commit;


创建任务表的 DDL语句

```sql
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `group` varchar(255) NOT NULL,
  `cron_expression` varchar(255) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
```

首先，创建一个Spring Boot项目并添加必要的依赖项。在`pom.xml`文件中，添加以下依赖项：

```xml
<!-- Spring Boot Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Spring Boot Starter Thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!--jdbc-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <!--quartz-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>
<!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
```

###  配置数据库连接

在`application.properties`文件中配置数据库连接信息：

```properties
server.port=8080

# Thymeleaf配置
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML5

spring.datasource.url=jdbc:mysql://localhost:3306/wx_sample
spring.datasource.username=root
spring.datasource.password=root

# Quartz Properties
spring.quartz.job-store-type=jdbc
spring.quartz.properties.org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.StdJDBCDelegate
spring.quartz.properties.org.quartz.jobStore.tablePrefix=QRTZ_
spring.quartz.properties.org.quartz.jobStore.isClustered=false
#第一次启动设置 always，再次启动设置为 never
spring.quartz.jdbc.initialize-schema=never

# Hibernate Properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.ddl-auto=update
```

### 创建Quartz任务

创建一个简单的Quartz任务，这是一个示例：

```java
package com.icoderoad.example.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	if (Thread.currentThread().isInterrupted()) {
            // 响应中断请求
            // 可以执行清理操作并退出任务
            return;
        }
		 // 在这里编写定时任务的具体逻辑
	    System.out.println("定时任务执行：" + new Date());
    }

}
```

**配置类 SchedulerConfig.java**

```java
package com.icoderoad.example.quartz.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QuartzProperties quartzProperties;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
```

**工厂类 SchedulerJobFactory.java**

```java
package com.icoderoad.example.quartz.conf;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class SchedulerJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}
```

### 创建实体类

创建一个实体类`Task`来表示任务，并一个DTO类`TaskForm`用于创建和编辑任务时的数据传输。

```java
package com.icoderoad.example.quartz.entity;

public class TaskForm {
    private String name;
    private String group;
    private String ClassName;
    private String cronExpression;

    public TaskForm() {
        // 默认构造函数
    }

    public TaskForm(String name, String group, String cronExpression) {
        this.name = name;
        this.group = group;
        this.cronExpression = cronExpression;
    }
    
    public TaskForm(String name, String group, String cronExpression, String className) {
    	this.name = name;
    	this.group = group;
    	this.cronExpression = cronExpression;
    	this.ClassName = className;		
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                '}';
    }
}
```

**Task实体类**：

```java
package com.icoderoad.example.quartz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;//任务名
    @Column(name = "`group`")
    private String group;//分组
    
    private String status; // 任务状态
    private String cronExpression;//定时表达式
    private String className;//要执行类名
}
```

**TaskRepository接口**：

```java
package com.icoderoad.example.quartz.repository;

import org.springframework.data.repository.CrudRepository;

import com.icoderoad.example.quartz.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
```

### 创建Controller

创建一个Spring Boot控制器来处理前端页面的请求，并与Quartz集成来管理任务的创建和编辑。

```java
package com.icoderoad.example.quartz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icoderoad.example.quartz.entity.Task;
import com.icoderoad.example.quartz.entity.TaskForm;
import com.icoderoad.example.quartz.repository.TaskRepository;

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
```

###  创建前端视图

创建Thymeleaf模板来管理任务的列表、创建和编辑。

在`src/main/resources/templates`目录下创建`task-list.html`、`create-task.html`和`edit-task.html`等文件，编写HTML代码，并使用Thymeleaf来渲染数据。

task-list.html 页面

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
    <!-- 添加Bootstrap CSS -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-4">
    <h2>任务列表</h2>
     <a th:href="@{/tasks/create}" class="btn btn-primary">添加任务</a> <!-- 添加任务按钮 -->
    <table class="table">
        <thead>
            <tr>
                <th>任务名称</th>
                <th>分组</th>
                <th>Cron表达式</th>
                <th>运行状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="task : ${tasks}">
                <td th:text="${task.name}"></td>
                <td th:text="${task.group}"></td>
                <td th:text="${task.cronExpression}"></td>
                <td th:text="${task.status}"></td>
                <td>
                    <a th:href="@{'/tasks/edit?taskId=' + ${task.id}}" class="btn btn-sm btn-primary">编辑</a>
                   	<a href="#" th:onclick="'startTask(' + ${task.id} + '); return false;'"  class="btn btn-sm btn-success">执行任务</a>
                   	<a href="#" th:onclick="'startOnceTask(' + ${task.id} + '); return false;'"  class="btn btn-sm btn-success">执行一次</a>
                    <a href="#" th:onclick="'stopTask(' + ${task.id} + '); return false;'"  class="btn btn-sm btn-info">暂停</a>
                    <a href="#" th:onclick="'resumeTask(' + ${task.id} + '); return false;'"  class="btn btn-sm btn-success">恢复运行</a>
                    <a href="#" th:onclick="'delTask(' + ${task.id} + '); return false;'"  class="btn btn-sm btn-danger">删除</a>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<script>
    function startTask(taskId) {
        $.post("/tasks/start?taskId=" + taskId, function(data) {
            // 处理成功或失败的响应
            location.reload();
        });
    }
    function startOnceTask(taskId) {
        $.post("/tasks/startOnce?taskId=" + taskId, function(data) {
            // 处理成功或失败的响应
            location.reload();
        });
    }

    function stopTask(taskId) {
        $.post("/tasks/stop?taskId=" + taskId, function(data) {
            // 处理成功或失败的响应
            location.reload();
        });
    }
    function resumeTask(taskId) {
        $.post("/tasks/resume?taskId=" + taskId, function(data) {
            // 处理成功或失败的响应
            location.reload();
        });
    }
    function delTask(taskId) {
        $.post("/tasks/delete?taskId=" + taskId, function(data) {
            // 处理成功或失败的响应
            location.reload();
        });
    }
</script>
</body>
</html>
```

create-task.html`页面`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>创建任务</title>
    <!-- 添加Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <!-- 添加cronstrue和cron-parser依赖 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cronstrue/2.0.0/cronstrue.min.js"></script>
    <script src="https://unpkg.com/cron-parser@4.6.0/lib/parser.js"></script>
</head>
<body>
<div class="container mt-4">
    <h2>创建任务</h2>
    <a href="/tasks/list" class="btn btn-secondary">返回任务列表</a>
    <form method="post" th:object="${taskForm}" th:action="@{/tasks/create}">
        <div class="form-group">
            <label for="name">任务名称</label>
            <input type="text" class="form-control" id="name" th:field="*{name}" required>
        </div>
        <div class="form-group">
            <label for="group">分组</label>
            <input type="text" class="form-control" id="group" th:field="*{group}" required>
        </div>
        <div class="form-group">
            <label for="cronExpression">Cron 表达式</label>
            <input type="text" class="form-control" id="cronExpression" th:field="*{cronExpression}" required>
        </div>
        <div class="form-group">
            <label for="className">任务类名</label>
            <input type="text" class="form-control" id="className" name="className" required>
        </div>
        <small id="cronDescription" class="form-text text-muted"></small>
        <button type="submit" class="btn btn-primary">创建</button>
    </form>
    <div th:if="${error}" class="alert alert-danger" th:text="${error}"></div>
</div>

<!-- JavaScript代码 -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var cronExpressionInput = document.getElementById('cronExpression');
        var cronDescription = document.getElementById('cronDescription');
        
        cronExpressionInput.addEventListener('input', function () {
            var cronExpression = cronExpressionInput.value;
            try {
                var interval = cronstrue.toString(cronExpression);
                cronDescription.textContent = '描述: ' + interval;
            } catch (e) {
                cronDescription.textContent = '无效的Cron表达式';
            }
        });
    });
</script>
</body>
</html>
```

edit-task.html页面

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>编辑任务</title>
    <!-- 添加Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <!-- 添加cronstrue和cron-parser依赖 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cronstrue/2.32.0/cronstrue.min.js"></script>
    <script src="https://unpkg.com/cron-parser@4.6.0/lib/parser.js"></script>
</head>
<body>
<div class="container mt-4">
    <h2>编辑任务</h2>
    <a href="/tasks/list" class="btn btn-secondary">返回任务列表</a>
    <form method="post" th:object="${taskForm}" th:action="@{/tasks/edit}">
        <div class="form-group">
            <label for="name">任务名称</label>
            <input type="text" class="form-control" id="name" th:field="*{name}" required>
        </div>
        <div class="form-group">
            <label for="group">分组</label>
            <input type="text" class="form-control" id="group" th:field="*{group}" required>
        </div>
        <div class="form-group">
            <label for="cronExpression">Cron 表达式</label>
            <input type="text" class="form-control" id="cronExpression" th:field="*{cronExpression}" required>
        </div>
         <div class="form-group">
            <label for="className">任务类名</label>
            <input type="text" class="form-control" id="className" th:field="*{className}" required>
        </div>
        <small id="cronDescription" class="form-text text-muted"></small>
        <input type="hidden" name="taskId" th:value="${taskId}">
        <button type="submit" class="btn btn-primary">保存</button>
    </form>
    <div th:if="${error}" class="alert alert-danger" th:text="${error}"></div>
</div>

<!-- JavaScript代码 -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var cronExpressionInput = document.getElementById('cronExpression');
        var cronDescription = document.getElementById('cronDescription');
        
        cronExpressionInput.addEventListener('input', function () {
            var cronExpression = cronExpressionInput.value;
            try {
                var interval = cronstrue.toString(cronExpression);
                cronDescription.textContent = '描述: ' + interval;
            } catch (e) {
                cronDescription.textContent = '无效的Cron表达式';
            }
        });
    });
</script>
</body>
</html>
```

### 启动应用

运行Spring Boot应用，然后访问前端页面 http://localhost:8080/tasks/list ,点击添任务，添加一个测试 任务，任务名称、 分组、Cron表达式，任务类名分别为：示例任务1、group1、com.icoderoad.example.quartz.job.SampleJob。任务创建成功后，可以编辑、删除、 执行，停止、恢复任务运行。