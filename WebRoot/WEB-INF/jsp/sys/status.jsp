<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />

<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>运行状态 </legend>
	</fieldset>
	<table class="layui-table">
		<tr>
			<th>总内存</th>
			<th>${status.totalMemory}</th>
		</tr>
		
		<tr>
			<th>剩余内存</th>
			<th>${status.freeMemory}</th>
		</tr>
		<tr>
			<th>最大内存</th>
			<th>${status.maxMemory}</th>
		</tr>
		
		<tr>
			<th>最大线程数</th>
			<th>${status.maxThreads}</th>
		</tr>
		
		<tr>
			<th>当前线程数</th>
			<th>${status.currentThreadCount}</th>
		</tr>
		
		<tr>
			<th>当前繁忙线程数</th>
			<th>${status.currentThreadBusy}</th>
		</tr>
		
		<tr>
			<th>最大处理时间</th>
			<th>${status.maxProcessingTime}</th>
		</tr>
		<tr>
			<th>最短处理时间</th>
			<th>${status.msProcessingTime}</th>
		</tr>
		<tr>
			<th>请求数</th>
			<th>${status.requestCount}</th>
		</tr>
		<tr>
			<th>错误数</th>
			<th>${status.errCount}</th>
		</tr>
		
		<tr>
			<th>接收字节</th>
			<th>${status.bytesReceived}</th>
		</tr>
		<tr>
			<th>发送字节</th>
			<th>${status.bytesSent}</th>
		</tr>
		</table>
	<script src="../layui/layui.js"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>

</body>
