package com.jiabin.picocli.practice.service;

import java.util.List;

public interface IMailService {
    void sendMessage(List<String> to, String subject, String text);
}