package com.small.cell.server.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.small.cell.server.util.TestFactory;
import com.smallcell.server.handler.ServerHandler;

public class SocketServiceLoader implements ServletContextListener {
	public static final int PORT = 5000;

	/** 30���ʱ */
	private static final int IDELTIMEOUT = 30;
	/** 30�뷢��һ�������� */
	private static final int HEARTBEATRATE = 30;
	/** ���������� */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		IoAcceptor acceptor = new NioSocketAcceptor();
		// 2. ������־��¼����������SL4J���¼��Ϣ
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());

		acceptor.getFilterChain().addLast("coderc",
				new ProtocolCodecFilter(new TestFactory()));

		// 4. ����ServerHandler, �Զ����Handler,TimeServerHandler
		acceptor.setHandler(new ServerHandler());

		// 6. ���ÿ���ʱ�䣬 �����BOTH_IDLEָEADER_IDLE��WRITER_IDLE��Ϊ10��
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
				IDELTIMEOUT);

		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();

		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
				IdleStatus.BOTH_IDLE);

		heartBeat.setForwardEvent(true);

		heartBeat.setRequestInterval(HEARTBEATRATE);
		
		//heartBeat.setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandlerImpl());

		acceptor.getFilterChain().addLast("heartbeat", heartBeat);

		try {
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("===����˰󶨳ɹ�!!===");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class KeepAliveMessageFactoryImpl implements
			KeepAliveMessageFactory {

		public boolean isRequest(IoSession session, Object message) {
			System.out.println("������������Ϣ: " + message);
			if (message.equals(HEARTBEATREQUEST))
				return true;
			return false;
		}

		@Override
		public boolean isResponse(IoSession session, Object message) {
			System.out.println("��Ӧ��������Ϣ: " + message);
			if (message.equals(HEARTBEATRESPONSE))
				return true;
			return false;
		}

		@Override
		public Object getRequest(IoSession session) {

			/** ����Ԥ����� */
			System.out.println("����Ԥ����Ϣ: " + HEARTBEATREQUEST);
			return HEARTBEATREQUEST;
		}

		@Override
		public Object getResponse(IoSession session, Object request) {
			System.out.println(" ������������ͻ��˷����������󣬿ͻ��˵�ȻҲ���÷���  �÷�������null");
			return null;

		}

	}

}