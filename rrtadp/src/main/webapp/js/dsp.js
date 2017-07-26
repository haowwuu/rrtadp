
if (typeof jQuery === 'undefined') {
  throw new Error('Bootstrap\'s JavaScript requires jQuery')
}

var dsp={
	login:function(){
		var name = $("#loginForm input[name='name']").val();
		var password = $("#loginForm input[name='password']").val();
		var random = randomstr.get(30);
		password = $.md5($.md5(name+password)+random);
		$("#navItem li[name='keys']").hide();
		$("#navItem li[name='stat']").hide();
		$.ajax({
			type:"post",
			url:"app/login",
			dataType:"json",
			data:{name:name, password:password, random:random},
			success:function(data){
				if(data.resultcode==1){
					$("#loginForm p").html(data.message);
					$("#dspLogin").show();
					$("#dspUser").hide();
				}else{
					$("#loginForm p").html("");
					$("#userAccount").html("您好，"+name);
					$("#dspLogin").hide();
					$("#dspUser").show();
					if(data.data.isAdmin){
						$("#navItem li[name='keys']").show();
						$("#navItem li[name='stat']").show();
					}
				}
			}
		})
	}, 
	logout:function(){
		$.post("app/logout");
		$("#dspLogin").show();
		$("#dspUser").hide();
		$("#navItem li[name='keys']").hide();
		$("#navItem li[name='stat']").hide();
		location.href = "http://sso.d.com/cas/rbac/api/logout";
	}
};

var randomstr = {
	_base:"0123456789abcdefghijklmnopqrstuvwxyz",	
	get:function(n){
		var retn = "";
		for(var i = 0; i < n ; i ++) {
		    var id = Math.ceil(Math.random()*35);
		    retn += this._base.charAt(id);
		}
		return retn;
	}
}

/*+function(window){
	var dspinit = function(){};
	dspinit.prototype = {
		load:function(){
			console.log("fadf");
		}
	}
	window.onload = function(){
		new dspinit().load();
	}
}(window)*/


/*+function($){
	
	$("#navItem button]").click(function(){
		console.log('click in');
	})
	console.log('login button binded');
	
}(jQuery)*/