package com.weng.demo.springbootelasticsearch.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @DATE: 2022/2/21 3:37 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ThreadPoolUtil {
    /**
     * 队列最大数量
     */
    public static final int QUEUE_MAX_NUM = 5000;

    /**
     * 当前服务的线程池工程名称
     */
    private static final String THIS_FACTORY_NAME = "springboot-elasticsearch";

    /**
     * 当前业务线程名称
     */
    private static final String CREATE_INDEX_NAME = "create_index";
    /**
     * 当前业务线程名称
     */
    private static final String UPDATE_INDEX_NAME = "update_index";


    /**
     * 新建一个消费创建索引队列的创建索引线程池
     *
     * @return
     */
    public static ExecutorService newCreateIndexSingleThreadPool() {

      return   new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(QUEUE_MAX_NUM),
                new ThreadDefaultFactory(THIS_FACTORY_NAME, CREATE_INDEX_NAME));

    }

    /**
     * 新建一个消费更新索引队列的更新索引线程池
     *
     * @return
     */
    public static ExecutorService newUpdateIndexSingleThreadPool() {
       return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(QUEUE_MAX_NUM),
                new ThreadDefaultFactory(THIS_FACTORY_NAME, UPDATE_INDEX_NAME));

    }

    public static class ThreadDefaultFactory implements ThreadFactory {
        /**
         * 线程池名称前缀
         */
        private StringBuffer namePrefix = new StringBuffer();

        /**
         * 线程数量的计数器
         */
        private final AtomicInteger nextId = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(null, r, namePrefix.toString() + nextId.getAndIncrement(), 0);
        }

        /**
         * 用于打印日志时输出线程名称
         * @param factoryName
         * @param businessName
         */
        public ThreadDefaultFactory(String factoryName, String businessName) {
                namePrefix.append("pool-").append(factoryName).append("-").append(businessName).append("-Worker-");
        }
    }

}
