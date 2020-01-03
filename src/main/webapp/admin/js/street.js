$(function () {

    $('#dg').datagrid({
        url:'getStreet',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'街道名称',width:100,align:'right'},
            {field:'districtId',title:'所属区域编号',width:100,align:'right'},
            {field:'opt',title:'修改|删除',width:100,align:'right',
                formatter:function (value,row,index) {
                    return "<a href='javascript:toEdit("+row.id+")'>修改</a> <a href='javascript:toDelete("+row.id+")'>删除</a>";
                }}
        ]]
    });
});

//触发工具栏的添加事件，打开窗口
function goAdd(){
    //打开对话框
    //$("#AddDialog").dialog("open");
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}

//关闭窗口
function CloseDialog(Dialog) {
    $("#"+Dialog).dialog("close");
}

//保存添加数据
function SaveDialog() {
    //将表单序列化参数数据
    // var param = $("#AddDialogForm").serialize();
    // $.post("addDistrict",param,function (data) {
    //     if(data.result>0){
    //         //成功关闭窗口
    //         $("#AddDialog").dialog("close");
    //     }else{
    //         //alert("sss");
    //         $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info');
    //         }
    // },"json");

    $("#AddDialogForm").form('submit',{
        url:"addStreet",
        success:function (data) {
            var obj = $.parseJSON(data);
            if (obj.result>0){
                $("#dg").datagrid('reload');
                $("#AddDialog").dialog("close");

            }else {
                $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info');
            }
        }
    });
}

function goUpdate() {
    //获取datagrid的选中行
    var selObjs = $("#dg").datagrid("getSelections");
    //判断是否选择一行或多行
    if (selObjs.length == 1){
        $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
        var id = selObjs[0].id;
        $.post("showStreet",{"id":id},function (data) {
            $("#upDialogForm").form('load',data);
        },"json")
    }else {
        $.messager.alert('友情提示','您可能没有没有选中行或选择了多行，请重新选中','info');
    }
}

function upSaveDialog() {

    $("#upDialogForm").form('submit',{
        url:"updateStreet",
        success:function (data) {
            var obj = $.parseJSON(data);
            console.log(obj);
            if (obj.result>0){

                $('#dg').datagrid('reload');  //刷新
                $("#upDialog").dialog("close");

            }else {
                $.messager.alert('友情提示','修改失败，请联系管理员:13260601227!','info');
            }
        }
    });

}

function toEdit(id) {
    $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
    $.post("showStreet",{"id":id},function (data) {
        $("#upDialogForm").form('load',data);
    },"json")
}

function toDelete(id) {
    $.messager.confirm('友情提示','是否确认删除',function (r) {

        if (r){

            $.post("deleteStreet",{"id":id},function (data) {

                if (data.result>0){

                    $("#dg").datagrid('reload');
                }else {
                    $.messager.alert('友情提示','删除失败，请联系管理员:13260601227!','info');
                }
            },"json");
        }
    });
}

function deleteMoreDistrict() {
    //获取datagrid的选中行
    var selObjs = $("#dg").datagrid("getSelections");
    if (selObjs.length>0){
        $.messager.confirm('友情提示','确定要删除吗？',function (r) {
            if (r){
                var str = "";
                for (var i = 0; i <selObjs.length ; i++) {
                    str = str+selObjs[i].id+",";
                }
                str = str.substring(0,str.length-1);
                $.post("removeMoreStreet",{"ids":str},function (data) {

                    if (data.result>0){

                        $("#dg").datagrid('reload');
                    }else {
                        $.messager.alert('友情提示','批量删除失败，请联系管理员:13260601227!','info');
                    }
                },"json");
            }
        })
    }else {
        $.messager.alert('友情提示','请选择至少一行删除','info');
    }
}