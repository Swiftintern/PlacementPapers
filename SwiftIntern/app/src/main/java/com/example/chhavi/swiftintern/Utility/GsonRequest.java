package com.example.chhavi.swiftintern.Utility;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {
	private Gson mGson = new Gson();
	private Class<T> clazz;
	private Map<String, String> headers;
	private Map<String, String> params;
	private Listener<T> listener;

	/**
	 * Make a GET request and return a parsed object from JSON.
	 * 
	 * @param url
	 *            URL of the request to make
	 * @param clazz
	 *            Relevant class object, for Gson's reflection
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.listener = listener;
		mGson = new GsonBuilder().disableHtmlEscaping().create();
	}

	/**
	 * Make a POST request and return a parsed object from JSON.
	 * 
	 * @param url
	 *            URL of the request to make
	 * @param clazz
	 *            Relevant class object, for Gson's reflection
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> params, Listener<T> listener,
			ErrorListener errorListener) {

		super(method, url, errorListener);
		this.clazz = clazz;
		this.params = params;
		this.listener = listener;
		this.headers = null;
		mGson = new Gson();
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params;
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			//Log.e("current", json);
			byte[] utf8 = json.getBytes("UTF-8");
			json = new String(utf8, "UTF-8");
			//json = Utils.correctJson(json);
			//Log.e("current", json);
			return Response.success(mGson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}
}