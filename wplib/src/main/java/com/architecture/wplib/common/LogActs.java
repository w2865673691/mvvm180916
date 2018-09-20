package com.architecture.wplib.common;

import android.annotation.SuppressLint;
import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public final class LogActs {

    /**
     * 获取文件名
     *
     * @return
     */
    public static String getFileName() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getFileName();
    }

    /**
     * 获取函数名
     *
     * @return
     */
    public static String getFunctionName() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getMethodName();
    }

    /**
     * 获取行号
     *
     * @return
     */
    public static int getLineNumber() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    /**
     * 获取时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        Date now = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(now);
    }

    private static String buildLog() {
        StackTraceElement[] stacktrace = (new Exception()).getStackTrace();// Thread.currentThread().getStackTrace();
        StackTraceElement traceElement = stacktrace[2];
        StringBuilder stringBuilder = new StringBuilder("[")
                .append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getMethodName()).append("() | ")
                .append(traceElement.getLineNumber()).append("]");
        String TAG = stringBuilder.toString();
        return TAG;
    }

    public static void v(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_V) {
            String TAG = buildLog();
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_D) {
            String TAG = buildLog();
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_I) {
            String TAG = buildLog();
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_W) {
            String TAG = buildLog();
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_E) {
            String TAG = buildLog();
            Log.e(TAG, msg);
        }
    }

    public static void a(String msg) {
        if (Constants.Net.LOG_ALL && Constants.Net.LOG_E) {
            String TAG = buildLog();
            logAll(TAG, msg);
        }
    }

    private static final int LOG_MAXLENGTH = 2000;

    public static void logAll(String type, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        String head = "-->";
        for (int i = 0; i < 100; i++) {
            if (i == 0) {
                head = "";
            } else {
                head = "-->";
            }
            if (strLength > end) {
                Log.d(type + "-" + i, head + msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.d(type + "-" + i, head + msg.substring(start, strLength));
                break;
            }
        }
    }
}
