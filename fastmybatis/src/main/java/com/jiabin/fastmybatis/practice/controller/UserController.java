package com.jiabin.fastmybatis.practice.controller;

import com.gitee.fastmybatis.core.PageInfo;
import com.gitee.fastmybatis.core.query.Query;
import com.jiabin.fastmybatis.practice.model.TUser;
import com.jiabin.fastmybatis.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 增删改查例子
 *
 * @author thc
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 分页查询
     * http://localhost:8080/user/page?id=10
     * http://localhost:8080/user/page?pageIndex=1&pageSize=5
     *
     * @param param
     * @return
     */
    @GetMapping("/user/page")
    public Result<PageInfo<TUser>> page(UserParam param) {
        Query query = param.toQuery();
        PageInfo<TUser> pageInfo = userService.page(query);
        return Result.ok(pageInfo);
    }

    /**
     * 新增记录，这里为了方便演示用了GET方法，实际上应该使用POST
     * http://localhost:8080/user/save?username=jim
     *
     * @param user
     * @return
     */
    @GetMapping("/user/save")
    public Result<Integer> save(TUser user) {
        userService.saveIgnoreNull(user);
        // 返回添加后的主键值
        return Result.ok(user.getId());
    }

    /**
     * 修改记录，这里为了方便演示用了GET方法，实际上应该使用POST
     * http://localhost:8080/user/update?id=10&username=jim
     *
     * @param user 表单数据
     * @return
     */
    @GetMapping("/user/update")
    public Result<?> update(TUser user) {
        userService.updateIgnoreNull(user);
        return Result.ok();
    }

    /**
     * 删除记录，这里为了方便演示用了GET方法，实际上应该使用DELETE
     * http://localhost:8080/user/delete?id=10
     *
     * @param id 主键id
     * @return
     */
    @GetMapping("/user/delete")
    public Result<?> delete(Integer id) {
        userService.deleteById(id);
        return Result.ok();
    }

    /**
     * 分页+多字段排序查询
     * http://localhost:8080/user/pagesort?pageIndex=1&pageSize=5&id=10&sortInfo=id%3Ddesc%2CaddTime%3Dasc
     *
     * @param param
     * @return
     */
    @GetMapping("/user/pagesort")
    public Result<PageInfo<TUser>> pagesort(UserParam param) {
        Query query = param.toQuery();
        PageInfo<TUser> pageInfo = userService.page(query);
        return Result.ok(pageInfo);
    }
}

