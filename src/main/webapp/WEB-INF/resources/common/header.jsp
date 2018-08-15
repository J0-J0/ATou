<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="layui-header header header-demo">
	<ul class="layui-nav" lay-filter="">
		<li class="layui-nav-item" style="margin-left: 150px;">
			<a href="javascript:;">课程</a>
			<dl class="layui-nav-child">
				<!-- 二级菜单 -->
				<dd>
					<a href="${ctx}/atou/atouCourse/manage">课程管理</a>
				</dd>
				<dd>
					<a href="${ctx}/atou/atouCourse/index">课程内容管理</a>
				</dd>
			</dl>
		</li>
		<li class="layui-nav-item" style="margin-left: 150px;">
			<a href="${ctx}/atou/atouUser/manage">学员管理</a>
		</li>
	</ul>
</div>