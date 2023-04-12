package com.codetime.myweb.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Component
public class QuartzThreadPoolConfig {

    @Bean(value = "threadPoolTaskExecutor")
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程
        executor.setCorePoolSize(5);
        // 线程池最大值
        executor.setMaxPoolSize(10);
        // 允许的空闲时间
        executor.setKeepAliveSeconds(60);
        // 缓存队列（阻塞队列）
        executor.setQueueCapacity(10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("threadPool-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
