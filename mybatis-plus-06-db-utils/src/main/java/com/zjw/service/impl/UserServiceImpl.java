package com.zjw.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.zjw.domain.Address;
import com.zjw.domain.User;
import com.zjw.domain.vo.AddressVO;
import com.zjw.domain.vo.UserVO;
import com.zjw.mapper.UserMapper;
import com.zjw.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 朱俊伟
 * @since 2023/11/15 22:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void decuctBalance(Long id, Integer money) {
        //查询用户
        User user = this.getUserById(id);
        //校验用户状态
        if (Objects.isNull(user) || user.getStatus() == 2){
            throw new RuntimeException("用户状态异常");
        }
        //余额是否充足
        if (user.getBalance() < money){
            throw new RuntimeException("余额不足");
        }
        int remain = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance,remain)
                .set(remain == 0 , User::getStatus,2)//用户没钱，账户设为冻结
                .eq(User::getId,id)
                .eq(User::getBalance,user.getBalance())//乐观锁，是查出来的值再更新
                .update();
        //扣减余额 使用lambdaUpdate方法更新
//        baseMapper.deductBalance(id,money);
    }

    @Override
    public List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance) {
        return lambdaQuery()
                .like(Objects.nonNull(name),User::getUsername,name)
                .eq(Objects.nonNull(status),User::getStatus,status)
                .ge(Objects.nonNull(minBalance),User::getBalance,minBalance)
                .le(Objects.nonNull(maxBalance),User::getBalance,maxBalance)
                .orderByDesc(User::getCreateTime)
                .list();

    }

    /**
     * Db工具的使用
     */
    @Override
    public UserVO queryUserAndAddressById(Long id) {
        User user = getById(id);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        List<Address> addressList = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, id)
                .list();
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if (!CollectionUtils.isEmpty(addressList)){
            userVO.setAddressVOList(BeanUtil.copyToList(addressList, AddressVO.class));
        }
        return userVO;
    }

}
