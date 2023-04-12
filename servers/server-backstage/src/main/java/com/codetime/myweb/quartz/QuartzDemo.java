package com.codetime.myweb.quartz;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class QuartzDemo {

    @Async("threadPoolTaskExecutor")
    @Scheduled(cron = "${define.quartz.cron}")
    public void schedule() {
        System.out.println(Thread.currentThread().hashCode());
    }

    @Async("threadPoolTaskExecutor")
    @Scheduled(cron = "${define.quartz.cron}")
    public void schedule2() {
        System.out.println(Thread.currentThread().hashCode());
    }

}
