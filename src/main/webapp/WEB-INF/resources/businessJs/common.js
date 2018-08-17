/**
 * 存些都会用到的方法，变量
 */

// 加载组件
var layer = null;
layui.use([ 'form', 'layer' ], function() {
	var form = layui.form;
	layer = layui.layer;
});
layui.use('element', function() {
});

/**
 * form表单提交本页面打开
 * 
 * @param url
 * @param params
 */
function simulatePostForm(url, params) {
	var form = $("<form method='post'></form>");
	var input;
	form.attr({
		"action" : url
	});
	$.each(params, function(key, value) {
		input = $("<input type='hidden'>");
		input.attr({
			"name" : key
		});
		input.val(value);
		form.append(input);
	});
	$(document.body).append(form);
	form.submit();
}

/**
 * 判断字符是否为空的方法
 * 
 * @param obj
 * @returns
 */
function isEmpty(obj) {
	if (typeof obj == "undefined" || obj == null || obj == "") {
		return true;
	} else {
		return false;
	}
}

/**
 * 
 * @param obj
 * @returns
 */
function isNotEmpty(obj) {
	if (typeof obj == "undefined" || obj == null || obj == "") {
		return false;
	} else {
		return true;
	}
}