/*
 * File Name: IpPortUtil.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-8-31

 * Modifier: Wuwuhao
 * Modify Date: 2016-8-31
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.rrt.adp.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年5月26日
 * 
 */
public class IpPortUtil {
	
	public static final String SERVER_IP = serverIp();
			
	private static String serverIp(){
		String localip = null;
		String netip = null;
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress()&& !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
		
	public static Integer getAvailablePort(int port){
		ServerSocket socket = null;
		for(int i=0; i<2048; i++){
			try {
				socket = new ServerSocket(port+i);
			} catch (Exception e) { 
				
			} finally{
				if(null!=socket){
					try {
						socket.close();
						return port+i;
					} catch (Exception e2) {
						
					}
				}
			}
		}
		
		return null;
	}

}
