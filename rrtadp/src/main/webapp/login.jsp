<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/3 0003
  Time: 上午 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>人人投广告</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
    <title>Title</title>
</head>
<body>
<style>
    html,body{
        height: 100%;
        margin: 0;
        padding: 0;
    }
    /*b7d5df*/
    .login-body{
        height:100%;
        width:100%;
        min-height: 600px;
        min-width: 600px;
        background-image: url(images/login-bg1.jpg);
    }
    .lg-aside-event-txt-icon{
        background-image: url(images/login.svg);
    }
    .regist{
        display: none;
    }
    .psw-info{
        display: none;
    }
    .lg-logo-icon {
        background-image: url(images/app_label.png);
    }
    #tip-info{
        padding-left: 5px;
    }

</style>

<div class="login-body">
    <div class="login-inner">
        <div class="lg-aside">
            <div class="lg-logo">
                <h1 style="color: white;font-size: 30px;">人人投广告</h1>
                <!--<a href="/">
                   &lt;!&ndash; <i class="lg-logo-icon"></i>&ndash;&gt;
                </a>-->
            </div>
            <div class="lg-aside-event">

                <div class="lg-aside-event-tit">
                    <div class="lg-aside-event-icon">

                        <img src="https://imgcache.qq.com/open_proj/proj_qcloud_v2/gateway/login-regist/css/img/event/icon.svg" alt="">

                    </div>
                    <h2>
                        广告用户的信赖首选
                    </h2>
                </div>

                <div class="lg-aside-event-con">


                    <div class="lg-aside-event-txt">
                        <icon class="lg-aside-event-txt-icon"></icon>
                        <span>一些其他的宣传语1</span>
                    </div>



                    <div class="lg-aside-event-txt">
                        <icon class="lg-aside-event-txt-icon"></icon>
                        <span>一些其他的宣传语2</span>
                    </div>



                    <div class="lg-aside-event-txt">
                        <icon class="lg-aside-event-txt-icon"></icon>
                        <span>一些其他的宣传语3</span>
                    </div>



                </div>

            </div>

        </div>
        <div class="lg-content">
            <div class="qc-pt-login-content" id="loginBox">
                <div class="qc-pt-login-content J-commonLoginContent ">
                    <!--login start-->
                    <div class="login-tab">
                        <h1 class="login-tab-title J-txtLoginTitle">登录</h1>
                        <!--邮箱手机登录　start-->
                        <div class="login-box J-loginContentBox J-qcloginBox" style="">
                            <div class="login-form">
                                <div class="tc-msg error" id="error-msg" style="display:none">
                                    <div id="tip-info" class="tip-info J-loginTip"></div>
                                </div>
                                <ul class="form-list">
                                    <li>
                                        <div class="form-input">
                                            <div class="form-unit tip-unit">
                                                <label class="input-tips" style="display: none;">用户名/手机号</label>
                                                <input name="account" value="" type="text" class="qc-log-input-text lg J-username" placeholder="用户名/手机号">
                                                <ul class="tip-list J-mailSuggest" style="display:none;">
                                                </ul>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-input">
                                            <div class="form-unit">
                                                <label class="input-tips" style="display: none;">密码</label>
                                                <input name="password" type="password" class="qc-log-input-text lg J-password" placeholder="密码">
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="op-btn">
                                <input id="login_btn" type="button" value="登 录" class="qc-log-btn J-loginBtn" >
                                <button type="button" class="qc-log-btn lg" style="display:none">
                                    <div class="m-loading">
                                        <div class="loading">
                                            <div class="one"></div>
                                            <div class="two"></div>
                                            <div class="three"></div>
                                        </div>
                                    </div>
                                </button>
                                <div class="psw-info">
                                    <a href="" class="forgot-psw J-link">忘记密码？</a>
                                </div>
                            </div>
                        </div>   <!--邮箱手机登录 end-->   <!--QQ登录示意 start-->
                        <!--<div class="login-box qq-box J-loginContentBox J-ptloginBox" style="height: 300px; padding-bottom: 0px; display: none;">
                            <iframe id="qc_ptlogin_iframe" scrolling="no" width="100%" height="100%" frameborder="0" allowtransparency="yes"></iframe>
                        </div>
                        <div class="login-box wx-box J-loginContentBox J-wxloginBox" style="display: none; height: 260px;">
                            <div class="lg-qr-box">
                                <iframe frameborder="no" scrolling="no" style="width:100%; height:215px;" src=""></iframe>
                            </div>
                        </div>   -->   <!--QQ登录示意 end-->
                        <div class="outside-login">
                            <div class="outside-login-tit">
                                <span class="J-txtMoreLoginType">下载手机APP</span>
                            </div>
                            <div style="height:150px;">
                                <img src="images/renrentouapp.png" style="height:100%;border: #dcdee3 1px solid;"/>
                            </div>
                        </div>
                    </div>  <!--login end-->   <!--注册提示 start-->
                    <div class="regist">
                        <p class="tag-line">    还没有账号？
                            <a href="https://cloud.tencent.com/register?s_url=https%3A%2F%2Fcloud.tencent.com%2F%3FfromSource%3Dgwzcw.234976.234976.234976" class="link J-link" hotrep="login.pc.register">立即注册</a>
                        </p>
                    </div>    <!--注册提示 end-->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.md5.js" type="text/javascript"></script>
</body>
<script>
    (function($){
        var login = {
            option:{
                accountTarget:$("input[name='account']"),
                psdTarget:$("input[name='password']"),
                errorMsgTarget : $("#error-msg"),
                loginBtn:$("#login_btn"),
                errorTipTarget : $("#tip-info"),
            },
            initLogin:function(){
                var that = this;
                this.option.loginBtn.click(function(){
                    that.login();
                });
                this.option.accountTarget.change(function(){
                    that.hideError();
                });
                this.option.psdTarget.change(function(){
                    that.hideError();
                });
            },
            login:function(){
                var that = this;
                if(!this.validate()){
                    return;
                }
                var account = this.option.accountTarget.val(),
                    password = this.option.psdTarget.val(),
                    token = new Date().getTime();
                password = $.md5($.md5(account+password)+token);
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/user/login",
                    dataType:"json",
                    data:{
                        account:account,
                        password:password,
                        token:token
                    },
                    success:function(data){
                        if(data.code==="0"){
                            //window.location='page/jsp/mediadevice.jsp';
                            window.location='${pageContext.request.contextPath}/main.html';
                        }else{
                            that.showError(data.message);
                        }
                    },error:function(){
                        that.showError("登录失败");
                    }
                })
            },
            validate:function(){
                var account = this.option.accountTarget.val(),
                    password = this.option.psdTarget.val();
                if($.trim(account) == ''){
                    this.showError("请填写用户名或手机号");
                    return false;
                }else if($.trim(password) == ''){
                    this.showError("请填写密码");
                    return false;
                }
                return true;
            },
            showError:function(msg){
                this.option.errorMsgTarget.show();
                this.option.errorTipTarget.html(msg);
            },
            hideError:function(){
                this.option.errorMsgTarget.hide();
            }
        };
        $(function(){
            login.initLogin();
        });
    })($);

</script>

</html>