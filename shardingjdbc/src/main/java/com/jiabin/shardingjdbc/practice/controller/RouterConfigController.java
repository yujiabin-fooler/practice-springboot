package com.jiabin.shardingjdbc.practice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiabin.shardingjdbc.practice.model.entity.RouterConfigEntity;
import com.jiabin.shardingjdbc.practice.model.param.RouterConfigAddParam;
import com.jiabin.shardingjdbc.practice.model.param.RouterConfigQueryOneParam;
import com.jiabin.shardingjdbc.practice.model.param.RouterConfigQueryPageParam;
import com.jiabin.shardingjdbc.practice.service.RouterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 路由器配置控制层
 * @Author jiabin.yu
 * @Date: 2022/4/1
 */
@RestController
@RequestMapping(value = "/api/sharding/jdbc/router/config")
public class RouterConfigController {

    @Autowired
    private RouterConfigService routerConfigService;

    /**
     * 路由器配置新增单条
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RouterConfigEntity> add(@RequestBody RouterConfigAddParam addParam) {
        return ResponseEntity.ok(routerConfigService.add(addParam));
    }

    /**
     * 路由器配置查询单条
     *
     * @param queryOneParam
     * @return
     */
    @GetMapping(value = "/query/one", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RouterConfigEntity> queryOne(RouterConfigQueryOneParam queryOneParam) {
        return ResponseEntity.ok(routerConfigService.queryOne(queryOneParam));
    }

    /**
     * 路由器配置分页查询
     *
     * @param queryPageParam
     * @return
     */
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<IPage<RouterConfigEntity>> queryPage(RouterConfigQueryPageParam queryPageParam) {
        return ResponseEntity.ok(routerConfigService.queryPage(queryPageParam));
    }


}
