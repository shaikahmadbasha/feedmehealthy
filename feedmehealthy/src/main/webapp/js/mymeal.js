
/**
 * Global variables.
 */
GlobalVariables = {
	deliveryLocationsJsonData : "",
	menuCategoriesJsonData : "",
	menuItemsJsonData : "",
	foodItems : [],
	offerPercentage : "0",
	minPurchase : 200,
	deliveryCharges : 40
	
};

/**
 * End points.
 */
GlobalEndPoints = {
	imageContext : "images/",
	getDeliveryLocations : "/getDeliveryLocations.do",
	getMenuCategories : "/getMenuCategories.do",
	getMenuItems : "/getMenuItems.do",
	saveOrder : "/submitOrder.do",
	applyOfferCode : "/applyOfferCode.do",
	updateCustDetails : "/updateCustomerDetails.do",
	generateInvoice : "/generateInvoice.do",
	fetchReports : "/getReports.do",
	login : "/login.do",
	getCustomerDetails : "/getCustomerDetails.do",
	forgotPassword : "/forgotPassword.do",
	submitFeedback : "/submitFeedback.do"	
};

/**
 * MyMeal Loading
 */
MyMeal = {

	initDeliveryLocResp : function(responseData) {
		GlobalVariables.deliveryLocationsJsonData = responseData.body;
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		/*
		var options = $("#deliverLocSelect");
		options.empty();
		//options.append($("<option />").val('').text('Deliver to Location'));
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			options.append($("<option />").val(location.locationName).text(location.locationName));
		}
		*/
	
		var deliveryLoc = [];
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			deliveryLoc.push(location.locationName);
		}
		
		$('#deliverLocSelectTxtBox').autocomplete({
                source: deliveryLoc,
                minLength: 0,
                scroll: true,
				change: function (event, ui) {
					MyMeal.populateDeliveryDates('deliverLocSelectTxtBox', 'deliverDateSelect');
				}
            }).focus(function() {
                $(this).autocomplete("search", "");
        });
		
	},
	
	initMenuCategoriesResp : function(responseData) {
		GlobalVariables.menuCategoriesJsonData = responseData.body;
		MyMeal.renderMenuCategories();
	},
	
	initMenuItemsResp : function(responseData) {
		GlobalVariables.menuItemsJsonData = responseData.body;
		MyMeal.viewItems("ALL");
	},
	
	reset : function() {
	},

	/*
	locationNameById : function(locationId) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var locationName = '';
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationId == locationId) {
				locationName = location.locationName;
				break;
			}
		}
		return locationName;
	},

	locationTypeById : function(locationId) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var locationType = '';
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationId == locationId) {
				locationType = location.locationType;
				break;
			}
		}
		return locationType;
	},
	*/

	locationTypeByName : function(locationName) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var locationType = 'Others';
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationName == locationName) {
				locationType = location.locationType;
				break;
			}
		}
		return locationType;
	},
	
	locationNameByName : function(locationName) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var locationNameTemp = 'Others';
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationName == locationName) {
				locationNameTemp = location.locationName;
				break;
			}
		}
		return locationNameTemp;
	},
	
	locationIdByName : function(locationName) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var locationIdTemp = '12'; 
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationName == locationName) {
				locationIdTemp = location.locationId;
				break;
			}
		}
		return locationIdTemp;
	},
	
	populateDeliveryDates : function(firstid, secid) {
		var locationType = MyMeal.locationTypeByName($('#deliverLocSelectTxtBox').val());
		if ("Appartment" == locationType){
			$("#dellocfreetxtmodlblid").html('<h4><i class="fa fa-building  fa-lg" style="padding-right:10px"></i>Block and Flat No</h4>');
			$("#dellocfreetxtmod").attr("placeholder","Block & Flat number");
		} else if("Others" == locationType) {
			$("#dellocfreetxtmodlblid").html('<h4><i class="fa fa-building  fa-lg" style="padding-right:10px"></i>Address</h4>');
			$("#dellocfreetxtmod").attr("placeholder","Office/Appt Name, Location");
		}
		
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var options = $("#"+secid);
		options.empty();
		options.append($("<option />").val('').text('Select Date'));
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationName == MyMeal.locationNameByName($("#" +firstid).val())) {
				var locationDates =  location.locationDates;
				for(var j=0; j<locationDates.length; j++) {
					options.append($("<option />").val(locationDates[j]).text(locationDates[j]));					
				}
			}
		}

		var toptions = $("#deliverTimeSelect");
		toptions.empty();
		toptions.append($("<option />").val('').text('Select Time'));
		
		toptions = $("#deliverTimeSelectMenu");
		toptions.empty();
		toptions.append($("<option />").val('').text('Select Time'));
	},
	
	populateDeliveryTimes : function(firstid, cur, secid) {
		var locations = GlobalVariables.deliveryLocationsJsonData.deliveryLocations;
		var options = $("#" +secid);
		options.empty();
		options.append($("<option />").val('').text('Select Time'));
		for (var i = 0; i < locations.length; i++) {
			var location = locations[i];
			if(location.locationName == MyMeal.locationNameByName($("#" +firstid).val())) {
				var selDate = 	$("#" + cur + " option:selected").text();
				var locationTimes =  location.locationTimes;
				for(var j=0; j<locationTimes.length; j++) {
					var locDate = locationTimes[j].locationDate;
					var locDateTimes = locationTimes[j].locationTimes;
					if(selDate == locDate) {
						for(k=0;k<locDateTimes.length;k++) {
							options.append($("<option />").val(locDateTimes[k]).text(locDateTimes[k]));					
						}
					}
				}
			}
		}
	},
	
	renderMenuCategories : function () {
		$("#menucategories").empty();
		var menuCategories = GlobalVariables.menuCategoriesJsonData.menuCategories;
		for (var i = 0; i < menuCategories.length; i++) {
			$("#menucategories").append('<li role="menucategories"><a href="javascript:void(0)" onclick="MyMeal.viewItems(' + "'" + menuCategories[i].categoryName + "'" +');MyMeal.gotoMenu();MyMeal.toggleMenu();" aria-controls="menucategories" role="tab" data-toggle="tab">' + menuCategories[i].categoryName + '</a></li>');
		}
		
	},
	
	toggleMenu : function () {
		$("#sidebarparent").removeClass('active');
		
	},
	viewItems : function (category) {
		//alert("view items invoked " + category);
		var menuItems = GlobalVariables.menuItemsJsonData.menuItems;
		var itm = '';
		for (var i = 0; i < menuItems.length; i++) {
			var row = menuItems[i];
			//alert("row " + row);
			//alert("row.categoryNames " + row.categoryNames);
			if( (row.categoryNames.toUpperCase().indexOf(category.toUpperCase()) != -1) || (row.categoryNames.toUpperCase().indexOf("ALL")) ) {
		       itm = itm 
						+	'<div class="col-sm-4" style="padding-bottom:8px !important;">'
						+		'<div class="card-8" style="width:100%;">'
						+			'<img src="' + GlobalEndPoints.imageContext + row.imageName + '" style="width:100%;height:170px;">'
						+			'<div>'
						+			  '<p style="height:40px;text-align:center">' + row.itemName + '</p>'
						+			  '<p>' + row.description1 + '</p>'
						+			'</div>'
						+			'<div class="row">'
						+				'<div class="col-xs-5"><p>Rs. ' + row.price + '</p></div>'
						+				'<div class="col-xs-5"> <span class="itmcountpad" id="itemCount' + row.itemId + '">0</span> </div>'
						+			'</div>'
						+			'<div class="row visible-xs" style="text-align:right;padding-right:15px"><a href="#cart" class="gotocart">Go to Cart</a></div>'

						+		'</div>'
						+	'</div>';
		    } 
		}
		
		$("#menuItemsContent").html(itm);
		
		$("#menuItems").addClass('active');
		
		//$('html, body').animate({scrollTop: $("#menu").offset().top}, 500);
		//$("#sidebarparent").toggleClass('active');
		
		
	},
	
	gotoMenu : function() {
		$("#menu").css("display", "block");
		$('html, body').animate({scrollTop: $("#menu").offset().top}, 500);
		//$("#sidebarparent").toggleClass('active');
		ga('send','event','Location - None','click','Menu Items');
	},
	
	gotoDiv : function(divid) {
		$("#" + divid).css("display", "block");
		$('html, body').animate({scrollTop: $("#" + divid).offset().top}, 500);	
	},
	
	applyOfferCode : function() {
		$("#applyoffercdbtnspnr").removeClass('hideSpinner');
		$("#applyoffercdbtn").addClass('disabled');

		applyOfferCodeReq = {};
		applyOfferCodeReq.foodItems = GlobalVariables.foodItems;
		applyOfferCodeReq.offerCode = $("#offercode").val();
		applyOfferCodeReq.amount = $("#ordtotbfdisc").html();
		var applyOfferCodeJsonReq = JSON.stringify(applyOfferCodeReq);
		
		if(Stubs.isStubEnabled("applyOfferCode")) {
			MyMeal.offerCodeApplied(Stubs.offerCodeAppliedStubData);
		} else {
			AjaxCall.postJSONReq(applyOfferCodeJsonReq, GlobalEndPoints.applyOfferCode, MyMeal.offerCodeApplied, Common.failed);
		}	
	},
	
	offerCodeApplied : function(data) {
		$("#applyoffercdbtnspnr").addClass('hideSpinner');
		$("#applyoffercdbtn").removeClass('disabled');
		
		var offrResp = data.body;
		if(offrResp.offerStatus == "valid") {
			var offerPercentage = parseInt(offrResp.offerPercentage);
			GlobalVariables.offerPercentage = offerPercentage;
			var curtotamt = $("#ordtotbfdisc").html();
			var ordtotdisc = ((curtotamt * offerPercentage) / 100 );
			if( (curtotamt - ordtotdisc) >= GlobalVariables.minPurchase ) {
				$("#deliveryChargesId").html(0);
			} else {
				$("#deliveryChargesId").html(GlobalVariables.deliveryCharges);
			}
			var deliveryCharges = parseInt($("#deliveryChargesId").html());
			
			var ordtotaftrdisc = curtotamt - ordtotdisc + deliveryCharges;
			$("#ordtotdisc").html(ordtotdisc);
			$("#ordtotaftrdisc").html(ordtotaftrdisc);
			$("#applyoffermsg").html("    Offer applied");
			$("#applyoffermsg").css("color", "rgb(15, 73, 115)");
		} else {
			$("#applyoffermsg").html(offrResp.offerMessage);
			$("#applyoffermsg").css("color", "red");
		}
	},
	
	plusItem : function(itemId, itemName, price) {
		var found = false;

		var cartLiId = "cart" + itemId;
		var cartLiItmNameId = "cartitmnme" + itemId;
		var cartLiItmQty = "cartitmqty" + itemId;
		var cartLiItmPrice = "cartitmprice" + itemId;
		var cartLiItmTotPrice = "carttimtotprice" + itemId;
		var menuItemsItmCount = "itemCount" + itemId;
		
		for (var i = 0; i < GlobalVariables.foodItems.length; i++) {
			var orderItem = GlobalVariables.foodItems[i];
			
			if(itemName == orderItem.itemName) {
				orderItem.qty = orderItem.qty + 1;
				orderItem.price = orderItem.price + price;
				
				$("#" + cartLiItmQty).html(orderItem.qty);
				$("#" + cartLiItmTotPrice).html(orderItem.price);
				$("#" + menuItemsItmCount).html(orderItem.qty);
				
				var amt = parseInt($("#ordtotbfdisc").html()) + price;
				var percentage = parseInt(GlobalVariables.offerPercentage);
				var discount = (amt * percentage)/100;
				
				if( (amt-discount) >= GlobalVariables.minPurchase ) {
					$("#deliveryChargesId").html(0);
				} else {
					$("#deliveryChargesId").html(GlobalVariables.deliveryCharges);
				}
				var deliveryCharges = parseInt($("#deliveryChargesId").html());
					
				$("#ordtotbfdisc").html(amt);
				$("#ordtotdisc").html(discount);
				$("#ordtotaftrdisc").html(amt-discount + deliveryCharges);
				
				found = true;
				break;
			}
		}
		
		if(found == false) {
			var orderItem = {};
			orderItem.itemId = itemId;
			orderItem.itemName = itemName;
			orderItem.qty = 1;
			orderItem.price = price;
			GlobalVariables.foodItems.push(orderItem);
			
			$("#" + menuItemsItmCount).html(orderItem.qty);
		
			var amt = parseInt($("#ordtotbfdisc").html()) + price;
			var percentage = parseInt(GlobalVariables.offerPercentage);
			var discount = (amt * percentage)/100;
			if( (amt-discount) >= GlobalVariables.minPurchase ) {
				$("#deliveryChargesId").html(0);
			} else {
				$("#deliveryChargesId").html(GlobalVariables.deliveryCharges);
			}
			var deliveryCharges = parseInt($("#deliveryChargesId").html());
			
			$("#ordtotbfdisc").html(amt);
			$("#ordtotdisc").html(discount);
			$("#ordtotaftrdisc").html(amt-discount + deliveryCharges);
			
			var itemhtml = 			
						 '<li id = "' + cartLiId +'" class="list-group-item cartdata" style="margin: 5px 0px -10px 0px; padding: 10px 10px 0px 10px; ">'
						+ '<i class="fa fa-times-circle fa-lg pull-right" style="z-index:501; cursor: pointer; cursor: hand;margin-right:20px" onclick="MyMeal.removeItem(' + "'" + itemId + "',"  + "'" + itemName + "',"  + price + ')"></i>'
						+ '<span id="'+ cartLiItmNameId +'">' + itemName + '</span>'
						+'<p style="margin-top: 7px; vertical-align: middle;">'
						+	'<span style="padding: 10px 10px;">'
						+		'<span id="'+ cartLiItmQty +'">' + orderItem.qty + '</span>'
						+	'</span>'
						+	'<span>'
						+		'<i class="fa fa-times fa-fw" style="padding: 10px 10px;"></i>'
						+		'<i class="fa fa-inr " style="padding-left:10px"></i>'
						+		'<span id="'+ cartLiItmPrice +'">' + orderItem.price + '</span>'
						+	'</span>'
						+	'<span class="pull-right" style="padding: 4px 10px;"> <i class="fa fa-inr "></i>'
						+		'<span id="'+ cartLiItmTotPrice +'">' + orderItem.price + '</span>'
						+	'</span>'
						+  '</p>'
						+ '</li>';
			
			$("#cartitems").append(itemhtml);
			$( "#cart" ).css("display", "block");
			$( "#alertsdivid" ).css("display", "block");
		}
		
		/*
		for (var i = 0; i < GlobalVariables.foodItems.length; i++) {
			var orderItem = GlobalVariables.foodItems[i];
			alert(orderItem.itemName + " -- " + orderItem.qty + "--" + orderItem.price);
		}
		*/
	},
	
	minusItem : function(itemId, itemName, price) {
		var found = false;

		var cartLiId = "cart" + itemId;
		var cartLiItmNameId = "cartitmnme" + itemId;
		var cartLiItmQty = "cartitmqty" + itemId;
		var cartLiItmPrice = "cartitmprice" + itemId;
		var cartLiItmTotPrice = "carttimtotprice" + itemId;
		var menuItemsItmCount = "itemCount" + itemId;
				
		for (var i = 0; i < GlobalVariables.foodItems.length; i++) {
			var orderItem = GlobalVariables.foodItems[i];
			
			if(itemName == orderItem.itemName) {
				if(orderItem.qty >0) {
					orderItem.qty = orderItem.qty - 1;
					orderItem.price = orderItem.price - price;
					
					$("#" + cartLiItmQty).html(orderItem.qty);
					$("#" + cartLiItmTotPrice).html(orderItem.price);
					$("#" + menuItemsItmCount).html(orderItem.qty);
					
					GlobalVariables.offerPercentage = 0;
					var amt = parseInt($("#ordtotbfdisc").html()) - price;
					var percentage = parseInt(GlobalVariables.offerPercentage);
					var discount = (amt * percentage)/100;
					if( (amt-discount) >= GlobalVariables.minPurchase ) {
						$("#deliveryChargesId").html(0);
					} else {
						$("#deliveryChargesId").html(GlobalVariables.deliveryCharges);
					}
					var deliveryCharges = parseInt($("#deliveryChargesId").html());
					
					$("#ordtotbfdisc").html(amt);
					$("#ordtotdisc").html(discount);
					$("#ordtotaftrdisc").html(amt-discount + deliveryCharges);
					
					
					if(orderItem.qty ==0) {
						//alert("its zero");
						GlobalVariables.foodItems.splice(i, 1); 
						$("#"+cartLiId).remove();
						$("#deliveryChargesId").html(0);
						$("#ordtotaftrdisc").html(0);
					}
					
					$("#applyoffermsg").html("Please Re-Apply the Offer Code");
					$("#applyoffermsg").css("color", "red");
			
				}
				
				found = true;
				break;
			}
		}
	
		/*
		if(GlobalVariables.foodItems == 0) {
			$( "#cart" ).css("display", "none");
		}*/
		/*
		for (var i = 0; i < GlobalVariables.foodItems.length; i++) {
			var orderItem = GlobalVariables.foodItems[i];
			alert(orderItem.itemName + " -- " + orderItem.qty + "--" + orderItem.price);
		}
		*/
	},
	
	removeItem : function(itemId, itemName, price) {
		var cartLiId = "cart" + itemId;
		var menuItemsItmCount = "itemCount" + itemId;
		
		var removedItmsTotalQty = 0;
		for(var i=0;i<GlobalVariables.foodItems.length; i++) {
			var item = 	GlobalVariables.foodItems[i];
			if(item.itemName == itemName) {
				removedItmsTotalQty = item.qty;
				GlobalVariables.foodItems.splice(i, 1); 
				break;
			}
		}
		
	    var removedItmsTotalAmt = removedItmsTotalQty * price;
	   
		GlobalVariables.offerPercentage = 0;
	   
		var amt = parseInt($("#ordtotbfdisc").html()) - removedItmsTotalAmt;
		var percentage = parseInt(GlobalVariables.offerPercentage);
		var discount = (amt * percentage)/100;
		var discount = (amt * percentage)/100;
		if( (amt-discount) >= GlobalVariables.minPurchase ) {
			$("#deliveryChargesId").html(0);
		} else {
			$("#deliveryChargesId").html(GlobalVariables.deliveryCharges);
		}
		var deliveryCharges = parseInt($("#deliveryChargesId").html());
		
		$("#ordtotbfdisc").html(amt);
		$("#ordtotdisc").html(discount);
		$("#ordtotaftrdisc").html(amt-discount + deliveryCharges);

		$("#applyoffermsg").html("Please Re-Apply the Offer Code");
		$("#applyoffermsg").css("color", "red");
					
		//Utils.findAndRemove(GlobalVariables.foodItems, 'itemName', itemName);

		$("#"+cartLiId).remove();
		$("#" + menuItemsItmCount).html("0");
		
		/*
		if(GlobalVariables.foodItems == 0) {
			$( "#cart" ).css("display", "none");
		}
		*/
		
	},
	
	proceedOrder : function() {
		var deliveryTime = $('#deliverTimeSelect').val();
		var deliveryDate = $('#deliverDateSelect').val();
		var locationName = $('#deliverLocSelectTxtBox').val();
		var dellocfreetxtmod = $('#dellocfreetxtmod').val();
		var locationType = MyMeal.locationTypeByName($('#deliverLocSelectTxtBox').val());
		var others = false;
		if ("Appartment" == locationType || "Others" == locationType){
			others = true;
		}
		
		if( !deliveryTime || !deliveryDate || !locationName || (others && !dellocfreetxtmod) ) {
			Utils.notify("Select Location, Date, Time", "warning");
			this.gotoDiv("main-location-container");
		} else if(GlobalVariables.foodItems.length == 0) {
			this.gotoDiv("menu");
			Utils.notify("Please add your food to Cart", "warning");
		} else {
			$('#order-container').css("display","block");
			$('html, body').animate({scrollTop: $("#order-container").offset().top}, 500);
			$('#maincontainer').css("display","none");
			$('#menu').css("display","none");
			ga('send','event','Proceed to Order','click','Proceed to Order');
		}
				
	},
	
	saveOrderProcessed : function(data) {
		$("#submitorderbtn").removeClass('disabled');
		
		var orderid = data.body.orderId;
		console.log("order id is " + orderid);
		
		if(orderid == null || orderid == '') {
			$('#confirmmsg').html("Not Confirmed");
			$("#confirmsrydiv").css("display", "block");
		} else {
			$('#confirmmsg').html("Confirmed");
			$("#cnfrdiv").css("display", "block");
		}
		
		$('#orderid').html(Utils.last4Chars(orderid));
		
		$('html, body').animate({scrollTop: $("#order-container").offset().top}, 500);
		$('#order-confirm-container').css("display","block");
		$('#order-container').css("display","none");
		$('#maincontainer').css("display","none");
		$('#menu').css("display","none");
		$("#submitorderbtnspnr").addClass('hideSpinner');
	},
	
	openSubscribeModal : function () {
		$("#subscribemodal").modal('show'); 
	},
	
	openLoginModal : function () {
		$('#fgpassworddivid').css("display","none");
		$('#logindivid').css("display","block");
		
		$("#signupName").val("");
		$("#signupUserId").val("");
		$("#signupEmail").val("");
		$("#signupPassword").val("");
		$("#signupAddressArea").val("");
		$("#signupAddressFlat").val("");
		$("#signupMsg").html("");
		
		$("#loginUserId").val("");
		$("#loginPassword").val("");
		$("#loginRespMsg").html("");
		
		$('#fgPasswordUserId').val("");
		$('#fgRespMsg').html("");

		$("#loginmodal").modal('show'); 
		
	},
	
	subscribeSuccess : function () {
		
	},
	
	subscribe : function () {
		customer = {};
		customer.name = $("#subName").val();
		customer.phone = $("#subMobile").val();
		customer.email = $("#subEmail").val();
		
		updateCustReq = {}
		updateCustReq.customer = customer;
		
		var updateCustJsonReq = JSON.stringify(updateCustReq);
		
		if(Stubs.isStubEnabled("updateCustDetails")) {
			MyMeal.subscribeSuccess(Stubs.subscribeStubData);
		} else {
			AjaxCall.postJSONReq(updateCustJsonReq, GlobalEndPoints.updateCustDetails, MyMeal.subscribeSuccess, Common.failed);
		}	

	},
	
	getLoginCustomerDetailsSuccess : function (data) {
		var getCustResp = data.body.customerDetails;
		 $('#inputName').val(getCustResp.name);
		 $('#inputMobile').val(getCustResp.phone);
		 $('#inputEmail').val(getCustResp.email);

		//$('#deliverLocSelectTxtBox').val()
		$('#deliverLocSelectTxtBox').val(getCustResp.area);
		$("#dellocfreetxtmod").val(getCustResp.addLine1);
		$("#signuplable").html("<h4 style='padding-right:25px;padding-left:25px;color:white'><span style='cursor: pointer; cursor: hand;'>Welcome " + getCustResp.name + "</span></h4></li>");
		
		MyMeal.populateDeliveryDates('deliverLocSelectTxtBox', 'deliverDateSelect');
		 
		$("#loginbtnspnr").toggleClass('spinner');
		$("#loginbtnspnr").toggleClass('disabled');
		$('#loginmodal').modal('hide');
		Utils.notify("Login success!", "success");
	},
	
	getCustomerDetailsSuccess : function (data) {
		
		
	},
	
	getCustomerDetails : function (phone, callbackFun) {
		
		if(Stubs.isStubEnabled("getCustomerDetails")) {
			callbackFun(Stubs.getCustomerDetailsSuccessData);
		} else {
			AjaxCall.postJSONReq(phone, GlobalEndPoints.getCustomerDetails, callbackFun, Common.failed);
		}
		
	},
	
	loginSuccess : function (data) {
		var loginResp = data.body;
		if(loginResp.status == "success") {
			MyMeal.getCustomerDetails($("#loginUserId").val(), MyMeal.getLoginCustomerDetailsSuccess);
		} else {
			$("#loginRespMsg").html("Did you forgot your password?<br><a href='javascript:void(0)' onclick='MyMeal.openForgotPassword()'>Forgot password</a> ");
			$("#loginRespMsg").css("color", "red");
			
			$("#loginbtnspnr").toggleClass('spinner');
			$("#login").toggleClass('disabled');
		}
	},
	
	login : function () {
		$("#loginbtnspnr").toggleClass('spinner');
		$("#login").toggleClass('disabled');
		
		customer = {};
		customer.phone = $("#loginUserId").val();
		customer.password = $("#loginPassword").val();
				
		loginReq = {}
		loginReq.customer = customer;
		
		var loginJsonReq = JSON.stringify(loginReq);
		
		if(Stubs.isStubEnabled("login")) {
			MyMeal.loginSuccess(Stubs.loginSuccessData);
		} else {
			AjaxCall.postJSONReq(loginJsonReq, GlobalEndPoints.login, MyMeal.loginSuccess, Common.failed);
		}
		
	},

	forgotPasswordSuccess : function (data) {
		var resp = data.body;
		if(resp.fgStatus == "NOT_FOUND") {
			$('#fgRespMsg').html("Sorry we could not Identify you. Please Sign UP!");
		} else if(resp.fgStatus == "EMAIL_SENT") {
			$('#loginmodal').modal('hide');
			Utils.notify("Your password is sent to you Email!", "success");
		}
		$("#fgbtnspnr").toggleClass('spinner');
		$("#fgpassword").toggleClass('disabled');
	},
	
	forgotPassword : function () {
		$("#fgbtnspnr").toggleClass('spinner');
		$("#fgpassword").toggleClass('disabled');
		var fgPassVal = $('#fgPasswordUserId').val();

		if(Stubs.isStubEnabled("forgotPassword")) {
			MyMeal.forgotPasswordSuccess(Stubs.forgotPasswordSuccessData);
		} else {
			AjaxCall.postJSONReq(fgPassVal, GlobalEndPoints.forgotPassword, MyMeal.forgotPasswordSuccess, Common.failed);
		}

	} , 

	openForgotPassword : function () {
		$('#fgpassworddivid').css("display","block");
		$('#logindivid').css("display","none");
	},
	
	signupSuccess : function (data) {
		$("#signupbtnspnr").toggleClass('spinner');
		$("#signup").toggleClass('disabled');
		
		var signupResp = data.body;
		if(signupResp.signupResp == "success") {
			$('#loginmodal').modal('hide');
			Utils.notify("Sign up success!", "success");
		} else {
			$("#signupMsg").html("Sorry some Error!");
			$("#signupMsg").css("color", "red");
		}
	},
	
	signup : function () {
		$("#signupbtnspnr").toggleClass('spinner');
		$("#signup").toggleClass('disabled');

		customer = {};
		customer.name = $("#signupName").val();
		customer.phone = $("#signupUserId").val();
		customer.email = $("#signupEmail").val();
		customer.password = $("#signupPassword").val();
		customer.area = $("#signupAddressArea").val();
		customer.addLine1 = $("#signupAddressFlat").val();
		
		updateCustReq = {}
		updateCustReq.customer = customer;
		
		var updateCustJsonReq = JSON.stringify(updateCustReq);
		
		if(Stubs.isStubEnabled("updateCustDetails")) {
			MyMeal.signupSuccess(Stubs.signupSuccessData);
		} else {
			AjaxCall.postJSONReq(updateCustJsonReq, GlobalEndPoints.updateCustDetails, MyMeal.signupSuccess, Common.failed);
		}
		
	},
	
	generateInvoice : function (orderId) {
		$("#geninvoiceidspnr" + Utils.last4Chars(orderId)).removeClass('hideSpinner');
		var giReqVO = {};
		//giReqVO.orderId = $("#giorderid").val();
		giReqVO.orderId = orderId;
		var giReqVOJson = JSON.stringify(giReqVO);
		AjaxCall.postJSONReq(giReqVOJson, GlobalEndPoints.generateInvoice, MyMeal.generateInvoiceSuccess, Common.failed);
	},
	
	generateInvoiceSuccess : function () {
		//alert("Success...");
		//$("#gisubmitmsg").html("Sent E-Bill to customer");
		$("#geninvoiceidspnr").addClass('hideSpinner');
		
		Utils.notify("Sent Invoice to Customer!", "success");
	},
	
	fetchReports : function () {
		$("#reportbtdspnr").removeClass('hideSpinner');
		var reportReqVO = {};
		reportReqVO.date = $("#reportdate").val();
		var reportReqVOJson = JSON.stringify(reportReqVO);
		if(Stubs.isStubEnabled("fetchReports")) {
			MyMeal.fetchReportsSuccess(Stubs.fetchReportsStubData);
		} else {
			AjaxCall.postJSONReq(reportReqVOJson, GlobalEndPoints.fetchReports, MyMeal.fetchReportsSuccess, Common.failed);
		}
		
	},
	
	fetchReportsSuccess : function(data) {
		$("#reportbtdspnr").addClass('hideSpinner');
		$('#reportdatatable').css("display","block");
		var ordersList = data.body.ordersList;
		if(ordersList) {
		
			for (var i = 0; i < ordersList.length; i++) {
				var order = ordersList[i];
				$('#reportordertable tbody').append('<tr> <td>'+ order.customer.name +'</td> <td><button id="geninvoiceid" type="button" onclick="MyMeal.generateInvoice(\'' + order.orderId+ '\')" class="btn btn-primary">Gen Invoice&nbsp;<i id="geninvoiceidspnr'+Utils.last4Chars(order.orderId)+'" class="fa fa-refresh fa-spin hideSpinner"></i></button></td> </tr>');
			}
		}
	
	},
	
	aboutus : function() {
		$('#maincontainer').css("display","none");
		$('#menu').css("display","none");
		$('#aboutus').css("display","block");
		$('html, body').animate({scrollTop: $("#topstart").offset().top}, 500);
	},
	
	submitFeedbackSuccess : function () {
		
	},
	
	submitFeedback : function () {
		ga('send','event','Submit Feedback','click','Submit Feedback');
		Utils.notify("Your Feedback Updated" , "success");
		var e = jQuery.Event( "click" );
		fm.closeFeedback(e);	

		var feedbackReqVO = {};
		feedbackReqVO.name = $('#feedbackName').val();
		feedbackReqVO.mobile = $('#feedbackMobile').val();
		feedbackReqVO.email = $('#feedbackEmail').val();
		feedbackReqVO.feedback = $('#feedbackComments').val();
				
		var feedbackReqVOJson = JSON.stringify(feedbackReqVO);
		AjaxCall.postJSONReq(feedbackReqVOJson, GlobalEndPoints.submitFeedback, MyMeal.submitFeedbackSuccess, Common.failed);
	}
};






/**
 * All utility methods
 */
Utils = {
	findAndRemove : function(array, property, value) {
	   $.each(array, function(index, result) {
		  if(result[property] == value) {
			  //Remove from array
			  array.splice(index, 1);
		  }    
	   });
	},
	
	notify : function(messaage, type) {
		if("warning" === type) {
			$.notify({message: messaage, icon: 'glyphicon glyphicon-warning-sign'},{type: 'danger'	});
		} else if("success" === type) {
			$.notify({message: messaage, icon: 'glyphicon glyphicon-ok'},{type: 'success'	});
		} else {
			$.notify({message: messaage, icon: 'glyphicon glyphicon-warning-sign'},{type: 'danger'	});
		}
	},
	
	last4Chars : function (str) {
		var last4chars = str;
		
		if(str) {
			last4chars = str.substring(str.length-4);
		}
		
		return last4chars;
	}
}


/**
 * Get Meals Loading pages
 */
MealAjaxInvocations = {
		
	initDeliveryLocations : function() {
		if(Stubs.isStubEnabled("getDeliveryLocations")) {
			MyMeal.initDeliveryLocResp(Stubs.getDeliveryLocationsStubData);
		} else {
			AjaxCall.postJSONReq('', GlobalEndPoints.getDeliveryLocations, MyMeal.initDeliveryLocResp, Common.failed);
		}
	},
		
	initMenuCategories : function() {
		if(Stubs.isStubEnabled("getMenuCategories")) {
			MyMeal.initMenuCategoriesResp(Stubs.getMenuCategoriesStubData);
		} else {
			AjaxCall.postJSONReq('', GlobalEndPoints.getMenuCategories, MyMeal.initMenuCategoriesResp, Common.failed);
		}
	},
	
	initMenuItems : function() {
		if(Stubs.isStubEnabled("getMenuItems")) {
			MyMeal.initMenuItemsResp(Stubs.getMenuItemsStubData);
		} else {
			AjaxCall.postJSONReq('', GlobalEndPoints.getMenuItems, MyMeal.initMenuItemsResp, Common.failed);
		}
	},
	
	saveOrder : function() {
		$("#submitorderbtnspnr").removeClass('hideSpinner');
		$("#submitorderbtn").addClass('disabled');
		
		//alert("save order called");
		customer = {};
		customer.name = $('#inputName').val();
		customer.phone = $('#inputMobile').val();
		customer.email = $('#inputEmail').val();
		
		saveOrderReq = {};
		saveOrderReq.foodItems = GlobalVariables.foodItems;
		saveOrderReq.paymentMode = "COD";
		saveOrderReq.customer = customer;
		//saveOrderReq.discountAmount
		saveOrderReq.deliveryTime = $('#deliverTimeSelect').val();
		saveOrderReq.deliveryDate = $('#deliverDateSelect').val();
		saveOrderReq.locationId = MyMeal.locationIdByName($('#deliverLocSelectTxtBox').val());
		saveOrderReq.locationName = MyMeal.locationNameByName($('#deliverLocSelectTxtBox').val());
				
		var blockflatno = $("#dellocfreetxtmod").val();
		if(blockflatno) {
			saveOrderReq.locationName = saveOrderReq.locationName + " - " + blockflatno;
		}
		
		saveOrderReq.offerCode = $("#offercode").val();
		
		var saveOrderJsonReq = JSON.stringify(saveOrderReq);
		console.log(saveOrderJsonReq);
		ga('send','event','Submit Order','click','Order');
		
		if(Stubs.isStubEnabled("saveOrder")) {
			MyMeal.saveOrderProcessed(Stubs.saveOrderRespStubData);
		} else {
			AjaxCall.postJSONReq(saveOrderJsonReq, GlobalEndPoints.saveOrder, MyMeal.saveOrderProcessed, Common.failed);
		}
				
	},

};

/**
 * Startup Initializations
 */
Initializer = {
		
	initMasterJSON : function() {
		MealAjaxInvocations.initDeliveryLocations();
		MealAjaxInvocations.initMenuCategories();
		MealAjaxInvocations.initMenuItems();
	},

	initOrderFormSubmit : function() {
		$('#orderform').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MealAjaxInvocations.saveOrder();
			}
		});
	},
	
	initDeliveryLocModalSubmit : function() {

		$('#deliveryLocModal').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
			} else {
				e.preventDefault();
				//$('#maincontainer').css("display","none");
				$('#order-container').css("display","none");
				$('#menu').css("display","block");
				$('html, body').animate({scrollTop: $("#menu").offset().top}, 500);
				var locationName = "Location - " + MyMeal.locationNameByName($('#deliverLocSelectTxtBox').val());
				ga('send','event',locationName,'click','Locations');
				
				/*	
				var loc = MyMeal.locationNameById($('#deliverLocSelect').val());
				//alert(" loc is " + loc);
				ga('send', 'pageview', 'index?location=' + loc);
  			    $('#locationModal').modal('toggle');
				
				var $locoptions = $("#deliverLocSelect > option").clone();
				$('#deliverLocSelectMenu').empty();
				$('#deliverLocSelectMenu').append($locoptions);
				$('#deliverLocSelectMenu').val($('#deliverLocSelect').val());

				var $dateoptions = $("#deliverDateSelect > option").clone();
				$('#deliverDateSelectMenu').empty();
				$('#deliverDateSelectMenu').append($dateoptions);
				$('#deliverDateSelectMenu').val($('#deliverDateSelect').val());
				
				var $timeoptions = $("#deliverTimeSelect > option").clone();
				$('#deliverTimeSelectMenu').empty();
				$('#deliverTimeSelectMenu').append($timeoptions);
				$('#deliverTimeSelectMenu').val($('#deliverTimeSelect').val());
				*/
			}
		});

	},
	
	initDeliveryLocNonModalSubmit : function() {
		$('#deliveryLocMenu').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
			}
		});
	},
	initOfferCodeSubmit : function() {
		$('#offerCodeForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.applyOfferCode();
			}
		});
	},
	initGISubmit : function() {
		$('#giform').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.generateInvoice();
			}
		});
		
	},
	
	initSubscribeSubmit : function() {
		$('#subscribeForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.subscribe();
			}
		});
	}, 
	
	initLoginSubmit : function() {
		$('#loginForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.login();
			}
		});
	}, 
	
	initFGpasswordSubmit : function () {
		$('#fgPasswordForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.forgotPassword();
			}
		});
	},
	
	initSignupSubmit : function() {
		$('#signupForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.signup();
			}
		});
	}, 
	
	initReportSearchSubmit : function() {
		$('#reportForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.fetchReports();
			}
		});
	},
	
	initFeedBackForm : function() {
		//set up some minimal options for the feedback_me plugin
		var feedbackHtml = '<div id="feedback-form-container">'
			+ 	'<div style="padding:10px;" >'
			+		'<div style="background-color: white;color:black;padding:20px;border-radius: 4px;">'
			+			'<form role="form" data-toggle="validator" id="feedbackForm" >'
			+				'<div class="form-group">'
			+					'<input type="text" class="form-control" id="feedbackName" placeholder="Name" required />'
			+				'</div>'
			+				'<div class="form-group">'
			+					'<input type="text" class="form-control" id="feedbackMobile" pattern="^[0-9]{10,11}$" placeholder="Mobile" required />'
			+				'</div>'
			+				'<div class="form-group">'
			+					'<input type="email" class="form-control" id="feedbackEmail" placeholder="Email" required />'
			+				'</div>'
			+				'<div class="form-group">'
			+					'<textarea class="form-control" rows="3" id="feedbackComments" required></textarea>'
			+				'</div>'
			+				'<div class="form-group" style="text-align:right">'
			+					'<button id="feedbackbtn" type="submit" data-dismiss="modal" class="btn btn-primary disabled">Send'
			+					'</button>'
			+				'</div>'
			+				'<div></div>'
			+			'</form>'
			+		'</div>'
			+	'<div>'
			+ '</div>';
		fm_options = {
			show_form : false,
			position : "right-top",
			title_label: "Feedback",
			custom_html : feedbackHtml,
			close_on_click_outside : true
		};
	 
		//init feedback_me plugin
		fm.init(fm_options);
		Initializer.initFeedBackFormSubmit();
	},
	
	initFeedBackFormSubmit : function() {
		$('#feedbackForm').validator().on('submit', function (e) {
			if (e.isDefaultPrevented()) {
				
			} else {
				e.preventDefault();
				MyMeal.submitFeedback();
			}
		});
		
	},
	
	initCartSticky : function() {
		var $window = $(window),
		$stickyEl = $('#cart'),
		$stickyE2 = $('#menucategories'),
		$stickyE3 = $('#showmenumobile'),
		$stickyE4 = $('#gotomenuarrow'),
		
		
		elTop = $stickyE2.offset().top;
		$window.scroll(function() {
			$stickyEl.toggleClass('stick', $window.scrollTop() > elTop);
			$stickyE2.toggleClass('stick', $window.scrollTop() > elTop);
			$stickyE3.toggleClass('stick', $window.scrollTop() > elTop);
			$stickyE4.toggleClass('stick', $window.scrollTop() > elTop);
		});
	}

};

/**
 * When page loads
 */
$(document).ready(function(){
	Initializer.initFeedBackForm();
	Initializer.initMasterJSON();
	Initializer.initOrderFormSubmit();
	Initializer.initDeliveryLocModalSubmit();
	Initializer.initDeliveryLocNonModalSubmit();
	Initializer.initOfferCodeSubmit();
	Initializer.initGISubmit();
	Initializer.initSubscribeSubmit();
	Initializer.initLoginSubmit();
	Initializer.initFGpasswordSubmit();
	Initializer.initSignupSubmit();
	Initializer.initReportSearchSubmit();
	Initializer.initCartSticky();
	
	ga('send','event','Page Hit','click','Page Hit');
});