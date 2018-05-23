package com.dq.huibao.utils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by d on 2018/5/18.
 */

public class WechatLoginUtil {
    public static void wechatLogin(PlatformActionListener var1){
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(var1);
        wechat.SSOSetting(false);
        wechat.showUser(null);
    }
}
