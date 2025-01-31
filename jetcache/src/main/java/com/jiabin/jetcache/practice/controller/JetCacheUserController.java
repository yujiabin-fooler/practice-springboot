package com.jiabin.jetcache.practice.controller;

import com.jiabin.jetcache.practice.common.api.ApiResult;
import com.jiabin.jetcache.practice.model.entity.UserEntity;
import com.jiabin.jetcache.practice.model.param.user.*;
import com.jiabin.jetcache.practice.service.IJetCacheUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表
 * 
 * @author junqiang.lu
 * @date 2020-08-31 16:39:56
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/jetcache/user")
@Api(value = "JetCache-用户表控制层", tags = "JetCache-用户表控制层")
public class JetCacheUserController {

	@Autowired
	private IJetCacheUserService userService;

    /**
     * 保存(单条)
     *
     * @param userSaveParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表保存(单条)",  notes = "用户表保存(单条)")
    public ResponseEntity<?> save(@Validated @RequestBody UserSaveParam userSaveParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(userService.save(userSaveParam)), headers, HttpStatus.OK);
    }

    /**
     * 查询详情(单条)
     *
     * @param userInfoParam
     * @return
     */
    @GetMapping(value = "/info", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表查询详情(单条)",  notes = "用户表查询详情(单条)")
    public ResponseEntity<ApiResult<UserEntity>> info(@Validated UserInfoParam userInfoParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(userService.info(userInfoParam)), headers, HttpStatus.OK);
    }

    /**
     * 查询列表
     *
     * @param userListParam
     * @return
     */
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表查询列表",  notes = "用户表查询列表")
    public ResponseEntity<?> list(@Validated UserListParam userListParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(userService.list(userListParam)), headers, HttpStatus.OK);
    }

    /**
     * 修改(单条)
     *
     * @param userUpdateParam
     * @return
     */
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表修改(单条)",  notes = "用户表修改(单条)")
    public ResponseEntity<?> update(@Validated @RequestBody UserUpdateParam userUpdateParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(userService.update(userUpdateParam)), headers, HttpStatus.OK);
    }

    /**
     * 删除(单条)
     *
     * @param userDeleteParam
     * @return
     */
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "用户表删除(单条)",  notes = "用户表删除(单条)")
    public ResponseEntity<?> delete(@Validated @RequestBody UserDeleteParam userDeleteParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(ApiResult.success(userService.delete(userDeleteParam)), headers, HttpStatus.OK);
    }





}
