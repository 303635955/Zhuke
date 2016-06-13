package com.yunguo.TenantModel;


import com.yunguo.TenantUtil.AppConfigUrlUtil;
import com.yunguo.TenantUtil.HttpPostUtil;

import android.os.Handler;

public class HouseMessageImpl implements HouseMessageModel{

	/**
	 * ˢ�·����б�
	 */
	@Override
	public String refreshHouseMessage(String paramStr,Handler handler) {
		String urlStr = AppConfigUrlUtil.GetConfig("urlKey");
		return HttpPostUtil.PostStringToUrl(urlStr, paramStr);
	}
}
