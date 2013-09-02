<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="<c:url value="/resources/site/css/personal.css"/>" rel="stylesheet" type="text/css">
<script src="<c:url value="/resources/site/js/jquery-2.0.3.min.js"/>" type="text/javascript"></script>
<head>
<title>个人资料</title>
</head>

<body id="fans">
<form:form modelAttribute="studengInfo">

<input type="hidden" name="wechatId" value="${param.other}"/>
<div class="qiandaobanner"> <a href="javascript:history.go(-1);"><img src="<c:url value="/resources/site/img/fans.jpg"/>" ></a> </div>
<div class="cardexplain">

<ul class="round">
<li class="title mb"><span class="none">会员资料</span></li>
<li class="nob">
<div class="beizhu">以下信息将作为发奖凭证，请认真填写！</div>
</li>

<li  class="nob">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="kuang">
<tr>
<th>真实姓名</th>
<td><input name="name"  type="text" class="px" id="truename" value="" placeholder="请输入您的真实姓名"></td>
</tr>
</table>
</li>

<li  class="nob">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="kuang">
<tr>
<th>学校</th>
<td><input name="school"  type="text" class="px" id="school" value="" placeholder="请输入您学校名称"></td>
</tr>
</table>
</li>

<li  class="nob">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="kuang">
<tr>
<th>联系电话</th>
<td><input name="mobile"  class="px" id="tel" value=""  type="text" placeholder="请输入您的电话"></td>
</tr>
</table>
</li>
</ul>

<div class="footReturn"> <a id="showcard"  class="submit" href="javascript:void(0)">保 存</a>
	<!--JS消息提示滑动窗口 -->
	<div class="window" id="windowcenter">
		<div id="title" class="wtitle">消息提醒<span class="close" id="alertclose"></span></div>
		<div class="content">
			<div id="txt"></div>
		</div>
	</div>
	</div>
</div>
</form:form>

<div style="display:none"><script type="text/javascript" src="http://tajs.qq.com/stats?sId=27038520" charset="UTF-8"></script></div>

<!--QA for  s-66915.gotocdn.com 
<div style="display:none"><script type="text/javascript" src="http://tajs.qq.com/stats?sId=27025512" charset="UTF-8"></script></div>
-->
</body>
</html>
<script type="text/javascript"> 
$("#showcard").bind("click",
function() {
var btn = $(this);
var truename = $("#truename").val();
if (truename == '') {
alert("请输入姓名!");
return
}
var school = $("#school").val();
if (school == '') {
alert("请输入学校!");
return
}
var tel = $("#tel").val();
if (tel == '') {
alert("请输入手机号!");
return
}
if(!(/^1[3|4|5|6|7|8][0-9]\d{4,8}$/.test(tel))){
alert("输入手机号错误请检查！");
return
}
alert("保存成功");

$("form").submit(); 
});
</script> 
<script type="text/javascript"> 
$(document).ready(function () { 

$("#windowclosebutton").click(function () { 
$("#windowcenter").slideUp(500);
}); 
$("#alertclose").click(function () { 
$("#windowcenter").slideUp(500);
}); 

}); 
function alert(title){ 
$("#windowcenter").slideToggle("slow"); 
$("#txt").html(title); 
setTimeout('$("#windowcenter").slideUp(500)',8000);
} 
</script> 