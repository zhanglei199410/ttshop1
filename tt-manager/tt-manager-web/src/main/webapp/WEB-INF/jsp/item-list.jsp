<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--当前页面的HTML部分--%>
<table id="itemListDg"></table>
<%--当前页面js部分--%>
<script>
    var tb = [{
        iconCls:'icon-add',
        text:'新增',
        handler:function(){
            console.log('新增')
        }
    },{
        iconCls:'icon-remove',
        text:'删除',
        handler:function () {
            //console.log('删除')
            var selections = $('#itemListDg').datagrid('getSelections');
            //没选择删除信息
            if(selections.length == 0){
                $.messager.alert('警告','选择后才能删除!!!');
                return;
            }
            //选择删除信息
            $.messager.confirm('确认','是否确定删除???',function(r){
                if(r){
                    //把selections中的id放入放入集合ids中
                    var ids = [];
                    for(var i = 0;i < selections.length;i++){
                        ids.push(selections[i].id)
                    }
                    $.post(
                        //url
                        'item/batch',
                        //data
                        {'ids[]':ids,'flag':3},
                        //success
                        function (data) {
                            if(data > 0){
                                $('#itemListDg').datagrid('reload');
                            }
                        }
                        //,'json'
                    );

                }
            });
        }
    },{
        iconCls:'icon-up',
        text:'上架',
        handler:function(){
            var selections = $('#itemListDg').datagrid('getSelections');
            //没选择上架信息
            if(selections.length == 0){
                $.messager.alert('警告','选择后才能上架!!!');
                return;
            }
            //选择上架信息
            $.messager.confirm('确认','是否确定上架???',function(r){
                if(r){
                    //把selections中的id放入放入集合ids中
                    var ids = [];
                    for(var i = 0;i < selections.length;i++){
                        ids.push(selections[i].id)
                    }
                    $.post(
                        //url
                        'item/batch',
                        //data
                        {'ids[]':ids,'flag':1},
                        //success
                        function (data) {
                            if(data > 0){
                                $('#itemListDg').datagrid('reload');
                            }
                        }
                        //,'json'
                    );

                }
            });
        }

    },{
        iconCls:'icon-down',
        text:'下架',
        handler:function () {
            var selections = $('#itemListDg').datagrid('getSelections');
            //没选择下架信息
            if(selections.length == 0){
                $.messager.alert('警告','选择后才能下架!!!');
                return;
            }
            //选择下架信息
            $.messager.confirm('确认','是否确定下架???',function(r){
                if(r){
                    //把selections中的id放入放入集合ids中
                    var ids = [];
                    for(var i = 0;i < selections.length;i++){
                        ids.push(selections[i].id)
                    }
                    $.post(
                        //url
                        'item/batch',
                        //data
                        {'ids[]':ids,'flag':2},
                        //success
                        function (data) {
                            if(data > 0){
                                $('#itemListDg').datagrid('reload');
                            }
                        }
                        //,'json'
                    );

                }
            });
        }
    }];
    $(function () {
        //alert(1);
        $('#itemListDg').datagrid({
            width:50,
            toolbar:tb,
            url: 'items',
            striped: true,
            pagination: true,
            fit: true,
            columns: [[
//          复选框
                {field: 'ck',checkbox: true},
                {field: 'id', title: '商品编号',sortable:true},
                {field: 'title', title: '商品标题',sortable:true},
                {field: 'catName', title: '商品分类'},
                {field: 'created', title: '创建时间',formatter:function(value,row,index){
                    return moment(value).format('L');
                }},
                {field: 'priceView', title: '商品价格'},
                {field: 'status', title: '商品状态',formatter:function (value,row,index) {
                    switch (value){
                        case 1:
                            return '正常';
                            break;
                        case 2:
                            return '下架';
                            break;
                        case 3:
                            return '删除';
                            break;
                        default:
                            return '未知';
                            break;
                    }
                }}
            ]]
        });
    });
</script>