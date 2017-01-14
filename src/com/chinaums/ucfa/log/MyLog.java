package com.chinaums.ucfa.log;

import android.util.Log;

/**
 * 打印log日志
 * 首先要在application中初始化，调用init()方法
 * @author caijj
 *
 */
public class MyLog {
	
	private static String mTag = "chinaumslog";

	private static boolean mIsDebug = true;
	
	private MyLog() {
        throw new UnsupportedOperationException(MyLog.class.getName() + " cannot be instantiated");  
    }    
	
	/**
	 * 是否处理debug日志，主要用于是否显示日志
	 * @return
	 */
	static public boolean isDebugLog() {
		return mIsDebug;
	}
	
	/**
	 * 初始化日志工具类。可以在application中初始化
	 * 所有日志都只在isDebug为true才在logcat中打印出来
	 * 
	 * @param logTag 日志的mTag，默认为 chinaumslog
	 * @param isDebug app是否处理测试状态
	 */
	static public void init(String logTag, boolean isDebug) {
		mTag = logTag;
		mIsDebug = isDebug;
	}	
	
	/**
	 * 打印日志
	 * @param priority  The priority/type of this log message.
	 * @param msg 带有{}的信息字符串
	 * @param params 替换msg中的{}
	 */
	static public void println(int priority, String msg, Object... params) {
		if (mIsDebug) {
			for (Object param : params) {
				msg = msg.replaceFirst("\\{\\}", param.toString());
			}
			Log.println(priority, mTag, msg);
		}
	}

	/**
	 * 打印Log.DEBUG日志
	 * 
	 * @param msg 日志信息
	 * @see Log.DEBUG
	 */
	static public void d(String msg) {
		if (mIsDebug) {
			println(Log.DEBUG, msg);
		}
	}

	/**
	 * 打印Log.DEBUG日志
	 * 
	 * @param t Throwable
	 * @param msg 日志信息
	 * @see Log.DEBUG
	 */
	static public void d(String msg, Throwable t) {
		if (mIsDebug) {
			println(Log.DEBUG, msg);
			t.printStackTrace();
		}
	}

	/**
	 * 打印Log.DEBUG日志
	 * 
	 * @param msg 带有{}的信息字符串
	 * @param params 替换msg中的{}
	 * @see Log.DEBUG
	 */
	static public void d(String msg, Object... params) {
		if (mIsDebug) {
			println(Log.DEBUG, msg, params);
		}
	}

	/**
	 * 打印Log.INFO日志
	 * 
	 * @param msg 日志信息
	 * @see Log.INFO
	 */
	static public void i(String msg) {
		if (mIsDebug) {
			println(Log.INFO, msg);
		}
	}

	/**
	 * 打印Log.INFO日志
	 * 
	 * @param t Throwable
	 * @param msg 日志信息
	 * @see Log.INFO
	 */
	static public void i(String msg, Throwable t) {
		if (mIsDebug) {
			println(Log.INFO, msg);
			t.printStackTrace();
		}
	}

	/**
	 * 打印Log.INFO日志
	 * 
	 * @param msg 带有{}的信息字符串
	 * @param params 替换msg中的{}
	 * @see Log.INFO
	 */
	static public void i(String msg, Object... params) {
		if (mIsDebug) {
			println(Log.INFO, msg, params);
		}
	}

	/**
	 * 打印Log.ERROR日志
	 * 
	 * @param msg 日志信息
	 * @see Log.ERROR
	 */
	static public void e(String msg) {
		if (mIsDebug) {
			println(Log.ERROR, msg);
		}
	}

	/**
	 * 打印Log.ERROR日志
	 * 
	 * @param t Throwable
	 * @param msg 日志信息
	 * @see Log.ERROR
	 */
	static public void e(String msg, Throwable t) {
		if (mIsDebug) {
			println(Log.ERROR, msg);
			t.printStackTrace();
		}
	}

	/**
	 * 打印Log.ERROR日志
	 * 
	 * @param msg 带有{}的信息字符串
	 * @param params 替换msg中的{}
	 * @see Log.ERROR
	 */
	static public void e(String msg, Object... params) {
		if (mIsDebug) {
			println(Log.ERROR, msg, params);
		}
	}

}
