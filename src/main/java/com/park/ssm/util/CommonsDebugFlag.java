package com.park.ssm.util;


/**
 * 用于读取当前系统是否处于debug状态
 * @author ASNPHXJ
 *
 */
public class CommonsDebugFlag {
	private static boolean debugFlag;
	
	/**
	 * 返回当前系统是否处于debug状态
	 * @return
	 */
	public static boolean isDebug() {
		return debugFlag;
	}


	static {
		String debugFlagStr=System.getProperty("debug.flag");
		if(debugFlagStr != null && Boolean.valueOf(debugFlagStr)) {
			debugFlag=true;
		}else {
			debugFlag=false;
		}
	}
}
