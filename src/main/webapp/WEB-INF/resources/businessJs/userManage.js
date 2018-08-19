var table = null;
layui.use('table', function() {
	table = layui.table;

	// 监听工具条
	table.on('tool(demo)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			window.location.href="/atou/atouUserProgress/"+data.id;
		} else if (obj.event === 'del') {
			layer.confirm('对应用户信息会丢失，后果自负', function(index) {
				layer.close(index);
				deleteUser(data.id);
				reload();
			});
		} else if (obj.event === 'edit') {
			updateUser(data.id);
		}
	});
});

/**
 * 更新或新增课程，一个小输入框
 * 
 * @param id
 * @returns
 */
function updateUser(id) {
	layer.open({
		type : 2,
		title : 'update',
		shadeClose : true,
		shade : false,
		maxmin : true, // 开启最大化最小化按钮
		area : [ '600px', '600px' ],
		content : '/atou/atouUser/updateUser/' + id,
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
function deleteUser(id) {
	$.ajax({
		type : "post",
		url : "/atou/atouUser/deleteUser",
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
	table.reload('user');
}