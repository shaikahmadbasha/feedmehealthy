Common = {
	failed : function(data) {
		console.log("Ajax call failed " + data);
	}
};

AjaxCall = {

	getJSON : function(formData, url, ajaxSuccessCallBack, ajaxfailureCallBack) {
		$.ajax({
			url : url,
			type : "GET",
			data : JSON.stringify(formData),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				ajaxSuccessCallBack(data);
			},
			error : function(errorThrown) {
				ajaxfailureCallBack(errorThrown);
			}
		});
	},

	postJSON : function(formData, url, ajaxSuccessCallBack, ajaxfailureCallBack) {
		$.ajax({
			url : url,
			type : "POST",
			data : JSON.stringify(formData),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				ajaxSuccessCallBack(data);
			},
			error : function(errorThrown) {
				ajaxfailureCallBack(errorThrown);
			}
		});
	},

	postJSONReq : function(formData, url, ajaxSuccessCallBack,
			ajaxfailureCallBack) {
		$.ajax({
			url : url,
			type : "POST",
			data : formData,
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				ajaxSuccessCallBack(data);
			},
			error : function(errorThrown) {
				ajaxfailureCallBack(errorThrown);
			}
		});
	},

	post : function(formData, url, ajaxSuccessCallBack, ajaxfailureCallBack) {
		$.ajax({
			url : url,
			type : "POST",
			data : JSON.stringify(formData),
			success : function(data) {
				ajaxSuccessCallBack(data);
			},
			error : function(errorThrown) {
				ajaxfailureCallBack(errorThrown);
			}
		});
	}

};

PostAction = {

	postAction : function() {

		var data = $('#request').val();
		var requestUrl = $('#path').val();

		// check request input is empty or not
		if (null == requestUrl && " " == requestUrl) {
			AjaxCall.post(data, requestUrl, PostAction.prosessResponse,
					Common.failed);
		} else if ("Others" == requestUrl) {
			var requestUrl = $('#textPath').val();
			AjaxCall.postJSONReq(data, requestUrl, PostAction.prosessResponse,
					Common.failed);
		} else {
			AjaxCall.postJSONReq(data, requestUrl, PostAction.prosessResponse,
					Common.failed);
		}

	},

	prosessResponse : function(data) {
		if (data.statusCode == "STATUS_OK") {
			$("#response").html(JSON.stringify(data));
			$("#responseDiv").css("display", "block");
		} else {
			console.log("Error occured " + data.statusCode);
		}
	}

};

function checkpath() {
	var path = document.getElementById("path").value;
	if (path == "Others") {
		$("#pathText").css("display", "block");
	} else {
		$("#pathText").css("display", "none");
	}

	/*
	//alert("path ="+path);
	if(path == "/newbankaccount/login.do") {
	        document.getElementById("request").value = document.getElementById("login").textContent ;
	}
	 */
};
