<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
<head>
<meta charset="utf-8" />
<title>摘星</title>
<meta name="keywords" content="设置关键词..." />
<meta name="description" content="设置描述..." />
<meta name="author" content="DeathGhost" />
<meta name="renderer" content="webkit">

<style>
.line {
	height: 1px;
	margin: 0px auto;
	padding: 0px;
	background-color: black;
	overflow: hidden;
}

.kuangkuang {
	width: 22%;
	height: 159px;
	border-style: solid;
	border-width: 1px;
	float: left;
	border-color: rgb(201, 201, 201);
}

.kuangkuang-up {
	line-height: 96px;
	text-align: center;
	font-size: 32px;
	position: relative;
}

.kuangkuang-down {
	text-align: center;
	line-height: 65px;
	font-size: 20px;
	background-color: rgb(233, 233, 233);
}
</style>


<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
<script src="../laydate/laydate.js"></script>
<script src="../layui/layui.js" type="text/javascript"></script>
<script src="../echarts/echarts.min.js"></script>

</head>
<body style="width: 100%;height: 120%">
	<br>
	<div class="page-container">
		<div style="height:159px;text-align:center;">
			<div class="kuangkuang" style="margin-left:2%;">
				<div class="kuangkuang-down">终端数</div>
				<a href="<%=basePath%>/smallCell/termList" target="option"><div
						class="kuangkuang-up" id="rkd">0</div> </a>
			</div>
			<div class="kuangkuang" style="margin-left:2%;">
				<div class="kuangkuang-down">在线数</div>
				<div class="kuangkuang-up" id="ckd">0</div>
			</div>
			<div class="kuangkuang" style="margin-left:2%;">
				<div class="kuangkuang-down">告警数</div>
				<div class="kuangkuang-up" id="shouru">0</div>
			</div>
			<div class="kuangkuang" style="margin-left:2%;">
				<div class="kuangkuang-down">用户数</div>
				<a href="<%=basePath%>/user/index" target="option"><div
						class="kuangkuang-up" id="zhichu">0</div> </a>
			</div>
		</div>

		<!--  
		<br> <br> <input type="text" id="test5"
			placeholder="yyyy-MM-dd HH:mm:ss" class="layui-input"
			style="width:140px;float: right;margin-right: 4%;"> <span
			style="float: right;margin-right:1%;margin-top:5px;">选择时间:</span> <br>
		-->

		<br>
		<div id="main1"
			style="width:50%;height:450px;float:left;overflow:auto;"></div>
		<!-- 多个柱状图 -->
		<div id="main2"
			style="width:50%;height:480px;float:left;overflow:auto;"></div>
	</div>








	<script>
      laydate.render({
        elem: '#test5' //指定元素
     });
</script>

	<script type="text/javascript">		
	var myChart1 = echarts.init(document.getElementById('main1'));
	var myChart2 = echarts.init(document.getElementById('main2'));
     $(document).ready(function () {
			$.ajax({
				type : "POST",
				url : "<%=basePath%>/smallCell/getKuNameAndCount",
				success : function(data) {
					data = JSON.parse(data);
					$("#rkd").html(data.tcount);
					$("#ckd").html(data.ocount);
					$("#shouru").html(data.ghichu);
					$("#zhichu").html(data.ucount);
					var option1 = {
						title : {
							text : '近半年终端接入数'
						},
						tooltip : {},
						legend : {
							bottom : '-1%',
							data : [ '接入终端数' ]
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '9%',
							containLabel : true
						},
						xAxis : {
							data : data.month
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						yAxis : {},
						series : [ {
							name : '接入终端数',
							type : 'bar',
							data : [ 8, 15, 52, 16, 17, 22 ]
						} ]
					};

					myChart1.setOption(option1);

					var option2 = {
						title : {
							text : '一周内告警统计'
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							bottom : '4%',
							data : [ '告警' ]
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '9%',
							containLabel : true
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : data.week,
							axisLabel : {
								rotate : 45
							}
						},
						yAxis : {
							type : 'value'
						},
						series : [ {
							name : '告警数',
							type : 'line',
							data : data.countList
						} ]
					};

					myChart2.setOption(option2);

				}
			});

		});

		/*---------------------------------------------------------------*/
	</script>
</body>
</html>