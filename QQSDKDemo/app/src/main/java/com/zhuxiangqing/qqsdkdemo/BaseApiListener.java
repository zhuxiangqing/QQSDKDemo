package com.zhuxiangqing.qqsdkdemo;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class BaseApiListener implements IRequestListener {

	private String mScope = "all";
	private Boolean mNeedReAuth = false;
	private Activity mActivity;
	public BaseApiListener(String scope, boolean needReAuth,Activity activity) {
		mScope = scope;
		mNeedReAuth = needReAuth;
		mActivity = activity;
	}

	private static class BaseApiHandler extends Handler{
		private WeakReference<Activity> activityWeakReference;

		public BaseApiHandler(WeakReference<Activity> activityWeakReference) {
			this.activityWeakReference = activityWeakReference;
		}

		@Override
		public void handleMessage(Message msg) {
			Bundle params = msg.getData();
			String title = params.getString("title");
			String response = params.getString("response");
			Util.showResultDialog(activityWeakReference.get(), response, title);
			super.handleMessage(msg);
			Util.dismissDialog();
		}
	}
	private Handler mHandler = new BaseApiHandler(new WeakReference<Activity>(mActivity));
	
	@Override
	public void onComplete(final JSONObject response) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", response.toString());
		params.putString("title", "onComplete");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onIOException(final IOException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onIOException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onMalformedURLException(final MalformedURLException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onMalformedURLException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onJSONException(final JSONException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onJSONException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onConnectTimeoutException(ConnectTimeoutException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onConnectTimeoutException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onSocketTimeoutException(SocketTimeoutException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onSocketTimeoutException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onUnknowException(Exception e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onUnknowException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onHttpStatusException(HttpStatusException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onHttpStatusException");
		mHandler.sendMessage(msg);
	}

	@Override
	public void onNetworkUnavailableException(NetworkUnavailableException e) {
		Message msg = new Message();
		Bundle params = new Bundle();
		params.putString("response", e.getMessage());
		params.putString("title", "onNetworkUnavailableException");
		mHandler.sendMessage(msg);
	}

	public String getmScope() {
		return mScope;
	}

	public void setmScope(String mScope) {
		this.mScope = mScope;
	}

	public Boolean getmNeedReAuth() {
		return mNeedReAuth;
	}

	public void setmNeedReAuth(Boolean mNeedReAuth) {
		this.mNeedReAuth = mNeedReAuth;
	}

	public Activity getmActivity() {
		return mActivity;
	}

	public void setmActivity(Activity mActivity) {
		this.mActivity = mActivity;
	}

}
