package com.jiabin.caffeine.practice.controller;

import com.jiabin.caffeine.practice.UserService;
import com.jiabin.caffeine.practice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Cacheable("users")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute User user) {
    	userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String editUserSubmit(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
    	userService.deleteById(id);
        return "redirect:/";
    }
}