package com.example.chhavi.swiftintern.Utility;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;
	public static GoogleAnalytics analytics;
	public static Tracker tracker;
	private static AppController mInstance;
	public static Tracker tracker() {
		return tracker;
	}
	public static GoogleAnalytics analytics() {
		return analytics;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	/*	FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Raleway-Regular.ttf");
		FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Raleway-Regular.ttf");
		FontsOverride.setDefaultFont(this, "SERIF", "fonts/Raleway-Regular.ttf");
		FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Raleway-Regular.ttf");*/
		analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);

		tracker = analytics.newTracker("UA-49272936-4"); // Replace with actual tracker/property Id
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
