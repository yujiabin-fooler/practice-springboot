package com.jiabin.detect.faces.practice.service;


import com.jiabin.detect.faces.practice.entity.User;

public interface FaceEngineService {

    User detectFaces(String file);
}
