package com.rxjava_niupai_lqtj.myui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.rxjava_niupai_lqtj.App;
import com.rxjava_niupai_lqtj.R;
import com.rxjava_niupai_lqtj.base.BaseActivity;
import com.rxjava_niupai_lqtj.network.manager.MyRetrofitManager;

import butterknife.Bind;


public class WebViewActivity extends BaseActivity {

//	@Bind(R.id.appheader_img_back)
//	ImageView appheader_img_back;
//	@Bind(R.id.appheader_txt_title)
//	TextView appheader_txt_title;
//	@Bind(R.id.appheader_img_right)
//	ImageView appheader_img_right;
	@Bind(R.id.progress)
	ProgressBar progressBar;
	@Bind(R.id.webview)
	WebView webView;

	private Context mContext;
	private App app;
	private String headerTitle;
	private String url;



	public static void start(Context context, String title,String url) {
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("url", MyRetrofitManager.BASE_IMAGE_URL+url);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	protected void afterCreate(Bundle savedInstanceState) {
		mContext = this;
		app = (App) getApplication();
		app.addClearActivity(this);
		initData();
	}

	private void initData() {
		
		headerTitle=getIntent().getStringExtra("title");
		WebSettings webSet = webView.getSettings();
		webSet.setJavaScriptEnabled(true);
		url = getIntent().getStringExtra("url");
		webView.loadUrl(getIntent().getStringExtra("url"));
		webView.requestFocus();
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
					webView.goBack();
					return true;
				}
				return false;
			}
		});
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				// Activity和Webview根据加载程度决定进度条的进度大小
				// 当加载到100%的时候 进度条自动消失
				progressBar.setProgress(progress);
				if (progress == 100) {
					progressBar.setVisibility(View.GONE);
				} else {
					if (progressBar.getVisibility() == View.GONE)
						progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(progress);
				}
				super.onProgressChanged(view, progress);
			}

//			@Override
//			public void onReceivedTitle(WebView view, String title) {
//				super.onReceivedTitle(view, title);
//				appheader_txt_title.setText(headerTitle);
//
//			}
		});

//		appheader_txt_title.setText(headerTitle);
//		appheader_img_back.setVisibility(View.VISIBLE);
//		appheader_img_back.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				if (webView.canGoBack()) {
//					webView.goBack(); // goBack()表示返回webView的上一页面
//				}else{
//					finish();
//				}
//			}
//		});
//		appheader_img_right.setVisibility(View.VISIBLE);
//		appheader_img_right.setImageResource(R.drawable.img_close);
//		appheader_img_right.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				finish();
//			}
//		});
	}
	public boolean onKeyDown(int keyCoder, KeyEvent event) {
		super.onKeyDown(keyCoder, event);
		if (webView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
			webView.goBack(); // goBack()表示返回webView的上一页面
		}
		return true;
	}
//	@Override
//	protected void onPause() {
//		super.onPause();
//		MobclickAgent.onPause(this);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		MobclickAgent.onResume(this);
//	}
}
