<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
<script src="../layui/layui.js" type="text/javascript"></script>
<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
<script src="../js/DataTableExtend.js"></script>



<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">终端管理平台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">终端管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">系统</a>
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
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> ${
						sessionScope.SESSION_USERNAME}</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">所有商品</a>
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
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
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
						</dl></li>
					<li class="layui-nav-item"><a href="">云市场</a></li>
					<li class="layui-nav-item"><a href="">发布商品</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">

				<div class="demoTable">
					MAC
					<div class="layui-inline">
						<input class="layui-input" name="id" id="demoReload">
					</div>
					<button class="layui-btn">搜索</button>

					<input type="hidden" name="total" value="${total}" id="total">
					<input type="hidden" value="${num}" id="num">
				</div>
				<table class="layui-table" id="test" lay-size="sm" >

					<thead>
						<tr>
							<th>序号</th>
							<th>MAC地址</th>
							<th>设备型号</th>
							<th>固件版本</th>
							<th>启动类型</th>
							<th>对应型号</th>

							<th>协议版本号</th>
							<th>状态</th>
							<th>授权</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${list}" var="smtp" varStatus="status">

							<tr>

								<td>${status.index+1}</td>
								<td id="mac"><a
									href="<%=basePath%>/smallCell/viewSmtp?mac=${smtp.mac }"
									class="layui-table-link" target="_top">${smtp.mac }</a></td>
								<td>${smtp.model }</td>
								<td>${smtp.fw }</td>
								<td>${smtp.startType }</td>
								<td>${smtp.corrModel }</td>
								<td>${smtp.version }</td>
								<td id="cstatus">${smtp.status}</td>
								<td><c:choose>
										<c:when test="${smtp.auth== '0'}">  
                                                                                                    通过
										</c:when>
										<c:otherwise>
											未通过
										</c:otherwise>
									</c:choose></td>

								<td><a class="layui-btn layui-btn-primary layui-btn-xs"
									onclick="control('${smtp.mac }')">控制请求</a> <a
									class="layui-btn layui-btn-xs" onclick="update('${smtp.mac }')">配置更新</a>
									<a class="layui-btn layui-btn-danger layui-btn-xs"
									onclick="query('${smtp.mac }')">配置查询</a>
								</td>
							</tr>

						</c:forEach>





					</tbody>

				</table>
				<div id="demo8"></div>
			</div>

		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© lerry.zhang - 终端管理平台
		</div>
	</div>
</body>


<script>
	
	var websocket;
	var layer;
	var currentRowDataList;
	
     layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage;
        layer = layui.layer;
        laypage.render({
             elem: 'demo8'
             ,count: $("#total").val()
             ,pages: Math.ceil( $("#total").val()/20)
             ,layout: ['count', 'prev', 'page', 'next', 'refresh']
             ,jump: function(obj){
              var curr = obj.curr;
              if(!first){  
                   location.href='<%=basePath%>smallCell/index?&page='+curr;
              }
             }
        });
       });
     
		
	if ('WebSocket' in window) {
		console.log("此浏览器支持websocket");
		websocket = new WebSocket(
				"ws://localhost:8080/SSMina/websocket/socketServer.do");

	} else if ('MozWebSocket' in window) {
		alert("此浏览器只支持MozWebSocket");
	} else {

		alert("此浏览器只支持SockJS");
	}
	websocket.onopen = function(evnt) {

	};

	websocket.onmessage = function(evnt) {
		var message = evnt.data;
		var values = message.split(",");
		$("#test tbody tr").each(function() {
			var objString = $(this).children("#mac").text();
			if (objString.indexOf(values[0])!=-1) {
				$(this).children("#cstatus").text(values[1])
			}
		});
	};
	websocket.onerror = function(evnt) {
	};
	websocket.onclose = function(evnt) {

	};
	
	
	
	var goEasy = new GoEasy({
			appkey : 'BC-fff7f85db34d4e82bd5dc8ab7f5e29fa'
		});
		goEasy.subscribe({
			channel : '系统通知',
			onMessage : function(message) {

				layer.open({
					title : '在线调试',
					offset : 'rb',
					content : message.content
				});

			}
		});
	
	function control(mac){
	layer.open({
						type : 2 //Page层类型
						,
						area : [ '800px', '600px' ],
						title : '控制命令列表',
						shade : 0.6 //遮罩透明度
						,
						maxmin : true //允许全屏最小化
						,
						anim : 1 //0-6的动画形式，-1不开启
						,
						content : [ '<%=basePath%>/control.jsp?mac='
														+ mac, 'no' ], //iframe的url，no代表不显示滚动条
									});
		
									
							
	}
	
	
	
	
	function query(mac){
		$.ajax({
						url : "<%=basePath%>/smallCell/query",
			type : "POST",
			data : {
				"mac" : mac
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

	}
	
	function update(mac){
		layer.open({
						type : 2,//Page层类型
					
						area : [ '1000px', '600px' ],
						title : '更新命令列表',
						shade : 0.6, //遮罩透明度
						maxmin : true, //允许全屏最小化
						anim : 1 ,//0-6的动画形式，-1不开启
						
					content : [ '<%=basePath%>/update.jsp?mac=' + mac, 'yes' ], //iframe的url，no代表不显示滚动条
		});

	}
</script>



