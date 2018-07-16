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

	/** 30秒后超时 */
	private static final int IDELTIMEOUT = 30;
	/** 30秒发送一次心跳包 */
	private static final int HEARTBEATRATE = 30;
	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		IoAcceptor acceptor = new NioSocketAcceptor();
		// 2. 加入日志记录过滤器，用SL4J库记录信息
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());

		acceptor.getFilterChain().addLast("coderc",
				new ProtocolCodecFilter(new TestFactory()));

		// 4. 设置ServerHandler, 自定义的Handler,TimeServerHandler
		acceptor.setHandler(new ServerHandler());

		// 6. 设置空闲时间， 这里的BOTH_IDLE指EADER_IDLE和WRITER_IDLE都为10秒
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
			System.out.println("===服务端绑定成功!!===");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class KeepAliveMessageFactoryImpl implements
			KeepAliveMessageFactory {

		public boolean isRequest(IoSession session, Object message) {
			System.out.println("请求心跳包信息: " + message);
			if (message.equals(HEARTBEATREQUEST))
				return true;
			return false;
		}

		@Override
		public boolean isResponse(IoSession session, Object message) {
			System.out.println("响应心跳包信息: " + message);
			if (message.equals(HEARTBEATRESPONSE))
				return true;
			return false;
		}

		@Override
		public Object getRequest(IoSession session) {

			/** 返回预设语句 */
			System.out.println("请求预设信息: " + HEARTBEATREQUEST);
			return HEARTBEATREQUEST;
		}

		@Override
		public Object getResponse(IoSession session, Object request) {
			System.out.println(" 服务器不会给客户端发送心跳请求，客户端当然也不用反馈  该方法返回null");
			return null;

		}

	}

}