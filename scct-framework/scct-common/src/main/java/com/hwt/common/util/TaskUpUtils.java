package com.hwt.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 后台异步任务工具类 保证所有调用此任务的方法只有queueNum个在执行
 * @author wds
 * @version 1.0
 * @date 2024/1/8 11:33
 */
public class TaskUpUtils {

    private static int activeTasks = 0;
    private static final Object lock = new Object();

    /**
     * 执行逻辑：每次调用该方法会向阻塞队列添加一个任务异步执行，
     * 当有未执行结束的任务数量达到queueNum时阻止下一个任务
     *
     * @param task   要执行的任务
     * @param queueNum 允许的最大同时执行的任务数
     */
    public static void addTask(Runnable task, int queueNum) {
        ExecutorService executor= Executors.newFixedThreadPool(10);
        if (activeTasks >= queueNum) {
            throw new RuntimeException("当前任务数已达最大并发数，请稍后再试");
        }
        executor.execute(() -> {
            try {
                task.run();
            } finally {
                synchronized (lock) {
                    activeTasks--;
                    if (activeTasks == 0) {
                        System.out.println("所有任务已完成，关闭线程池");
                        executor.shutdown();
                    }
                }
            }
        });

        synchronized (lock) {
            activeTasks++;
        }
    }
}
