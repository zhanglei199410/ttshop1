package com.zl.ttshop.web;

import com.zl.ttshop.dto.Page;
import com.zl.ttshop.dto.Result;
import com.zl.ttshop.pojo.po.TbItem;
import com.zl.ttshop.pojo.vo.TbItemCustom;
import com.zl.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable("itemId") Long itemId){

        return itemService.getItemById(itemId);
    }
   /* //没有分页的
    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public List<TbItem> listItems(){
        List<TbItem> list = null;
        try{
            list = itemService.listItems();
           // System.out.println(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }
*/
//有分页的
    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public Result<TbItemCustom> listItems(Page page){
        Result<TbItemCustom> result = null;
        try{
            result = itemService.listItemsByPage(page);
        }catch(Exception e){
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除更新
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "item/batch",method = RequestMethod.POST)
    public int batchUpdate(@RequestParam("ids[]") List<Long> ids ,@RequestParam("flag") Integer flag){
        int i = 0;
        try{
            i = itemService.batchUpdate(ids,flag);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;

    }

}
