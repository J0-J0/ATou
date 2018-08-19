var zTree;
$(document).ready(function() {
	zTree = $.fn.zTree.init($("#treeDemo"), setting, getAllCourseInfo());
	// zTree = $.fn.zTree.init($("#treeDemo"), setting, null);
	zTree.expandAll(true);
});

var setting = {
	view : {
		selectedMulti : false
	},
	edit : {
		enable : true,
		editNameSelectAll : true,
		removeTitle : "删除",
		showRemoveBtn : false,
		showRenameBtn : false
	},
	data : {
		key : {
			name : "title"
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		}
	},
	callback : {
		onClick : zTreeOnClick
	}
};

/**
 * 点击事件
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function zTreeOnClick(event, treeId, treeNode) {
	$("#title").val(treeNode.title);
	$("#title").attr("name", treeNode.tId);
	$("#title").attr("operationType", "update");
	ue.setContent("");
	var parentId = treeNode.parentId;
	if (parentId == "0") {
		layer.msg("选中的是课程，只能改名字哦~");
		return;
	}
	$.ajax({
		type : "post",
		url : "/atou/atouCourse/getCourseIndexContentInfo",
		data:{
			"courseId":treeNode.courseId,
			"indexId":treeNode.id
		}
	}).done(function(data, textStatus, jqXHR) {
		var jsonData = JSON.parse(data);
		if (jsonData.data == null || jsonData.data == undefined) {
			return;
		}
		var content = jsonData.data.content;
		if (isEmpty(content)) {
			return;
		}
		ue.setContent(content);
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("http请求错误：" + textStatus + "\r\n" + errorThrown);
	});
}


/** ************************** 其他函数 ********************************* */
/**
 * 获取树节点，其实可以交给z-tree去获取
 * 
 * @returns
 */
function getAllCourseInfo() {
	var zNodes = null;
	$.ajax({
		type : "get",
		url : "/atou/atouCourse/allIndexInfo",
		dataType : 'json',
		async : false,
		cache : false
	}).done(function(data, textStatus, jqXHR) {
		zNodes = data.data;
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("http请求错误：" + textStatus + "\r\n" + errorThrown);
	});
	return zNodes;
}


