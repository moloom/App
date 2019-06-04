function delfile(id) {
	var modify = document.getElementById("modifyBy").value;
	;
	$.ajax({
		type : "POST", //请求类型
		url : "delFile", //请求的url
		data : {
			id : id,
			modify : modify
		}, //请求参数
		dataType : "json", //ajax接口（请求url）返回的数据类型
		success : function(data) { //data：返回数据（json对象）
			if (data.result == "success") {
				alert("删除成功！");
				$("#uploadfile").show();
				$("#apkFile").html('');
			} else if (data.result == "failed") {
				alert("删除失败！");
			} else if (data.result == "empty") {
				alert("文件不存在！");
			}
		},
		error : function(data) { //当访问时候，404，500 等非200的错误状态码
			alert("请求错误！");
		}
	});
}

$(function() {
	$("#back").on("click", function() {
		history.back(-1);
	});
	//上传APK文件---------------------
	var downloadLink = $("#downloadLink").val();
	var id = $("#id").val();
	var apkFileName = $("#APKFileName").val();
	if (downloadLink == null || downloadLink == "") {
		$("#uploadfile").show();
	} else {
		$("#apkFile").append("<p>" + apkFileName +
			"&nbsp;&nbsp;<a href=\"" + downloadLink + "\" >下载</a> &nbsp;&nbsp;" +
			"<a href=\"javascript:;\" onclick=\"delfile('" + id + "');\">删除</a></p>");
	}
//	{
//		$("#apkFile").append("<p>"+apkFileName+
//							"&nbsp;&nbsp;<a href=\"uplocalfiles"+downloadLink+"\" >下载</a> &nbsp;&nbsp;" +
//							"<a href=\"javascript:;\" onclick=\"delfile('"+id+"');\">删除</a></p>");
//	}
//"?m="+Math.random()+
});