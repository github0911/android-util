package com.chinaums.ucfa.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 *
 */
public class SPUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "share_data";

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param key
	 * @param object
	 * @param fileName
	 */
	public static void put(Context context, String key, Object object, String fileName) {

		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}

		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void put(Context context, String key, Object object) {
		put(context, key, object, FILE_NAME);
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param key
	 * @param defaultVal
	 * @param fileName
	 * @return
	 */
	public static Object get(Context context, String key, Object defaultVal, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);

		if (defaultVal instanceof String) {
			return sp.getString(key, (String) defaultVal);
		} else if (defaultVal instanceof Integer) {
			return sp.getInt(key, (Integer) defaultVal);
		} else if (defaultVal instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultVal);
		} else if (defaultVal instanceof Float) {
			return sp.getFloat(key, (Float) defaultVal);
		} else if (defaultVal instanceof Long) {
			return sp.getLong(key, (Long) defaultVal);
		}

		return null;
	}
	
	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static Object get(Context context, String key, Object defaultVal) {
		return get(context, key, defaultVal, FILE_NAME);		
	}

	/**
	 * 移除某个key值已经对应的值
	 * 
	 * @param context
	 * @param key
	 * @param fileName
	 */
	public static void remove(Context context, String key, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}
	
	/**
	 * 移除某个key值已经对应的值
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key) {
		remove(context, key, FILE_NAME);
		
	}
	
	/**
	 * 清除所有数据
	 * 
	 * @param context
	 * @param fileName
	 */
	public static void clear(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 清除所有数据
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		clear(context, FILE_NAME);		
	}
	
	/**
	 * 查询某个key是否已经存在
	 * 
	 * @param context
	 * @param key
	 * @param fileName
	 * @return
	 */
	public static boolean contains(Context context, String key, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}
	
	/**
	 * 查询某个key是否已经存在
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String key) {
		return contains(context, key, FILE_NAME);
	}

	/**
	 * 返回所有的键值对
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static Map<String, ?> getAll(Context context, String fileName) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}
	
	/**
	 * 返回所有的键值对
	 * 默认文件名{@link #FILE_NAME}
	 * 
	 * @param context
	 * @return
	 */
	public static Map<String, ?> getAll(Context context) {
		return getAll(context, FILE_NAME);
	}

	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 *  
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 * 
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 * 
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}

}
