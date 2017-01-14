package com.chinaums.ucfa.util;

/**
 * 懒汉单例
 * 
 *
 * @param <T>
 */
public abstract class LazySingletonUtils<T> {

    private T instance;

    protected abstract T newInstance();

    /**
     * 获取实例
     * 
     * @return
     */
    public final T getInstance() {
        if (instance == null) {
            synchronized (LazySingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}