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
			用户名
			<div class="layui-inline">
				<input class="layui-input" name="id" id="demoReload"
					autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" id="reload">搜索</button>
			<button class="layui-btn layui-btn-normal" id="add">新建用户</button>
			<button class="layui-btn layui-btn-warm">修改用户</button>
			<button class="layui-btn layui-btn-danger">删除用户</button>
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
				url : '<%=basePath%>/user/list',
				page:true,
				cols : [ [ {
					title : '序号',
					templet : '#indexTpl'

				}, {
					field : 'username',
					title : '用户名'
				}, {
					field : 'password',
					title : '密码'

				}, {
					field : 'email',
					title : '邮箱'

				}, {
					field : 'tel',
					title : 'Tel'

				}, 
				
				{
					field : 'ptime',
					title : '创建时间'

				},
				
				{
					field : 'lastip',
					title : '上一次登陆IP'

				}, {
					field : 'lasttime',
					title : '上一次登陆时间'

				} ] ]
			});
			
			
		/* laypage.render({
				elem : 'notice_pages',
				count : 100,
				skin : '#1E9FFF',
				skip: true,
				jump : function(obj, first) {
					if (!first) {
						layer.msg('第' + obj.curr + '页'+obj.limit);

					}
				}
			}); */

			$(document).on('click', '#add', function() {
				layer.open({
					type : 2 //Page层类型
					,
					area : [ '60%', '80%' ],
					title : '新增用户',
					shade : 0.6 //遮罩透明度
					,
					maxmin : true //允许全屏最小化
					,
					anim : 1 //0-6的动画形式，-1不开启
					,
					content : [ '<%=basePath%>/user/add', 'no' ], //iframe的url，no代表不显示滚动条

				});

			});
		})
	</script>

	<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>

</body>
