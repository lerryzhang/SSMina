package com.small.cell.server.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.small.cell.server.util.TestFactory;
import com.smallcell.server.handler.ServerHandler;

public class SocketServiceLoader implements ServletContextListener {
	public static final int PORT = 5000;

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
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		// 7. �󶨼����˿�9123.MD

		// acceptor.bind(new InetSocketAddress("localhost",PORT));0
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("===bangding==");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}