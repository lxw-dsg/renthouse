$(function () {

    $('#dg').datagrid({
        url:'getHouseAll',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[5,8,10,20],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'title',title:'标题',width:100,align:'right'},
            {field:'dname',title:'区域',width:100,align:'right'},
            {field:'sname',title:'街道',width:100,align:'right'},
            {field:'tname',title:'类型',width:100,align:'right'},
            {field:'price',title:'价格',width:100,align:'right'},
            {field:'floorage',title:'面积',width:100,align:'right'},
            {field:'ispass',title:'状态',width:100,align:'right',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value 表示当前字段的值， row当前行的值 index表示索引
                    return value==0?"未审核":"已审核";
                }
            },
            {field:'opt',title:'审核|详情',width:100,align:'right',
                formatter:function (value,row,index) {
                    return "<a href='javascript:goPass("+row.id+")'>确认审核</a> <a href='javascript:toDelete("+row.id+")'>详情</a>";
                }}
        ]]
    });
});


//设置审核通过
function goPass(id) {
//发送异步请求   state=1 表示审核通过
    $.post("updatePassState",{"hid":id,"state":1},function(data){
        if(data.result>0) {
            $('#dg').datagrid('reload');  //刷新datagrid
        }else{
            $.messager.alert('友情提示','审核失败，请联系管理员:13260601227!','info');
        }
    },"json");
}

function searchUser() {

    var inputName =$("#inputName").val();
    var inputTel = $("#inputTel").val();
    $("#dg").datagrid("load",{

        name:inputName,
        telephone:inputTel
    });
}

