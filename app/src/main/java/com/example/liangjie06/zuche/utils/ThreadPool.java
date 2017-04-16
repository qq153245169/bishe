package com.example.liangjie06.zuche.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {
    private static ExecutorService sDynamicPool = null;
    private static ExecutorService sDynamicCriticalPool = null;

    private static ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1);

    private static Handler sUiHandler = null;
    private static HandlerThread sHandlerThread = null;
    private static Handler sWorkerHandler = null;
    private static final AtomicBoolean sIsStartedUp = new AtomicBoolean(false);

    public static void runOnPool(Runnable r) {
        sDynamicPool.execute(r);
    }

    public static Executor getPoolExecutor() {
        return sDynamicPool;
    }

    public static void runOnUi(Runnable r) {
        if (sUiHandler == null) {
            return;
        }
        sUiHandler.post(r);
    }

    public static void postOnUiDelayed(Runnable r, int delay) {
        if (sUiHandler == null) {
            return;
        }

            sUiHandler.postDelayed(r, delay);
    }

    public static void runCriticalTask(Runnable r) {
        sDynamicCriticalPool.execute(r);
    }

    public static void runOnWorker(Runnable r) {
        sWorkerHandler.post(r);
    }

    public static void postOnWorkerDelayed(Runnable r, int delay) {
        sWorkerHandler.postDelayed(r, delay);
    }

    public static Looper getWorkerLooper() {
        return sHandlerThread.getLooper();
    }

    public static Handler getWorkerHandler() {
        return sWorkerHandler;
    }

    /**
     * Schedule a runnable running at fixed rate
     */
    public static ScheduledFuture<?> schedule(Runnable r, long initialDelay, long period, TimeUnit unit) {
        return scheduledPool.scheduleAtFixedRate(r, initialDelay, period, TimeUnit.SECONDS);
    }

    public static void startup() {
        if (!sIsStartedUp.compareAndSet(false, true)) {
            return;
        }

        // standard pool runner
        sDynamicPool = Executors.newCachedThreadPool();

        // critical pool runner
        sDynamicCriticalPool = Executors.newCachedThreadPool();

        // ui thread runner
        sUiHandler = new Handler();

        // handler based thread runner
        sHandlerThread = new HandlerThread("internal");
        sHandlerThread.setPriority(Thread.NORM_PRIORITY - 1);
        sHandlerThread.start();
        sWorkerHandler = new Handler(sHandlerThread.getLooper());
    }

    public static void shutdown() {
        sHandlerThread.quit();
    }
}