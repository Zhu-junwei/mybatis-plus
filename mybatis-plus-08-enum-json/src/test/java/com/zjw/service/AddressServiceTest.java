package com.zjw.service;

import com.zjw.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 朱俊伟
 * @date 2023/11/17 12:34
 */
@SpringBootTest
class AddressServiceTest {

    @Autowired
    IAddressService addressService;

    /**
     * 测试逻辑删除
     */
    @Test
    void testLogicDelete() {
        //设置了逻辑删除，会把数据库逻辑删除的字段标记为已删除
        Address address = addressService.getById(59L);
        addressService.removeById(address);
        //查询的时候只查询未删除的数据 FROM address WHERE id=? AND deleted=0
        address = addressService.getById(59L);
        System.out.println("address = " + address);

    }
}