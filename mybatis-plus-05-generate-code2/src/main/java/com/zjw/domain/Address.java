package com.zjw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author zjw
 * @since 2024-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县/区
     */
    private String town;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 详细地址
     */
    private String street;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    private Boolean isDefault;

    /**
     * 备注
     */
    private String notes;

    /**
     * 逻辑删除
     */
    private Boolean deleted;
}
