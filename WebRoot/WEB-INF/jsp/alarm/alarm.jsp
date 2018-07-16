<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<title>layout 后台大布局 - Layui</title>




<!-- 
 <meta http-equiv="refresh" content="10">  
  -->
<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
<!--  
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
-->

<body class="layui-layout-body">

	<!-- 内容主体区域 -->
	<div style="padding: 15px;">

		<div class="demoTable">
			MAC
			<div class="layui-inline">
				<input class="layui-input" name="id" id="demoReload"
					autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" id="reload">搜索</button>

			<button class="layui-btn layui-btn-primary">导出报表</button>
		</div>


		<table class="layui-hide" id="test" lay-filter="demo"></table>

	</div>

	<div id="notice_pages"></div>

	<script src="../layui/layui.js" type="text/javascript"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>

	<script src="../js/DataTableExtend.js"></script>



	<script>
	
		layui.use(['laypage', 'layer', 'table'], function() {
			var table = layui.table
			,laypage = layui.laypage;
			 table.render({
				elem : '#test',
				url : '<%=basePath%>/alarm/list',
				page : true,
				cols : [ [ {
					title : '序号',
					field : 'id'

				},

				{
					field : 'mac',
					title : 'MAC'
				}, {
					field : 'code',
					title : '告警编码'
				}, {
					field : 'version',
					title : '异常版本号'

				}, {
					field : 'process',
					title : '异常退出进程'

				}, {
					field : 'restart',
					title : '异常重启'

				}, {
					field : 'tip',
					title : '告警信息'

				},

				{
					field : 'ptime',
					title : '上报时间'

				}

				] ]
			});

		})
	</script>



</body>
