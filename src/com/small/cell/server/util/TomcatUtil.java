package com.small.cell.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.small.cell.server.pojo.TomcatStatus;

public class TomcatUtil {

	public static String getHtmlContext(String tempurl, String username,
			String password) throws IOException {
		URL url = null;
		BufferedReader breader = null;
		InputStream is = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			url = new URL(tempurl);
			String userPassword = username + ":" + password;
			String encoding = new sun.misc.BASE64Encoder().encode(userPassword
					.getBytes());// 在classpath中添加rt.jar包，在%java_home%/jre/lib/rt.jar

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			is = conn.getInputStream();
			breader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = breader.readLine()) != null) {
				resultBuffer.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			if (breader != null)
				breader.close();
			if (is != null)
				is.close();
		}
		return resultBuffer.toString();

	}

	public static void main(String args[]) {

		getTomcatStatus();

	}

	public static TomcatStatus getTomcatStatus() {
		String result = "";
		Document document = null;// 引入org.dom4j包
		TomcatStatus tomcatStatus = new TomcatStatus();
		try {
			result = getHtmlContext(
					"http://127.0.0.1:8080/manager/status?XML=true", "tomcat",
					"tomcat");
			System.out.println("===" + result);

			document = DocumentHelper.parseText(result);// 将字符串转化为XML的Document
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 对xml结点的操作
		Element rootElement = document.getRootElement();
		Element element = rootElement.element("jvm");
		element = element.element("memory");
		tomcatStatus.setMaxMemory(element.attribute("max").getText());
		tomcatStatus.setTotalMemory(element.attribute("total").getText());
		tomcatStatus.setFreeMemory(element.attribute("free").getText());

		element = rootElement.element("connector");

		element = element.element("threadInfo");
		tomcatStatus.setMaxThreads(element.attribute("maxThreads").getText());
		tomcatStatus.setCurrentThreadCount(element.attribute(
				"currentThreadCount").getText());
		tomcatStatus.setCurrentThreadBusy(element.attribute(
				"currentThreadsBusy").getText());
		element = rootElement.element("connector");

		element = element.element("requestInfo");
		tomcatStatus.setMaxProcessingTime(element.attribute("maxTime")
				.getText());
		tomcatStatus.setMsProcessingTime(element.attribute("processingTime")
				.getText());
		tomcatStatus.setRequestCount(element.attribute("requestCount")
				.getText());
		tomcatStatus.setErrCount(element.attribute("errorCount").getText());
		tomcatStatus.setBytesReceived(element.attribute("bytesReceived")
				.getText());
		tomcatStatus.setBytesSent(element.attribute("bytesSent").getText());

		return tomcatStatus;

	}
}
