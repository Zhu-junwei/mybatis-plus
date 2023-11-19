package com.zjw.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zjw.domain.User;
import com.zjw.domain.dto.PageDTO;
import com.zjw.domain.dto.UserFormDTO;
import com.zjw.domain.query.UserQuery;
import com.zjw.domain.vo.UserVO;
import com.zjw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "用户管理接口")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "新增用户接口")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        //把DTO拷贝到PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @Operation(summary = "删除用户接口")
    @DeleteMapping("{id}")
    @Parameters({
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH),
    })
    public void deleteUserById(@PathVariable("id") Long id){
        userService.removeById(id);
    }

    @Operation(summary = "根据id查询用户接口")
    @GetMapping("{id}")
    @Parameters({
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH),
    })
    public UserVO queryUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        //把PO拷贝到VO
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @Operation(summary = "根据id查询用户及用户地址接口")
    @GetMapping("/{id}/address")
    @Parameters({
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH),
    })
    public UserVO queryUserAndAddressById(@PathVariable("id") Long id){
        return userService.queryUserAndAddressById(id);
    }

    @Operation(summary = "根据id批量查询用户接口")
    @GetMapping
    @Parameters({
            @Parameter(name = "ids",description = "用户id集合",required = true,in=ParameterIn.QUERY)
    })
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids){
        List<User> userList = userService.listByIds(ids);
        //把PO拷贝到VO
        return BeanUtil.copyToList(userList, UserVO.class);
    }

    @Operation(summary = "根据条件分页查询用户")
    @GetMapping("/page")
    public PageDTO<UserVO> queryUsersByPage(UserQuery query){
        return userService.queryUsersPage(query);
    }

    @Operation(summary = "扣减用户余额接口")
    @PutMapping("/{id}/deduction/{money}")
    @Parameters({
            @Parameter(name = "id",description = "用户id",required = true,in=ParameterIn.PATH),
            @Parameter(name = "money",description = "扣减的金额",required = true,in=ParameterIn.PATH)
    })
    public void decuctBalance(
            @PathVariable("id") Long id,
            @PathVariable("money") Integer money){
        userService.decuctBalance(id, money);
    }

    @Operation(summary = "根据复杂条件查询用户接口")
    @GetMapping("/list")
    public List<UserVO> queryUsers(UserQuery query){
        List<User> userList = userService.queryUsers(query.getName(),query.getStatus(),query.getMinBalance(),query.getMaxBalance());
        //把PO拷贝到VO
        return BeanUtil.copyToList(userList, UserVO.class);
    }


}
