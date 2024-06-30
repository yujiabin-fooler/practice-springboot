package com.jiabin.redis.geographic.location.info.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoLocationService {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public void addLocation(String userId, double longitude, double latitude) {
        User user = new User(userId, "username", longitude, latitude);
        // 使用Geospatial索引存储用户位置
//        redisTemplate.opsForGeo().add("userLocations", new GeoLocation(user.getLongitude(), user.getLatitude()), userId);
    }

    public List<User> getUsersNearby(double longitude, double latitude, double radius) {
        // 查询给定位置附近的用户
//        List<GeoWithin> nearbyUsersGeo = redisTemplate.opsForGeo().radius("userLocations",
//                new Circle(new GeoCoordinate(latitude, longitude), radius),
//                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs());
//
//        List<User> nearbyUsers = new ArrayList<>();
//        for (GeoWithin geoWithin : nearbyUsersGeo) {
//            nearbyUsers.add(redisTemplate.opsForValue().get(geoWithin.getMember()));
//        }
//        return nearbyUsers;
        return null;
    }
}