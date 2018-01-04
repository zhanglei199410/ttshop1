package com.zl.ttshop.service;

import com.zl.ttshop.dto.Page;
import com.zl.ttshop.dto.Result;
import com.zl.ttshop.pojo.po.TbItem;
import com.zl.ttshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {
    TbItem getItemById(Long itemId);

    List<TbItem> listItems();

    Result<TbItemCustom> listItemsByPage(Page page);

    int batchUpdate(List<Long> ids,Integer flag);
}
