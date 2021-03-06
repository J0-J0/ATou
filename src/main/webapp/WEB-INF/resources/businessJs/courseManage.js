var table = null;
layui.use('table', function() {
	table = layui.table;

	// 监听工具条
	table.on('tool(demo)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			layer.msg('id：' + data.id + ' 的查看操作');
		} else if (obj.event === 'del') {
			layer.confirm('对应目录信息会丢失，后果自负', function(index) {
				layer.close(index);
				deleteCourse(data.id);
				reload();
			});
		} else if (obj.event === 'edit') {
			updateCourse(data.id);
		}
	});
});

/**
 * 更新或新增课程，一个小输入框
 * 
 * @param id
 * @returns
 */
function updateCourse(id) {
	layer.open({
		type : 2,
		title : 'update',
		shadeClose : true,
		shade : false,
		maxmin : true, // 开启最大化最小化按钮
		area : [ '600px', '600px' ],
		content : '/atou/atouCourse/updateCourse/' + id,
		end : function() {
			reload();
		}
	});
}

/**
 * 删除
 * 
 * @param id
 * @returns
 */
function deleteCourse(id) {
	$.ajax({
		type : "post",
		url : "/atou/atouCourse/deleteCourse",
		async : false,
		cache : false,
		data:{"id":id}
	}).done(function(data, textStatus, jqXHR) {
		var jsonData = JSON.parse(data);
		layer.msg(jsonData.message);
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("http请求错误：" + textStatus + "\r\n" + errorThrown);
	});
}

/**
 * 刷新
 * 
 * @returns
 */
function reload() {
	// 执行重载
	table.reload('course');
}