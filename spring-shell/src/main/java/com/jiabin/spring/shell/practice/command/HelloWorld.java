package com.jiabin.spring.shell.practice.command;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("HelloWorld")
public class HelloWorld {

    @ShellMethod("Say hello")
    public void hello(@ShellOption(defaultValue = "World")String name) {
        System.out.println("hello, " + name + "!");
    }

}
