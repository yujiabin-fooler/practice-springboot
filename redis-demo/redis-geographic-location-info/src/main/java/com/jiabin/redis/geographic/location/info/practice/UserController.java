package com.jiabin.redis.geographic.location.info.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private GeoLocationService geoLocationService;

    @PostMapping("/addLocation")
    public ResponseEntity<String> addLocation(@RequestParam String userId,
                                              @RequestParam double longitude,
                                              @RequestParam double latitude) {
        geoLocationService.addLocation(userId, longitude, latitude);
        return ResponseEntity.ok("User location added");
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<User>> getUsersNearby(@RequestParam double longitude,
                                                     @RequestParam double latitude,
                                                     @RequestParam double radius) {
        List<User> nearbyUsers = geoLocationService.getUsersNearby(longitude, latitude, radius);
        return ResponseEntity.ok(nearbyUsers);
    }
}