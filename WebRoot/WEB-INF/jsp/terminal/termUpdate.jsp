<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />

<body>

	<blockquote class="layui-elem-quote layui-text">方向：服务器->设备</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>列表展示 </legend>
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
				<input type="checkbox" name="c3" value="5003" title="LTE扫描 BAND ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r3" autocomplete="off" 
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，长度：1~16 字节,默认值为模块自身 Band，即 E 频默认扫描 band40。</div>
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
				<div class="layui-form-mid layui-word-aux">整型，范围 0~65535。</div>
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
				<div class="layui-form-mid layui-word-aux">整型数组，频点范围 0~1023，个数
					1~31 个。格式：{1,2,3.。。。。}</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c10" value="x500A" title="采集模式">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="00" checked="" title="只抓一次模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="01" title="周期模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="02" title="全抓模式">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r10" value="03" title="示抓捕模式">
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
				<div class="layui-form-mid layui-word-aux">整型。默认 900 秒，范围
					5~21600，单位：秒。</div>
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
				<div class="layui-form-mid layui-word-aux">整型</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c13" value="500D" title="同步模式">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="00" checked="" title="关闭同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="01" title="空口同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="02" title="GPS 同步">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r13" value="03" title="空口+GPS 同步">
					</div>
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c14" value="500E" title="无线参数">
			</div>

			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r14" 
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">4个整型数，值分别对应band、ulfreq、dlfreq、pci,中间以逗号隔开</div>

			</div>
		</div>





		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c15" value="500F" title="频点表">
			</div>

			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r15" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					整型数组列表，格式如{1，2，3}，{3，4，5}最多支持16组</div>

			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c16" value="5010" title="邻区表">
			</div>

			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r16" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					列表，格式如{1，2，3，4，4，‘123456’，‘78’}最多支持16组</div>

			</div>
		</div>







		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c17" value="5013" title="重定向到2G Band">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r17" value="00" checked=""
							title="DCS1800">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r17" value="01" title="PCS1900">
					</div>

				</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c18" value="5014" title="定位IMSI ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r18"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，长度16</div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c19" value="5015" title="白名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r19" placeholder="请输入IMSI"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，格式为多组用逗号隔开的 15
					位 IMSI</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c20" value="5016" title="黑名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r20" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，格式为多组用逗号隔开的 15
					位 IMSI</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c21" value="5017" title="发射功率 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r21" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">取值：5、4.5、4、3.5、3、2.5、2、1.5、1、0.8、0.65、0.5、0。</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c22" value="5018" title="查询IMSI">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r22" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">长度为 16 的字符串</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c23" value="5019" title="重启小区">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r23" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">首次下发参数时，重启小区值为 1，后续下发值为 0。
				</div>

			</div>
		</div>


		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c24" value="5020" title="PLMN">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r24" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">长度为 6 的字符串。
				</div>

			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c25" value="5021" title="重选优先级">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r25" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">SIB3 小区重选优先级，取值范围 0～7。
				</div>

			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c26" value="5022" title="设备出口IP地址">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r26" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c27" value="5023" title="服务器IP地址">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r27" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c28" value="5024" title="小区最低接入电平">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r28" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">整型数据，范围-22 ～ -77。
				</div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c29" value="5025"
					title="TAU/Attach Reject原因值">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r29" value="11" checked="" title="11">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r29" value="13" title="13">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r29" value="15" title="15">
					</div>

				</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c30" value="5026" title="关闭小区">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r30" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">整型数据，1 表示关闭小区，其它值无效。
				</div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c31" value="5027" title="GPS偏置时间">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r31" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">整型数据，取值范围： -10000～10000，单位 us。
				</div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c32" value="5028" title="GPS偏置时间开关">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r32" value="1" checked="" title="开启">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r32" value="0" title="关闭">
					</div>


				</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c33" value="5029" title="路由器联网方式">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r33" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					{类型方式,IP，掩码，网关，DNS}</div>

			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c34" value="5030" title="开启远程协助">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r34" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">{用户名,ssh 密钥
					id_rsa,密钥长度,服务器的公网 ip 地址，,服务器 ssh 端口号,设备映射到服务器的 ssh端口号。}</div>

			</div>
		</div>
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c35" value="5031" title="关闭远程协助">
				
				<input type="hidden" name="r35" autocomplete="off" value="01"
						class="layui-input">
			</div>


			
		</div>
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c36" value="5032" title="保存配置">
				
				<input type="hidden" name="r36" autocomplete="off" value="01"
						class="layui-input">
			</div>


			
		</div>
		
		
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c37" value="5033" title="日志级别">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r37" value="1" checked="" title="ALERT">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r37" value="2" checked="" title="CRIT">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r37" value="3" checked="" title="ERROR">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r37" value="6" checked="" title="INFO">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r37" value="7" checked="" title="DEBUG">
					</div>
					


				</div>
			</div>
		</div>
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c38" value="5034" title="重定向到 2G 列表（IMSI） ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r38" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					多条IMSI，中间以逗号隔开</div>

			</div>
		</div>
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c39" value="5035" title="起始 TAC">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r39" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>

			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c40" value="5036" title="结束 TAC">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r40" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux"></div>

			</div>
		</div>
		
		
		
		
		
			<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c41" value="5037" title=" REM 开机自动扫描开关">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r41" value="1" checked="" title="开启">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r41" value="0" checked="" title="关闭">
					</div>
					
					


				</div>
			</div>
		</div>
		
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c42" value="5038" title="NTP">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r42" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">格式为：1,2，‘1234567890123456’</div>

			</div>
		</div>
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c43" value="5039" title="同频干扰抑制级别">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="r43" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">整型，取值范围：0 - 6</div>

			</div>
		</div>
		
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c44" value="5040" title=" 开箱告警开关 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r44" value="1" checked="" title="开启">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r44" value="0" checked="" title="关闭">
					</div>
					
					


				</div>
			</div>
		</div>
		
		
		
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c45" value="5041" title="同步守护开关 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r45" value="1" checked="" title="开启">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r45" value="0" checked="" title="关闭">
					</div>
					
					


				</div>
			</div>
		</div>
		


		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">
					立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">
					重置</button>
			</div>
		</div>







	</form>
	<script src="../layui/layui.js"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
		layui
				.use(
						'form',
						function() {
							var form = layui.form, layer = layui.layer;

							form.on('checkbox()', function(obj) {

								var name = obj.elem.name;
								var index = name.substring(1);
								var key1 = "r" + index;
								if (obj.elem.checked == true) {
									$('input[name=' + key1 + ']').attr(
											"lay-verify", 'required');
								} else {

									$('input[name=' + key1 + ']').removeAttr(
											"lay-verify");
								}
							});

							form
									.on(
											'submit(formDemo)',
											function(data) {
												var map = {};
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
																				'input[name='
																						+ key1
																						+ ']')
																				.prop(
																						"type");

																		if (type == "radio") {

																			value = $(
																					'input[name='
																							+ key1
																							+ ']:checked')
																					.val();
																		} else {
																			value = $(
																					'input[name='
																							+ key1
																							+ ']')
																					.val();

																		}
																		map[key] = value;
																	}

																});

												$
														.ajax({
															type : "post",
															url : "<%=basePath%>/smallCell/update",
															data : {
																mac : $(
																		"input[name='mac']")
																		.val(),
																username : JSON
																		.stringify(map)
															},
															dataType : "json",
															success : function(
																	data) {

																if (data == "0") {

																	layer
																			.alert(
																					"更新指令下发成功",
																					{
																						title : "提示",
																						offset : 't',
																						icon : 6
																					});
																} else {

																	layer
																			.alert(
																					"更新指令下发操作出现异常",
																					{
																						title : "提示",
																						offset : 't',
																						icon : 5
																					});

																}

															},
															error : function() {
																layer
																		.alert(
																				"更新指令下发操作出现异常",
																				{
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
