<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/resources/common/defaultTag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/resources/common/defaultJsCss.jsp"%>
<script type="text/javascript" src="${ctx}/resources/businessJs/userManage.js"></script>
<title>学员管理</title>
</head>
<body>
<jsp:include page="/WEB-INF/resources/common/header.jsp" />
	
	
	<br>
	<br>

	<div class="layui-main">
		<button class="layui-btn" type="button" onclick="updateUser(0)">新增</button>
		<br>
		<table class="layui-table" lay-data="{url:'/atou/atouUser/allUserInfo', cellMinWidth: 80 ,id:'user'}" lay-filter="demo">
		  <thead>
		    <tr>
		      <th lay-data="{field:'id', sort: true, fixed: true}">id</th>
		      <th lay-data="{field:'nickname'}">nickname</th>
		      <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}">操作</th>
		    </tr>
		  </thead>
		</table>
		<script type="text/html" id="barDemo">
          <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">进度</a>
          <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
	</div>
	
	
</body>
</html>