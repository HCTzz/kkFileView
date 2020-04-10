package com.ctt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilePreviewApplicationTests {

	@Resource
	public com.alibaba.druid.pool.DruidDataSource dataSource;

	@Test
	public void contextLoads() throws InterruptedException {
//		Config config = new Config();
//		RedissonClient client = Redisson.create(config);
//		client.getLock("");
//		System.out.println(dataSource.getClass());


		Config config = new Config();
		config.setLockWatchdogTimeout(10000);
		config.setNettyThreads(3);
		config.setExecutor(new ThreadPoolExecutor(10, 200, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(10)));
		config.useSingleServer().setAddress("redis://192.168.1.229:6379")
				.setConnectionPoolSize(128)
				.setPassword("123456").setConnectTimeout(5000).setTimeout(3000);
		RedissonClient client = Redisson.create(config);
		RLock lock = client.getLock("1234");
		lock.tryLock(1,TimeUnit.MILLISECONDS);
	}

}
