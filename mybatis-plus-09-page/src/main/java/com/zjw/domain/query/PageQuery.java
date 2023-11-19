package com.zjw.domain.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Objects;

/**
 * @author 朱俊伟
 * @date 2023/11/17 17:09
 */
@Data
@Schema(description = "分页查询")
public class PageQuery {
    @Schema(description = "页码",nullable = false)
    private Integer pageNo;
    @Schema(description = "页大小")
    private Integer pageSize;
    @Schema(description = "排序字段")
    private String sortBy;
    @Schema(description = "是否升序")
    private Boolean isAsc;

    /*public static <PO, VO> PageDTO<VO> of(Page<PO> p, Class<VO> clazz){
        PageDTO<VO> dto = new PageDTO<>();
        dto.setTotal(p.getTotal());
        dto.setPages(p.getPages());
        List<PO> records = p.getRecords();
        if (CollUtil.isEmpty(records)){
            dto.setList(Collections.emptyList());
            return dto;
        }
        dto.setList(BeanUtil.copyToList(records,clazz));
        return dto;
    }*/

    public <T> Page<T> toMpPage(OrderItem ... items){
        Page<T> page = Page.of(pageNo, pageSize);
        if (StrUtil.isNotBlank(sortBy)) {
            page.addOrder(new OrderItem(sortBy, isAsc));
        } else if (Objects.nonNull(items)){
            page.addOrder(items);
        }
        return page;
    }

    public <T> Page<T> toMpPage(){
        return toMpPage(null);
    }
}
