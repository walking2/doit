<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="_port" value=""/><c:if test="${pageContext.request.serverPort != 80}"><c:set var="_port" value=":${pageContext.request.serverPort}"/></c:if>
<c:set var="base"   value="http://${pageContext.request.serverName}${_port}${pageContext.request.contextPath}"/>
<c:set var="wwwurl" value="http://${pageContext.request.serverName}${_port}"/>
<c:set var="servername" value="http://${pageContext.request.serverName}"/>

<%--<link rel="stylesheet" type="text/css" href="${base}/css/table.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${base}/js/jquery-easyui-1.5/themes/bootstrap/easyui.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${base}/js/jquery-easyui-1.5/themes/icon.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${base}/js/jquery-easyui-1.5/themes/color.css">--%>
<%--<link rel="stylesheet" type="text/css" href="${base}/js/bootstrap/css/bootstrap.css">--%>
<script  type="text/javascript" src="${base}/js/jquery/jquery-2.2.0.min.js"></script>
<script  type="text/javascript" src="${base}/js/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/js/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script  type="text/javascript" src="${base}/js/myjs/datagrid.js"></script>
<script  type="text/javascript" src="${base}/js/myjs/number2Zm.js"></script>
<script type="text/javascript" src="${base}/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/js/myjs/validate.js"></script>
<script type="text/javascript">
var base = "${base}";
var _port = "${_port}";
var wwwurl = "${wwwurl}";
var servername = "${servername}";


function ftStatus(value,row,index){
	if(value==0){
		return "冻结";
	}else if(value==1){
		return  "正常";
	}
}



function ftTime(value, row, index) {
	if(value > 0){
		return formatTime(value * 1000, "YYYY-MM-DD hh:mm");	
	}
}


function ftDay(value, row, index) {
	if(value > 0){
		return formatTime(value * 1000, "YYYY-MM-DD");
	}
}

function getFormatedSelTime(selTime) {
	var selTime = selTime.replace(/-/g, "/");
	var timestamp = Date.parse(new Date(selTime));
	if (!isNaN(timestamp)) {
		return timestamp / 1000;
	}
}


function formatTime(time,fmt){
	  var t = new Date(time);
	  var month=t.getMonth()+1;
	  if(fmt=='YYYY-MM-DD'){
		  return   t.getFullYear()+"-"+month+"-"+t.getDate();
	  }
	  if(fmt=='YYYY-MM-DD hh:mm'){
		  return t.getFullYear()+"-"+month+"-"+t.getDate()+" "+t.getHours()+":"+t.getMinutes()+":00";
	  }
	  if(fmt=='YMD'){
		  return   t.getFullYear()+"年"+month+"月"+t.getDate()+"日";
	  }
	  if(fmt=='YMD hms'){
		  return   t.getFullYear()+"年"+month+"月"+t.getDate()+"日"+" "+t.getHours()+"时"+t.getMinutes()+"分"+t.getSeconds()+"秒";
	  }
}


/**
 * 四舍五入  n表示保留几位小数
 */
function cheng(value){
	var value=Math.round(parseFloat(value)*100)/100;
	 var xsd=value.toString().split(".");
	 if(xsd.length==1){
	 value=value.toString()+".00";
	 return value;
	 }
	 if(xsd.length>1){
	 if(xsd[1].length<2){
	  value=value.toString()+"0";
	 }
	 return value;
	 }
}


$(document).ajaxComplete(function(event,ajax){
    if(ajax.responseText && ajax.responseText.indexOf("/reLogin") != -1){
    	parent.location.href = "../../";
    }
});



</script>


