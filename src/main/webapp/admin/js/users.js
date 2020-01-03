$(function () {

    $('#dg').datagrid({
        url:'getUsers',
        pagination:true,  //开启分页
        pageSize:6,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'用户名',width:100,align:'right'},
            {field:'telephone',title:'电话',width:100,align:'right'},
            {field:'isadmin',title:'是否是管理员',width:100,align:'right'},
            {field:'opt',title:'修改|删除',width:100,align:'right',
                formatter:function (value,row,index) {
                    return "<a href='javascript:toEdit("+row.id+")'>修改</a> <a href='javascript:toDelete("+row.id+")'>删除</a>";
                }}
        ]]
    });
});

function searchUser() {

    var inputName =$("#inputName").val();
    var inputTel = $("#inputTel").val();
    $("#dg").datagrid("load",{

        name:inputName,
        telephone:inputTel
    });
}

