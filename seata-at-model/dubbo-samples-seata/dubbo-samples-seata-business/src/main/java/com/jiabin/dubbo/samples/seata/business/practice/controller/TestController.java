/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.jiabin.dubbo.samples.seata.business.practice.controller;

import com.jiabin.dubbo.samples.seata.api.practice.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final BusinessService businessService;

    public TestController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/commit")
    public String commit(@RequestParam String userId,@RequestParam String commodityCode,@RequestParam int orderCount){
        this.businessService.purchaseCommit(userId,commodityCode,orderCount);
        return "commit";
    }

    @GetMapping("/rollback")
    public String rollback(@RequestParam String userId,@RequestParam String commodityCode,@RequestParam int orderCount){
        try {
            this.businessService.purchaseRollback(userId,commodityCode,orderCount);
            return "commit";
        }catch (Exception e){
            return "rollback";
        }
    }
}
