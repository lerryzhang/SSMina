<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />

<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<!-- INCLUDES -->
<link rel="stylesheet" href="../css/bootstrap-table-expandable.css">
<script src="../js/bootstrap-table-expandable.js"></script>

<link rel="stylesheet" href="../css/font_eolqem241z66flxr.css"
	media="all" />
<style type="text/css">
.layui-table td,.layui-table th {
	text-align: center;
}

.layui-table td {
	padding: 5px;
}
</style>


<body class="childrenBody">

	<blockquote class="layui-elem-quote layui-text">设备详细参数</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>
			参数列表 <input type="hidden" name="mac" value="<%=mac%>">
		</legend>
	</fieldset>
	<form class="layui-form">
		<div class="container">
			<table class=" layui-table table-expandable">
				<colgroup>
					<col width="20%">
					<col width="40%">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>参数说明</th>
						<th>参数值</th>
						<th>参考</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>设备MAC地址</td>
						<td>${smtp.mac}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>协议版本号</td>
						<td>${smtp.version}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>设备型号</td>
						<td>${smtp.model}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>固件版本</td>
						<td>${smtp.fw}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>启动类型</td>
						<td>${smtp.startType}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>参数列表</h4>
							<ul>
								<li>0 上电</li>
								<li>1 手动重启</li>
								<li>2 升级</li>
								<li>3 恢复出厂设置</li>
								<li>4 软件重启</li>
								<li>5 网络重连接</li>
								<li>6 路由器升级</li>
								<li>7 异常重启</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>REM扫描状态</td>
						<td>${smtp.rem}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>参数列表</h4>
							<ul>
								<li>0 尚未进行REM扫描</li>
								<li>1 REM扫描完成</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>对应型号</td>
						<td>${smtp.corrModel}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>对应型号列表</h4>
							<ul>
								<li>0x0007 对应型号：TDD D频（ZL-81X3-D）</li>
								<li>0x0008 对应型号：TDD E频（ZL-81X3-E）</li>
								<li>0x0009 对应型号：TDD F频 (ZL-81X3-F)</li>
								<li>0x000a 对应型号：FDD Band1电信 (ZL-81X3-1)</li>
								<li>0x000b 对应型号：FDD Band1联通 (保留)</li>
								<li>0x000c 对应型号：FDD Band3电信 (ZL-81X3-3D)</li>
								<li>0x000d 对应型号：FDD Band3联通 (ZL-81X3-3L)</li>
								<li>0x000e 对应所有型号 （全局性配置使用，如服务器IP）</li>
								<li>0x0010 对应型号：TDD E频（室内普通，ZL-8110）</li>
								<li>0x0011 对应型号：TDD D频（室内加功放，ZL-8700-D）</li>
								<li>0x0012 对应型号：TDD E频 (室内加功放，ZL-8700-E)</li>
								<li>0x0013 对应型号：TDD F频 (室内加功放，ZL-8700-F)</li>
								<li>0x0014 对应型号：FDD Band1电信 (室内加功放，ZL-8700-1)</li>
								<li>0x0015 对应型号：FDD Band1联通 (保留)</li>
								<li>0x0016 对应型号：FDD Band3电信 (室内加功放，ZL-8700-3D)</li>
								<li>0x0017 对应型号：FDD Band3联通 (室内加功放，ZL-8700-3L)</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>路由器固件版本</td>
						<td>${smtp.routerFw}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>

					<tr>
						<td>IMSI</td>
						<td>${smtp.imsi}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>经纬度</td>
						<td>${smtp.lnglat}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>BAND信息</td>
						<td>${smtp.band}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>TAC</td>
						<td>${smtp.tac}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>频点表</td>
						<td>${smtp.frePoints}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>

					<tr>
						<td>邻区表</td>
						<td>${smtp.regions}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>PLMN</td>
						<td>${smtp.plmn}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>频点</td>
						<td>${smtp.frePoint}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>PCI</td>
						<td>${smtp.pci}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>场强</td>
						<td>${smtp.fieldStrength}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>

					<tr>

						<td>当前嗅探的频点</td>
						<td>${smtp.nowfrePoint}</td>
						<td></td>

					</tr>

					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>


					<tr>

						<td>IMSI捕获时间</td>
						<td>${smtp.imsiCaptureTime}</td>
						<td></td>

					</tr>

					<tr>
						<td colspan="3">
							<h4></h4>
							<ul>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>系统运行时长</td>
						<td>${smtp.uptimeSys}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>参数列表</h4>
							<ul>
								<li>第一个参数为系统启动时长</li>
								<li>第二个参数为小区启动时长</li>
								<li></li>
							</ul>
						</td>
					</tr>
					
					<tr>
						<td>状态信息定时上报</td>
						<td>${smtp.smtpState}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>参数列表</h4>
							<ul>
								<li>小区状态：0:down 1:up</li>
								<li>电源状态 始终为1</li>
								<li>空口同步状态：0:未同步 1:同步</li>
								<li>GPS同步状态：0:未同步 1:同步</li>
								<li>1588同步状态：0:未同步 1:同步</li>
								<li>CPU使用率</li>
								<li>内存使用率</li>
								<li>CPU温度</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td>IMSI缓存与传输状态</td>
						<td>${smtp.imsiTransmit}</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">
							<h4>参数列表</h4>
							<ul>
								<li>1:前30s上报成功计数</li>
								<li>2:前30s实时数据发送计数</li>
								<li>3:前30s重传发送计数</li>
								<li>4:前30s离线缓存数据发送计数</li>
								<li>5:前30s上报成功百分比</li>
								<li>6:当前离线缓存数据余量</li>
								<li>7:当前剩余离线缓存数据中，最老的一条捕获时间</li>
							</ul>
						</td>
					</tr>

				</tbody>
			</table>
		</div>
	</form>


	<script src="../layui/layui.js" type="text/javascript"></script>

	<script src="../js/systemParameter.js"></script>

	<script>
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element();
			var layer = layui.layer;

			//监听折叠
			element.on('collapse(test)', function(data) {
				layer.msg('展开状态：' + data.show);
			});
		});
	</script>




</body>


