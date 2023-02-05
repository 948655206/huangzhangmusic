package com.example.huangzhangmusic.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.huangzhangmusic.App;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class LocalIPUtils {
    private static Context mContext= App.Companion.getContext();

    //获取本地IP函数
    public static String getLocalIPAddress(){
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo=wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        try {
            String ip = InetAddress.getByName(String.format("%d.%d.%d.%d",
                    (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                    (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff))).toString();
            System.out.println("ip ==>"+ip);
            return ip;
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    public static String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

}
