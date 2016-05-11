package com.rxjava_niupai_lqtj;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;


import com.rxjava_niupai_lqtj.utils.L;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by xuxairong on 2016/5/7.
 */
public class CrashHandler implements UncaughtExceptionHandler {

	private App app;
	// 系统默认的UncaughtException处理类
	private UncaughtExceptionHandler mDefaultHandler;
	// CrashHandler实例
	private static CrashHandler INSTANCE = new CrashHandler();
	// 程序的Context对象
	private Context mContext;
	// 上传错误信息的实例
//	private FeedBackHandler handler;

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		app = (App) context.getApplicationContext();
//		handler = new FeedBackHandler(context);
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void uncaughtException(Thread thread, Throwable error) {
		L.e("CrashHandler_ This is:" + thread.getName() + ",Message:"
				+ error.getMessage());
		error.printStackTrace();
		if (!handleException(error) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, error);
			// 退出程序
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				L.e("CrashHandler sleepError : "+ e);
			}
		}
		app.exit();

	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true代表处理该异常，不再向上抛异常
	 *         false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return true;
		}
//		handler.setUp_title("系统崩溃");
//		handler.setUp_msg(ex.toString());
//		handler.writErro();
		ex.printStackTrace();
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, mContext.getResources().getString(R.string.crash_handler_exit), Toast.LENGTH_LONG)
						.show();
				Looper.loop();
			}
		}.start();
		return true;
	}

}
