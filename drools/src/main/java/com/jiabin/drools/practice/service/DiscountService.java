package com.jiabin.drools.practice.service;

import com.jiabin.drools.practice.entity.Product;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	 private final KieContainer kieContainer;

    @Autowired
    public DiscountService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    
    public void applyDiscount(Product product) {
    	KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}