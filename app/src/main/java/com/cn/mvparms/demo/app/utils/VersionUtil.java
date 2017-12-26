package com.cn.mvparms.demo.app.utils;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class VersionUtil {
	/**
	 * 获取版本号
	 * 
	 * @param type
	 *            =true 返回版本名称 type=false 返回版本code
	 * @return
	 */
	public static String getVersion(Context context, boolean type) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String versionName = info.versionName;
			int versionCode = info.versionCode;
			if (type)
				return versionName;
			else
				return versionCode + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "100.0.0";
		}
	}

	/** 获取渠道包 */
	public static String getChannelPackageName(Context context) {
		try {
			return context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData
					.getString("UMENG_CHANNEL");
		} catch (Exception e) {
			e.printStackTrace();
			return "XCS_WEBSIDE";
		}

	}

	/**
	 * 获取当前系统版本号（int）
	 */
	public static int getSystemVersion() {
		return android.os.Build.VERSION.SDK_INT;

	}

	/**
	 * 获取当前系统版本名称(String)
	 */
	@SuppressWarnings("deprecation")
	public static String getSystemVersionName() {
		return android.os.Build.VERSION.SDK;

	}

	/** 获取屏幕分辨率 */
	public static String getSystemView(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
	}

	/** 获取手机ip地址 */
	public static String getLocalIpAddress(Context activity) {
		String ip = null;
		//使用WIFI获取
		WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
		if(wifiManager.isWifiEnabled()) { //WiFi开启,使用WiFi获取IP
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			ip = intToIp(ipAddress);
		} else { //使用GPS获取
			try {
				for (Enumeration<NetworkInterface> enNetI = NetworkInterface
						.getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
					NetworkInterface netI = enNetI.nextElement();
					for (Enumeration<InetAddress> enumIpAddr = netI
							.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (inetAddress instanceof Inet4Address
								&& !inetAddress.isLoopbackAddress()
								&& !inetAddress.isLinkLocalAddress()) {
							ip = inetAddress.getHostAddress().toString();
						}
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
				ip = "127.0.0.1";
			}
		}
		//检测IP是否合理
		return !StringUtils.isEmpty(ip) ? (StringUtils.checkIP(ip) ? ip : "127.0.0.1") : "127.0.0.1";
	}

	private static String intToIp(int ip) {
		{
			return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
		}
	}

	/** 获取用户网络类型 */
	public static String getNetworkInfo(Activity activity) {
		String type = "";
		ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			type = "未知";
		} else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
			type = "WIFI";
		} else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
			int subType = info.getSubtype();
			if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
					|| subType == TelephonyManager.NETWORK_TYPE_EDGE) {
				type = "2G";
			} else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
					|| subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
					|| subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
				type = "3G";
			} else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
				type = "4G";
			}
		}
		return type;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return 手机型号
	 */
	public static String gainPhoneModel() {

		return android.os.Build.MODEL;
	}

}
