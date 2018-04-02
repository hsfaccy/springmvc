<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>测试</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="<%=basePath%>resources/frozenui/css/frozen.css">
<link rel="stylesheet" href="<%=basePath%>resources/frozenui/demo/demo.css">
<script src="<%=basePath%>resources/frozenui/lib/zepto.min.js"></script>
<script src="<%=basePath%>resources/frozenui/js/frozen.js"></script>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>首页</h1>
	</header>
	<footer class="ui-footer ui-footer-btn">
	<ul class="ui-tiled ui-border-t">
		<li data-href="<%=basePath%>wechat/index"><i class="ui-icon-add"></i>
			<div class="fontclass">首页</div></li>

		<li data-href="<%=basePath%>wechat/waterquality"><i
			class="ui-icon-more"></i>
			<div class="fontclass">河流水质</div></li>

		<li data-href="<%=basePath%>wechat/roadriver"><i
			class="ui-icon-arrow"></i>
			<div class="fontclass">河道信息</div></li>
	</ul>
	</footer>
	<section class="ui-container"> <section id="slider">

	<div>
		<ul class="ui-tiled ui-border-t">
			<li data-href="<%=basePath%>wechat/briefingList"><i
				class="ui-icon-add"></i>
				<div class="fontclass">最新资讯</div></li>

			<li data-href="<%=basePath%>wechat/waterquality"><i
				class="ui-icon-more"></i>
				<div class="fontclass">河流水质</div></li>

			<li data-href="<%=basePath%>wechat/roadriver"><i
				class="ui-icon-arrow"></i>
				<div class="fontclass">河道信息</div></li>
		</ul>
	</div>

	</section> </section>
	<script>
(function (){
    var slider = new fz.Scroll('.ui-slider', {
        role: 'slider',
        indicator: true,
        autoplay: true,
        interval: 5000
    });
})();
$('.ui-list li,.ui-tiled li').click(function(){
    if($(this).data('href')){
        location.href= $(this).data('href');
    }
});
</script>
</body>
</html>
