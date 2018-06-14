/**
 * 
 */
package com.small.cell.server.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.small.cell.server.pojo.Smtp;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisUtil
 * @Description TODO
 * @author lerry.zhang
 * @date 2018-2-23 上午11:02:43
 */

public class JedisUtil {
	private static String JEDIS_IP = "127.0.0.1";
	private static int JEDIS_PORT = 6379;
	private static String JEDIS_PASSWORD = null;
	private static final JedisPool jedisPool;

	private static String PROPERTIES_NAME = "redis.properties";
	private static Logger logger = Logger.getLogger(JedisUtil.class);
	static {
		InputStream in = null;
		try {
			Properties properties = new Properties();
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(PROPERTIES_NAME);
			properties.load(in);
			JEDIS_IP = properties.getProperty("redis.host", "127.0.0.1");
			JEDIS_PORT = Integer.parseInt(properties.getProperty("redis.port",
					"6379"));
			JEDIS_PASSWORD = properties.getProperty("redis.pass", null);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取配置信息失败！");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(300);
		config.setTestOnBorrow(false);
		// config.setMaxWaitMillis(1000);
		jedisPool = new JedisPool(config, JEDIS_IP, JEDIS_PORT, 60000);
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			value = jedis.get(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return value;
	}

	private static void close(Jedis jedis) {
		try {
			jedisPool.returnResource(jedis);
		} catch (Exception e) {
			if (jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}

	public static byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			value = jedis.get(key);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}

		return value;
	}

	public static void set(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.set(key, value);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	public static void set(byte[] key, byte[] value, int time) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.set(key, value);
			jedis.expire(key, time);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	public static void hset(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hset(key, field, value);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hset(key, field, value);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String hget(String key, String field) {

		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			value = jedis.hget(key, field);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}

		return value;
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] hget(byte[] key, byte[] field) {

		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			value = jedis.hget(key, field);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}

		return value;
	}

	public static void hdel(byte[] key, byte[] field) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hdel(key, field);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	/**
	 * 存储REDIS队列 顺序存储
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 */
	public static void lpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.lpush(key, value);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	/**
	 * 存储REDIS队列 反向存储
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 */
	public static void rpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.rpush(key, value);

		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {
			close(jedis);

		}
	}

	/**
	 * 
	 * 
	 * @param key
	 * @param destination
	 */
	public static List<byte[]> blpop(byte[] key) {

		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			return jedis.blpop(key);

		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return null;

		} finally {

			close(jedis);

		}
	}

	/**
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
	 * 
	 * @param key
	 *            reids键名
	 * @param destination
	 *            键值
	 */
	public static void rpoplpush(byte[] key, byte[] destination) {

		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.rpoplpush(key, destination);

		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
	}

	/**
	 * 获取队列数据
	 * 
	 * @param key
	 *            键名
	 * @return
	 */
	public static List<byte[]> lpopList(byte[] key, long end) {

		List list = new ArrayList();
		;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			while (end != 0) {
				list.add(jedis.lpop(key));
				end--;
			}
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
		return list;
	}

	/**
	 * 获取队列数据
	 * 
	 * @param key
	 *            键名
	 * @return
	 */
	public static byte[] rpop(byte[] key) {

		byte[] bytes = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			bytes = jedis.rpop(key);

		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);

		}
		return bytes;
	}

	/**
	 * 
	 * 
	 * @Title: @Description: TODO @param timeout @param key @return @return:
	 *         List<String> @author: Administrator @date: 2018-2-24 上午11:07:24 @throws
	 */

	public static List<String> brpop(int timeout, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.brpop(timeout, key);
		} catch (Exception e) {

			return null;
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {

				}
			}
		}
	}

	public static void hmset(Object key, Map hash) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hmset(key.toString(), hash);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);

		}
	}

	public static void hmset(Object key, Map hash, int time) {
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hmset(key.toString(), hash);
			jedis.expire(key.toString(), time);
		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
	}

	public static Smtp hmget(String key, String fields) {
		Smtp smtp = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
		
			smtp = (Smtp) ObjectUtil.bytes2Object(jedis.hget(key.getBytes(),
					fields.getBytes()));

		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
		return smtp;

	}

	public static Set hkeys(String key) {
		Set result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			result = jedis.hkeys(key);

		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
		return result;
	}

	public static List lrange(byte[] key, int from, int to) {
		List result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			result = jedis.lrange(key, from, to);

		} catch (Exception e) {

			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);

		}
		return result;
	}

	public static Map hgetAll(byte[] key) {
		Map result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			close(jedis);
		}
		return result;
	}

	public static void del(byte[] key) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.del(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
	}

	public static long llen(byte[] key) {

		long len = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			len = jedis.llen(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return len;
	}

	public static void hmset(String key, Map<byte[], byte[]> map) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hmset(key.getBytes(), map);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}

	}

	public static void hmset(String key, String mac, Smtp smtp) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			jedis.hset(key.getBytes(), mac.getBytes(),
					ObjectUtil.object2Bytes(smtp));
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}

	}

	public static List<Smtp> hvals(String key) {

		Jedis jedis = null;
		List<Smtp> list = new ArrayList<Smtp>();

		try {
			jedis = jedisPool.getResource();
			jedis.auth(JEDIS_PASSWORD);
			List<byte[]> byteList = jedis.hvals(key.getBytes());

			for (int i = 0; i < byteList.size(); i++) {

				list.add((Smtp) ObjectUtil.bytes2Object(byteList.get(i)));

			}

		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {

			close(jedis);
		}
		return list;

	}

}