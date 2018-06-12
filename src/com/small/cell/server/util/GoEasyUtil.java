package com.small.cell.server.util;

import com.uwantsoft.goeasy.client.goeasyclient.GoEasy;
import com.uwantsoft.goeasy.client.goeasyclient.listener.GoEasyError;
import com.uwantsoft.goeasy.client.goeasyclient.listener.PublishListener;

public class GoEasyUtil {

	static GoEasy goEasy = null;
	static {

		goEasy = new GoEasy("BC-fff7f85db34d4e82bd5dc8ab7f5e29fa");

	}

	public static void send(String message) {

		goEasy.publish("ϵͳ֪ͨ", message, new PublishListener() {
			@Override
			public void onFailed(GoEasyError error) {
				System.out.println("����ʧ���ˣ�Error code:" + error.getCode()
						+ "; error content:" + error.getContent());
			}

			@Override
			public void onSuccess() {
				System.out.println("���ͳɹ�");
			}
		});
	}

	public static void main(String args[]) {

		send("1111111");

	}

}
