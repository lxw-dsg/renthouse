<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>

  <script language="JavaScript" type="text/javascript">
    $(function () {

      //加载类型
      $.post("getTypeData",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
          //创建option
          var optioncode = $("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#type_id").append(optioncode);
        }

        //设置类型的选中项
        $("#type_id").val(${condition.tid});
      },"json");

      //加载区域

      $.post("getDistrictData",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
          //创建option
          var optioncode = $("<option value="+data[i].id+">"+data[i].name+"</option>");
          $("#district_id").append(optioncode);
        }

        //设置区域的选中项
        $("#district_id").val(${condition.did});
        //加载对应的区域街道
        loadStreet();
      },"json");


      //二级联动，给区域下拉框添加选项改变事件
      $("#district_id").change(function () {

        loadStreet();
      });


    });

    function loadStreet() {
      //选取当前区域选项的id
      var did = $("#district_id").val();
      //清空原有选项
      $("#street_id>option:gt(0)").remove();
      //异步加载街道
     if (did!=''){
       $.post("getStreetData",{"did":did},function (data) {
         for (var i = 0; i <data.length ; i++) {
           //创建option
           var optioncode = $("<option value="+data[i].id+">"+data[i].name+"</option>");
           $("#street_id").append(optioncode);
         }

         //设置街道选中项
         $("#street_id").val(${condition.sid});
       },"json");
     }
    }

    //实现分页跳转
    function goToPage(pageNum) {
      if (pageNum>${pageInfo.pages}){
        pageNum = ${pageInfo.pages};
      }
      //设置页码
      $("#savePage").val(pageNum);
      //提交表单
      $("#myform").submit();
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id="myform" method="post" action="/page/browseHouse">
     <%--隐藏域--%>
    <input type="hidden" name="page" id="savePage">
    标题:<input type="text" name="title" value="${condition.title}">
    区域:<select id="district_id" name="did"><option value="">请选择</option></select>
    街道:<select id="street_id" name="sid"><option value="">请选择</option></select>
    类型:<select id="type_id" name="tid"><option value="">请选择</option></select>
    价格:<input type="text" size="10" value="${condition.startPrice}" name="startPrice">-<input value="${searchCondition.endPrice}" size="10"  type="text" name="endPrice">
    <input type="submit" value="搜索" >
  </FORM>
</DL>
</DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <TR>
    <c:forEach items="${pageInfo.list}" var="house">
      <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${house.path}" width="100" height="75" alt=""></a></span></TD>
      <TD>
        <DL>
          <DT><A href="details.htm" target="_blank">${house.title}</A></DT>
          <DD>${house.dname}-${house.sname},${house.floorage}平米<BR>联系方式：${house.contact} </DD></DL></TD>
      <TD class=house-type>${house.tname}</TD>
      <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
  </TR>
  </c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goToPage(1)">首页</A></LI>
  <LI><A href="javascript:goToPage(${pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goToPage(${pageInfo.pageNum+1})">下一页</A></LI>
  <LI><A href="javascript:goToPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
