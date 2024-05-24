package com.jiabin.multidatasource.mybatis.annotation.practice.controller;

import com.jiabin.multidatasource.mybatis.annotation.practice.entity.People;
import com.jiabin.multidatasource.mybatis.annotation.practice.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * PeopleResource
 *
 * @author star
 **/
@RestController
@RequestMapping("/api")
public class PeopleResource {

    @Autowired
    private PeopleService peopleService;



    @GetMapping("/peoples")
    public ResponseEntity<People> getPlayer(@RequestParam String name) {
        People people = peopleService.selectPeople(name);
        return ResponseEntity.ok(people);
    }

    @PostMapping("/players")
    public ResponseEntity<Void> insertPlayer(@RequestBody People people) {
        int i = peopleService.insertPlayer(people);
        return ResponseEntity.ok().build();
    }

}
