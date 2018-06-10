<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>layout 后台大布局 - Layui</title>
<link href="layui/css/layui.css" rel="stylesheet" type="text/css" />

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">
				终端管理后台
			</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item">
					<a href="">控制台</a>
				</li>
				<li class="layui-nav-item">
					<a href="">商品管理</a>
				</li>
				<li class="layui-nav-item">
					<a href="">用户</a>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl>
				</li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;"> <img src="http://t.cn/RCzsdCq"
							class="layui-nav-img"> 贤心 </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="">退了</a>
				</li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="javascript:;">所有商品</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">列表一</a>
							</dd>
							<dd>
								<a href="javascript:;">列表二</a>
							</dd>
							<dd>
								<a href="javascript:;">列表三</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">解决方案</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">列表一</a>
							</dd>
							<dd>
								<a href="javascript:;">列表二</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="">云市场</a>
					</li>
					<li class="layui-nav-item">
						<a href="">发布商品</a>
					</li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">


				<table class="layui-hide" id="test" lay-filter="demo"></table>

			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
	<script src="layui/layui.js" type="text/javascript"></script>
	<script src="jquery/jquery-2.0.3.min.js" type="text/javascript"></script>

	<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="control">控制请求</a>
  <a class="layui-btn layui-btn-xs" lay-event="update">配置更新</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="query">配置查询</a>
</script>

	<script>
	//JavaScript代码区域
	layui.use('element', function() {
		var element = layui.element;

	});

	layui.use('table', function() {
		var table = layui.table;

		table.render( {
			elem : '#test',
			url : 'smallCell/list',
			page : true //开启分页
			,
			cellMinWidth : 120,
			cols : [ [ {title: '序号',templet: '#indexTpl'}, {
				field : 'mac',
				title : 'MAC地址'
			}, {
				field : 'model',
				title : '设备型号'
			}, {
				field : 'fw',
				title : '固件版本'
			}, {
				field : 'startType',
				title : '启动类型'

			}, {
				field : 'rem',
				title : 'REM扫描状态'
			}, {
				field : 'routerFw',
				title : '路由器固件版本'
			}, {
				field : 'tac',
				title : 'TAC'
			}, {
				fixed : 'right',
				align : 'center',
				toolbar : '#barDemo',
				width : 250
			} ] ]

		});

		table.on('tool(demo)', function(obj) {
			var data = obj.data;
			if (obj.event === 'query') {
				$.ajax( {
					url : "smallCell/query",
					type : "POST",
					data : {
						"mac" : data.mac
					},
					dataType : "json",
					success : function(data) {
						if (data == 0) {
							layer.msg('配置查询指令下发成功！');
						} else {
							layer.msg('配置查询指令下发失败！');
						}
					}
				});

			} else if (obj.event === 'update') {
			 layer.open({
              type: 2 //Page层类型
             ,area: ['800px', '600px']
              ,title: '更新命令列表'
              ,shade: 0.6 //遮罩透明度
              ,maxmin: true //允许全屏最小化
              ,anim: 1 //0-6的动画形式，-1不开启
              ,content: ['update.jsp?mac='+data.mac, 'yes'], //iframe的url，no代表不显示滚动条
            });  

			} else if (obj.event === 'control') {
			
			 layer.open({
              type: 2 //Page层类型
             ,area: ['800px', '600px']
              ,title: '控制命令列表'
              ,shade: 0.6 //遮罩透明度
              ,maxmin: true //允许全屏最小化
              ,anim: 1 //0-6的动画形式，-1不开启
              ,content: ['control.jsp?mac='+data.mac, 'no'], //iframe的url，no代表不显示滚动条
            });  
			}
		});

	});
</script>

<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
</body>
