package com.zl.ttshop.dao;

import com.zl.ttshop.dto.Page;
import com.zl.ttshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface TbItemCustomMapper {
    /**
     * 查询总数
     * @return
     */
    Long countItemsByCondition();

    /**
     * 每页的数据的集合
     * @param page
     * @return
     */
    List<TbItemCustom> listItemsByPage(Page page);
}
