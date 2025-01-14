package com.jiabin.wechat.mp.practice.listener;

import com.jiabin.wechat.mp.practice.util.SessionContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	
        HttpSession session = se.getSession();
        log.info("session 创建："+ session.getId());
        SessionContext.addSession(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.info("session 销毁："+ session.getId());
        SessionContext.removeSession(session);
    }
}
