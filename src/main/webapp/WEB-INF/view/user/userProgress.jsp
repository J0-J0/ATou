<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/resources/common/defaultTag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/resources/common/defaultJsCss.jsp"%>
<link rel="stylesheet" href="${ctx }/resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/multiselect.js"></script>
<script type="text/javascript" src="${ctx}/resources/businessJs/userProgress.js"></script>
<title>编辑课程</title>
</head>
<body>
<jsp:include page="/WEB-INF/resources/common/header.jsp" />

<br/>
<br/>

	<div class="layui-main">
		<div class="layui-form" style="z-index: 999">
			<!-- 节点树 -->
			<div style="float: left; width: 25%">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<!-- 展示，及操作页 -->
			<div style="float: left; width: 70%">
			 <div style="margin:20px auto auto auto;">
				<div class="layui-inline">
					<label class="layui-form-label">可选择</label>
					<select name="from[]" id="multiselect" size="8" multiple="multiple" style="width: 100%;">
						<option value="wrerg" name="notHave">wefwgr</option>
						<option value="ef" name="notHave">wefwgr</option>
						<option value="ef" name="notHave">wefwgr</option>
					</select>
				</div>
				<div class="layui-inline site-text">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p>
						<button class="layui-btn layui-btn-normal layui-btn-small" id="multiselect_rightAll">
							<i class="layui-icon">>></i>
						</button>
					</p>
					<p>
						<button class="layui-btn layui-btn-normal layui-btn-small" id="multiselect_rightSelected">
							<i class="layui-icon">&nbsp;>&nbsp;</i>
						</button>
					</p>
					<p>
						<button class="layui-btn layui-btn-normal layui-btn-small" id="multiselect_leftSelected">
							<i class="layui-icon">&nbsp;&lt;&nbsp;</i>
						</button>
					</p>
					<p>
						<button class="layui-btn layui-btn-normal layui-btn-small" id="multiselect_leftAll">
							<i class="layui-icon">&lt;&lt;</i>
						</button>
					</p>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">已选择</label>
					<select name="to[]" id="multiselect_to" size="8" multiple="multiple" style="width: 100%;">
						<option value="weefr" name="alreadyHave">qwefrgb</option>
						<option value="ef" name="notHave">wefwgr</option>
						<option value="ef" name="notHave">wefwgr</option>
						<option value="ef" name="notHave">wefwgr</option>
					</select>
				</div>
				<div style="margin: 20px auto auto 550px;">
					<button class="layui-btn" id="submitButton">确定提交</button>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>