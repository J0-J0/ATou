<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/resources/common/defaultTag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/resources/jQuery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/resources/businessJs/common.js"></script>
<link href="${ctx}/resources/layui/css/layui.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/resources/businessJs/course.js"></script>
<title>编辑课程</title>
</head>
<body>
<jsp:include page="/WEB-INF/resources/common/header.jsp" />

<br/>
<br/>
<c:if test="${not empty message }">
<span>${message}</span>
<%session.removeAttribute("message");%>
</c:if>

	<div class="layui-main">
		<div class="layui-form" style="z-index: 999">
			<!-- 节点树 -->
			<div style="float: left; width: 25%">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<!-- 展示，及操作页 -->
			<div style="float: left; width: 70%">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">标题</label>
						<div class="layui-input-inline">
							<input type="text" id="title" name="" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<div class="layui-input-inline">
							<button class="layui-btn" type="button" onclick="updateCourse()">提交</button>
						</div>
					</div>
				</div>
				<jsp:include page="/WEB-INF/resources/common/ueEditor.jsp" />
			</div>
		</div>
	</div>
</body>
</html>