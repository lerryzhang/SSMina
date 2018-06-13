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
				<input type="checkbox" name="c3" value="5005" title="探针开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r3" value="00000000" checked=""
							title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r3" value="00000001" title="开启">
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
	layui
			.use(
					'form',
					function() {
						var form = layui.form;

						form
								.on(
										'submit(formDemo)',
										function(data) {
											var map = {}; // Map map = new HashMap();
											$("input[type='checkbox']")
													.each(

															function() {
																if ($(this)
																		.is(
																				":checked")) {
																	var key = $(
																			this)
																			.val();
																	var name = $(
																			this)
																			.prop(
																					"name");
																	var index = name
																			.substring(1);
																	var key1 = "r"
																			+ index;
																	var value;
																	var type = $(
																			'input[name=' + key1 + ']')
																			.prop(
																					"type");

																	if (type == "radio") {

																		value = $(
																				'input[name=' + key1 + ']:checked')
																				.val();
																	} else {
																		value = $(
																				'input[name=' + key1 + ']')
																				.val();
																	}
																	map[key] = value;
																}

															});

											$
													.ajax( {
														type : "post",
														url : "smallCell/update",
														data : {
															mac : $(
																	"input[name='mac']")
																	.val(),
															username : JSON
																	.stringify(map)
														},
														dataType : "json",
														success : function(data) {

															alert("提交成功");
														}
													});

										});
					});
</script>


</body>
