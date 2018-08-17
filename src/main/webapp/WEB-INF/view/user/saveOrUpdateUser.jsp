<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/resources/common/defaultTag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/resources/common/defaultJsCss.jsp"%>
</head>
<body>
<br/>
<br/>
<div class="layui-main">
	<span style="margin-left: 110px; color:red;">${message}</span>
	<br/>
	<form class="layui-form" action="${ctx}/atouUser/saveOrUpdateUser" method="post">
		<input type="hidden" name = "id" value="${user.id}"/>
		<input type="hidden" name = "operationType" value="${operationType}"/>
		<div class="layui-form-item">
			<label class="layui-form-label">昵称</label>
			<div class="layui-input-block">
				<input type="text" name="nickname" value="${user.nickname}" autocomplete="off" placeholder="请输入标题" class="layui-input" style="width: 350px;">
			</div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit>立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
	</form>
</div>
</body>
</html>