package com.smallcell.server.handler;



import javax.annotation.Resource;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.TextMessage;

import com.small.cell.server.adapter.AlarmRequestAdapter;

import com.small.cell.server.adapter.AuthRequestAdapter;

import com.small.cell.server.adapter.ConfigureQueryResponseAdapter;
import com.small.cell.server.adapter.ConfigureUpdateResponseAdapter;
import com.small.cell.server.adapter.ControlResponseAdapter;
import com.small.cell.server.adapter.EchoRequestAdapter;
import com.small.cell.server.adapter.ReportRequestAdapter;
import com.small.cell.server.configure.SpringWebSocketHandler;
import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.Kamsg;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Status;
import com.small.cell.server.pojo.TypeCode;
import com.small.cell.server.pojo.PackageData.MsgHeader;


import com.small.cell.server.session.SessionManager;
import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.GoEasyUtil;
import com.small.cell.server.util.JedisUtil;
import com.small.cell.server.util.MyExeUtil;
import com.small.cell.server.util.MyUtils;

/**
 * ����������Ϣ������
 * 
 * @author mazaiting
 */
public class ServerHandler extends IoHandlerAdapter {

	@Bean
	// ���ע����Spring�����ó�Bean
	public static SpringWebSocketHandler infoHandler() {
		return new SpringWebSocketHandler();
	}


	

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		/**
		 * �Զ����쳣���� Ҫ��Ȼ�쳣�ᱻ���Ե�����
		 */
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		/**
		 * �Խ��յ�����Ϣ���Ľ��н�����
		 */
		byte[] bytes = (byte[]) message;
		PackageData packageData = new PackageData();
		MsgHeader msgHeader = new MsgHeader();
		String mac = null;
		msgHeader.setMsgFrameFlag(MyUtils.parseBcdStringFromBytes(bytes, 0, 2));
		msgHeader.setMsgTypeCode(MyUtils.parseBcdStringFromBytes(bytes, 2, 2));
		msgHeader.setMsgVersion(MyUtils.parseBcdStringFromBytes(bytes, 4, 2));
		// msgHeader.setMsgSeqNum(MyUtils.parseIntFromBytes(bytes, 6, 4));

		msgHeader.setMsgSeqNum(ByteAndStr16.Bytes2HexString(MyUtils.subBytes(
				bytes, 6, 4)));

		// msgHeader.setMsgLength(MyUtils.parseIntFromBytes(bytes, 10, 2));

		msgHeader.setMsgLength(ByteAndStr16.Bytes2HexString(MyUtils.subBytes(
				bytes, 10, 2)));
		packageData.setMsgHeader(msgHeader);
		if (FrameFlag.Encrypt.equals(msgHeader.getMsgFrameFlag())) {
			packageData
					.setMsgBodyBytes(MyExeUtil.getExeRes(Para.BlowFishMode_2,
							ByteAndStr16.Bytes2HexString(MyUtils.subBytes(
									bytes,
									PackageData.msgHeaderLength,
									Integer.valueOf(msgHeader.getMsgLength(),
											16) - PackageData.msgHeaderLength))));
		} else if (FrameFlag.NoEncrypt.equals(msgHeader.getMsgFrameFlag())) {

			packageData.setMsgBodyBytes(ByteAndStr16.Bytes2HexString(MyUtils
					.subBytes(bytes, PackageData.msgHeaderLength,
							Integer.valueOf(msgHeader.getMsgLength(), 16)
									- PackageData.msgHeaderLength)));
		}
		switch (TypeCode.getByValue(msgHeader.getMsgTypeCode())) {
		case AuthRequest:
			//packageData = AuthRequestAdapter.handler(packageData, session);
			packageData=AuthRequestAdapter.handler(packageData, session);
			session.write(IoBuffer.wrap(ByteAndStr16
					.HexString2Bytes(packageData.toString())));
			break;
		case ReportRequest:
			packageData = ReportRequestAdapter.handler(packageData);
			session.write(IoBuffer.wrap(ByteAndStr16
					.HexString2Bytes(packageData.toString())));
			break;
		case ControlResponse:
			ControlResponseAdapter.handler(packageData);
			break;
		case ConfigureUpdateResponse:
			ConfigureUpdateResponseAdapter.handler(packageData);
			break;
		case ConfigureQueryResponse:
			mac = ConfigureQueryResponseAdapter.handler(packageData);
			GoEasyUtil.send(String.format("MAC��ַΪ%s���ն����ò�������õ���Ӧ", mac));
			break;
		case AlarmRequest:
			// packageData = AlarmRequestAdapter.handler(packageData);
			packageData = AlarmRequestAdapter.handler(packageData);
			session.write(IoBuffer.wrap(ByteAndStr16
					.HexString2Bytes(packageData.toString())));
			break;
		case EchoRequest:
			packageData = EchoRequestAdapter.handler(packageData);
			session.write(IoBuffer.wrap(ByteAndStr16
					.HexString2Bytes(packageData.toString())));
			break;
		}

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		/**
		 * ��Session����IDLE״̬��ʱ���������״̬������
		 */
		System.out.println("IDLE:" + session.getIdleCount(status));

		String str = (String) session.getAttribute(Kamsg.CONNECT_STATE);
		if (str == null || str.equals(Kamsg.CONNECT_START)) {
			return;

		} else if (str.equals(Kamsg.CONNECT_NO)) {
			int count = (Integer) session.getAttribute(Kamsg.HeartBeat);
			count++;
			session.setAttribute(Kamsg.HeartBeat, count);
			if (count == 2) {
				session.setAttribute(Kamsg.HeartBeat, 0);
				session.close();
			}

		}
		session.setAttribute(Kamsg.CONNECT_STATE, Kamsg.CONNECT_NO);

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {

		String mac = SessionManager.getManager().getKey(session);
		infoHandler().sendMessageToUsers(
				new TextMessage(String.format("%s,%s", mac, Status.OFFLINE)));
		Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
		smtp.setStatus(Status.OFFLINE);
		JedisUtil.hmset(Smtp.SmtpRedisKey, mac, smtp);
		SessionManager.getManager().remove(session);
		System.out.println("�Ự�ر�");
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		System.out.println("�������Ϣ����");
		super.messageSent(iosession, obj);
	}

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		System.out.println("�Ự����");
		iosession.setAttribute(Kamsg.CONNECT_STATE, Kamsg.CONNECT_START);
		iosession.setAttribute(Kamsg.HeartBeat, 0);
		super.sessionCreated(iosession);
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		System.out.println("�Ự��");
		super.sessionOpened(iosession);
	}

}
