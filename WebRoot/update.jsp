<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>
<link href="layui/css/layui.css" rel="stylesheet" type="text/css" />

<body>

	<blockquote class="layui-elem-quote layui-text">
		方向：服务器 ->设备
	</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>
			列表展示
		</legend>
	</fieldset>

	<form class="layui-form" action="">
		<!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c1" value="5001" title="参数选择算法">
				<input type="hidden" name="mac" value="<%=mac%>">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r1" value="00000000" checked=""
							title="频率次数优先">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r1" value="00000001" title="优先级优先">
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c2" value="5002" title="参数自配置开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r2" value="00000000" checked=""
							title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r2" value="00000001" title="开启">
					</div>
				</div>
			</div>

		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c3" value="5003" title="LTE 扫描 BAND ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r3" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c4" value="5004" title="LTE 扫描频点列表 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r4" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>





		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c5" value="5005" title="探针开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r5" value="00000000" checked=""
							title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r5" value="00000001" title="开启">
					</div>
				</div>
			</div>

		</div>



		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c6" value="5006" title="重定向开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r6" value="00" checked="" title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r6" value="01" title="开启">
					</div>
				</div>
			</div>

		</div>


		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c7" value="5007" title="重定向选择开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r7" value="00" title="4G">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r7" value="01" title="2G">
					</div>
				</div>
			</div>

		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c8" value="5008" title="重定向到 4G 频点 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r8" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					整型，范围 0~65535。
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c9" value="5009" title="重定向到 2G 频点 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r9" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					整型数组，频点范围 0~1023，个数 1~31 个。
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c10" value="x500A" title="采集模式">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="0" checked="" title="只抓一次模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="1" title="周期模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="2" title="全抓模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="3" title="示抓捕模式">
					</div>
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c11" value="500B" title="周期采集时间 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r11" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					整型。默认 900 秒，范围 5~21600，单位：秒。
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c12" value="500C" title="TAC ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r12" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					整型
				</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c13" value="500D" title="同步模式">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="0" checked="" title="关闭同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="1" title="空口同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="2" title="GPS 同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="3" title="空口+GPS 同步">
					</div>
				</div>
			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c4" value="5015" title="白名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r4" placeholder="请输入IMSI"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					字符串，格式为多组用逗号隔开的 15 位 IMSI
				</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c5" value="5015" title="黑名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r5" placeholder="请输入IMSI"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					字符串，格式为多组用逗号隔开的 15 位 IMSI
				</div>

			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">
					立即提交
				</button>
				<button type="reset" class="layui-btn layui-btn-primary">
					重置
				</button>
			</div>
		</div>
	</form>
	<script src="layui/layui.js"></script>
	<script src="jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
	layui.use('form', function() {
		var form = layui.form;

		form.on('submit(formDemo)', function(data) {
			var map = {};
			$("input[type='checkbox']").each(

			function() {
				if ($(this).is(":checked")) {
					var key = $(this).val();
					var name = $(this).prop("name");
					var index = name.substring(1);
					var key1 = "r" + index;
					var value;
					var type = $('input[name=' + key1 + ']').prop("type");

					if (type == "radio") {

						value = $('input[name=' + key1 + ']:checked').val();
					} else {
						value = $('input[name=' + key1 + ']').val();
					}
					map[key] = value;
				}

			});

			$.ajax( {
				type : "post",
				url : "smallCell/update",
				data : {
					mac : $("input[name='mac']").val(),
					username : JSON.stringify(map)
				},
				dataType : "json",
				success : function(data) {

					if (data == "0") {
						layer.alert("更新指令下发成功", {
								title : "提示",
								icon : 6
							});
					} else {
						layer.alert("更新指令下发操作出现异常", {
								title : "提示",
								icon : 5
							});

					}

				},
				error : function() {
						layer.alert("更新指令下发操作出现异常", {
								title : "提示",
								icon : 5
							});
					}
			});
			return false;

		});
	});
</script>


</body>
