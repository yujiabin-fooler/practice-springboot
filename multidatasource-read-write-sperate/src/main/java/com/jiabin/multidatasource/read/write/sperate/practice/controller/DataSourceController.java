package com.jiabin.multidatasource.read.write.sperate.practice.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.jiabin.multidatasource.read.write.sperate.practice.common.annotation.DataSourceRouting;
import com.jiabin.multidatasource.read.write.sperate.practice.common.config.RoutingDataSourceContext;
import com.jiabin.multidatasource.read.write.sperate.practice.common.constant.DataSourceConst;
import com.jiabin.multidatasource.read.write.sperate.practice.dao.UserDao;
import com.jiabin.multidatasource.read.write.sperate.practice.model.entity.UserEntity;
import com.jiabin.multidatasource.read.write.sperate.practice.model.param.UserSaveParam;
import com.jiabin.multidatasource.read.write.sperate.practice.model.param.UserUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 数据源控制层
 * @Author: junqiang.lu
 * @Date: 2022/2/14
 */
@Slf4j
@RestController
@RequestMapping("/api/springboot/datasource")
public class DataSourceController {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    /**
     * 新增单条
     *
     * @param saveParam
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserSaveParam saveParam) {
        log.info("\n requestPath: {}, \n param: {}", "/api/springboot/datasource/add", saveParam);
        log.info("current datasource: {}", RoutingDataSourceContext.get());
        UserEntity userEntity = new UserEntity();
        BeanUtil.copyProperties(saveParam, userEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        userDao.insert(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    @GetMapping("/query/one")
    @DataSourceRouting(value = DataSourceConst.DATASOURCE_SLAVE)
    public ResponseEntity queryOne(Long id) {
        log.info("\n requestPath: {}, \n param: {}", "/api/springboot/datasource/query/one", id);
        log.info("current datasource: {}", RoutingDataSourceContext.get());
        UserEntity userEntity = userDao.selectById(id);
        return ResponseEntity.ok(userEntity);
    }

    /**
     * 更新单条
     *
     * @param updateParam
     * @return
     */
    @PutMapping("/update")
    @DataSourceRouting(DataSourceConst.DATASOURCE_MASTER)
    public ResponseEntity update(@RequestBody UserUpdateParam updateParam) {
        log.info("\n requestPath: {}, \n param: {}", "/api/springboot/datasource/update", updateParam);
        log.info("current datasource: {}", RoutingDataSourceContext.get());
        UserEntity userEntity = new UserEntity();
        BeanUtil.copyProperties(updateParam, userEntity, CopyOptions.create().ignoreError().ignoreNullValue());
        userDao.updateById(userEntity);
        return ResponseEntity.ok(userEntity);
    }



}
