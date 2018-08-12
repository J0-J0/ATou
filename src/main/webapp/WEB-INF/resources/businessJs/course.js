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
//			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//			zTree.addNodes(treeNode, {
//				id : (100 + newCount),
//				pId : treeNode.id,
//				name : "new node" + (newCount++)
//			});
			$("#title").val("new index");
			$("#title").focus();
			$("#title").attr("name", treeNode.tId);
			$("#title").attr("operationType", "save");
			return false;
		});
	}
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
};

var zTree;
$(document).ready(function() {
	zTree = $.fn.zTree.init($("#treeDemo"), setting, getAllCourseInfo());
	// zTree = $.fn.zTree.init($("#treeDemo"), setting, null);
	zTree.expandAll(true);
});

/** ************************** 其他函数 ********************************* */
function updateCourse() {
	var treeId = $("#title").attr("name");
	var treeNode = zTree.getNodeByTId(treeId);
	var url = "/atou/atouCourse/saveOrUpdateCourse";
	var param = {
		'treeId' : treeNode.id,
		'treeParentId' : treeNode.parentId,
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
		url : "/atou/atouCourse/allNodeInfo",
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