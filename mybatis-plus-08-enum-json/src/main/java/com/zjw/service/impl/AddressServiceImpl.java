package com.zjw.service.impl;

import com.zjw.domain.Address;
import com.zjw.mapper.AddressMapper;
import com.zjw.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-11-16
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
