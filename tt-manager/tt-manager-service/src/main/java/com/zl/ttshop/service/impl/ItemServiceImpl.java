package com.zl.ttshop.service.impl;

import com.zl.ttshop.dao.TbItemCustomMapper;
import com.zl.ttshop.dao.TbItemMapper;
import com.zl.ttshop.dto.Page;
import com.zl.ttshop.dto.Result;
import com.zl.ttshop.pojo.po.TbItem;
import com.zl.ttshop.pojo.po.TbItemExample;
import com.zl.ttshop.pojo.vo.TbItemCustom;
import com.zl.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Override
    public TbItem getItemById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    /**
     * 不分页
     * @return
     */
    @Override
    public List<TbItem> listItems() {
        List<TbItem> list = null;
        try{
            list = itemDao.selectByExample(null);

        }catch(Exception e){
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 分页的
     * @param page
     * @return
     */
    @Override
    public Result<TbItemCustom> listItemsByPage(Page page) {
        Result<TbItemCustom> result = new Result<>();
        try{
            Long total = itemCustomDao.countItemsByCondition();
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(page);
            //商品的总数
            result.setTotal(total);
            //一页商品的集合
            result.setRows(list);

        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新状态
     * @param ids
     * @return
     */
    @Override
    public int batchUpdate(List<Long> ids ,Integer flag) {
        int i = 0;
        int f = flag;
        try {
            //创建一个对象，设置商品的状态为3
            TbItem item = new TbItem();
            item.setStatus((byte)f);
            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行更新操作
            i = itemDao.updateByExampleSelective(item,example);
        }catch (Exception e ){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}
