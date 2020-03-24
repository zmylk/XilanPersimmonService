package com.xilan.common.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

	/**
	 * @param httpUrl
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
//	        connection.setRequestProperty("showapi_sign",  JoddPropUtil.loadStatistic("weather", "weatherKeyId"));
//	        connection.setRequestProperty("showapi_appid",  JoddPropUtil.loadStatistic("weather", "showAppId"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
			System.out.println("HTTP请求"+httpUrl+"连接失败");
			result=null;
	    }
	    return result;
	}


	public String doPost(String url, Map<String,String> map) throws Exception{
		String result = null;
		HttpClient httpClient = new SSLClient();
		HttpPost httpPost = new HttpPost(url);
		//设置参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
			list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
		}
		if(list.size() > 0){
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entity);
		}
		HttpResponse response = httpClient.execute(httpPost);
		if(response != null){
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				result = EntityUtils.toString(resEntity, "UTF-8");
			}
		}
		return result;
	}
	public String doGet(String url, Map<String,String> map) throws Exception{
		String result = null;
		HttpClient httpClient = new SSLClient();
		String param="";
		for(String nameKey:map.keySet()){
			param += nameKey+"="+map.get(nameKey)+"&";
		}
		param = param.substring(0,param.length()-1);
		String urlNameString = url + "?" + param;
		System.out.println(urlNameString);
		HttpGet httpGet = new HttpGet(urlNameString);
		HttpResponse response = httpClient.execute(httpGet);
		if(response != null){
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				result = EntityUtils.toString(resEntity, "UTF-8");
			}
		}
		return result;
	}
	public String doPostXml(String url, String xml) throws Exception{
		String result = null;
		HttpClient httpClient = new SSLClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type","text/xml;charset=UTF-8");
		StringEntity stringEntity = new StringEntity(xml, "UTF-8");
		stringEntity.setContentEncoding("UTF-8");

		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);
		if(response != null){
			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				result = EntityUtils.toString(resEntity, "UTF-8");
			}
		}
		return result;
	}
    public static void main(String[] args) {

	}
}
