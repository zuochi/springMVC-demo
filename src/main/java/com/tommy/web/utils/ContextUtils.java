package com.tommy.web.utils;

/**
 * 将上下文存放于threadlocal中
 */
public class ContextUtils {
    public static Context getContext() {
        return threadLocal.get();
    }

    public static void removeContext() {
        threadLocal.remove();
    }

    private static ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

}
