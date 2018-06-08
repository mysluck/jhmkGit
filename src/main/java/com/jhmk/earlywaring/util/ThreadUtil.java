//package com.jhmk.earlywaring.util;
//
//
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class ThreadUtil {
//    public static ThreadPool instance;
//    private ThreadPoolExecutor longExecutor;
//    private ThreadPoolExecutor shortExecutor;
//
//    public static ThreadPool getInstance() {
//        if (instance == null) {
//            synchronized (ThreadUtil.class) {
//                if (instance == null) {
//                    int cpuNum = Runtime.getRuntime().availableProcessors();//获取处理器储量
//                    int threadNum = cpuNum * 2 + 1;//根据cpu数量，计算出合理的线程并发数
//                    instance = new ThreadPool(threadNum - 1, threadNum, Integer.MAX_VALUE);
//                }
//            }
//        }
//        return instance;
//    }
//
//
//    public static class ThreadPool {
//        private ThreadPoolExecutor mExecutor;
//        private int corePoolSize;
//        private int maximunPoolSize;
//        private long keepAliveTime;
//
//        private ThreadPool(int corePoolSize, int maximunPoolSize, long keepAliveTime) {
//            this.corePoolSize = corePoolSize;
//            this.keepAliveTime = keepAliveTime;
//            this.maximunPoolSize = maximunPoolSize;
//        }
//
//        public void execute(Runnable runnable) {
//            if (runnable == null) {
//                return;
//            }
//            if (mExecutor == null) {
//                mExecutor = new ThreadPoolExecutor(corePoolSize, maximunPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
//                        new LinkedBlockingDeque<>(Integer.MAX_VALUE),
//                        new ThreadPoolExecutor.AbortPolicy() {
//                            @Override
//                            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
//                                super.rejectedExecution(r, e);
//                            }
//                        });
//            }
//            mExecutor.execute(runnable);
//        }
//
//        public void cancal(Runnable runnable) {
//            if (mExecutor != null) {
//                mExecutor.getQueue().remove(runnable);
//            }
//        }
//    }
//
//
//}
