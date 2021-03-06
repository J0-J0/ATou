var zTree;
var ue;
$(document).ready(function() {
	zTree = $.fn.zTree.init($("#treeDemo"), setting, getAllCourseInfo());
	// zTree = $.fn.zTree.init($("#treeDemo"), setting, null);
	zTree.expandAll(true);
    ue = UE.getEditor('container');
});

var setting = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	edit : {
		enable : true,
		editNameSelectAll : true,
		removeTitle : "删除",
		showRemoveBtn : true,
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
		onClick : zTreeOnClick,
		onRemove : zTreeOnRemove,

		beforeRemove : zTreeBeforeRemove,
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

/**
 * 删除前跳弹出框，需要确认
 * 
 * @param treeId
 * @param treeNode
 * @returns
 */
function zTreeBeforeRemove(treeId, treeNode) {
	return confirm("要删除吗");
}
/**
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function zTreeOnRemove(event, treeId, treeNode) {
	var url = "/atou/atouCourse/deleteCourseIndex";
	var param = {
		'treeId' : treeNode.id,
		'treeParentId' : treeNode.parentId
	};
	simulatePostForm(url, param);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.tId);
	if (btn) {
		btn.bind("click", function() {
			$("#title").val("new index");
			$("#title").focus();
			$("#title").attr("name", treeNode.tId);
			$("#title").attr("operationType", "save");
			ue.setContent("");
			return false;
		});
	}
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
};


/** ************************** 其他函数 ********************************* */
function updateCourse() {
	var treeId = $("#title").attr("name");
	var treeNode = zTree.getNodeByTId(treeId);
	var url = "/atou/atouCourse/saveOrUpdateCourseIndex";
	var courseId = treeNode.courseId;
	if (isEmpty(courseId)) {
		courseId = treeNode.id;
	}
	var content = ue.getContent();
	if (isEmpty(content)) {
		content = "";
	}
	var param = {
		'treeId' : treeNode.id,
		'treeParentId' : treeNode.parentId,
		'courseId': courseId,
		'content': content,
		'newTitle' : $("#title").val(),
		'operationType' : $("#title").attr("operationType"),
	};
	simulatePostForm(url, param);
}
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


