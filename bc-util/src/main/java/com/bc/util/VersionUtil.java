package com.bc.util;

public class VersionUtil {
	public static void main(String[] args) {
		System.out.println(isLastVersion("3.1.0", "3.2.9"));
	}
	/**
	 * 是否最新版本
	 * @return
	 */
	public static boolean isLastVersion(String appVersion , String onlineVersion){
		if (appVersion == null || onlineVersion == null) {
			return false;
		}
		String[] appVersionSubtrs = appVersion.split("\\.");
		String[] onlineVersionSubStrs = onlineVersion.split("\\.");
		if (appVersionSubtrs.length < 2) {
			return false;
		}
		if (onlineVersionSubStrs.length < 2) {
			return false;
		}

		try {
			int appHighNum = Integer.parseInt(appVersionSubtrs[0]);
			int onlineHighNum = Integer.parseInt(onlineVersionSubStrs[0]);
			if (onlineHighNum > appHighNum) {
				return true;
			} else if (onlineHighNum == appHighNum) {
				int appLowNum = Integer.parseInt(appVersionSubtrs[1]);
				int onlineLowNum = Integer.parseInt(onlineVersionSubStrs[1]);
				if (onlineLowNum > appLowNum) {
					return true;
				} else if (onlineLowNum == appLowNum) {
					try {
						if (appVersionSubtrs.length > 2 && onlineVersionSubStrs.length > 2) {
							appLowNum = Integer.parseInt(appVersionSubtrs[2]);
							onlineLowNum = Integer.parseInt(onlineVersionSubStrs[2]);
							if (onlineLowNum >= appLowNum) {
								return true;
							} else {return false;
								/*if (onlineVersionSubStrs.length > 3) {
									if (appVersionSubtrs.length > 3) {
										appLowNum = Integer.parseInt(appVersionSubtrs[3]);
										onlineLowNum = Integer.parseInt(onlineVersionSubStrs[3]);
										if (onlineLowNum > appLowNum) {
											return true;
										}
									} else {
										return true;
									}
*/
								//}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return false;
	}
}
