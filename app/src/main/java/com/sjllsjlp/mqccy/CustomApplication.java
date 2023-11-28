package com.sjllsjlp.mqccy;

import android.app.Application;

import com.qq.e.comm.managers.GDTAdSdk;
import com.windmill.sdk.WMAdnInitConfig;
import com.windmill.sdk.WMNetworkConfig;
import com.windmill.sdk.WindMillAd;
import com.windmill.sdk.WindMillConsentStatus;
import com.windmill.sdk.WindMillUserAgeStatus;

public class CustomApplication extends Application {
    private static CustomApplication applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        GDTAdSdk.init(applicationContext, "1205843912");

        try {
            System.loadLibrary("msaoaidsec");
        } catch (Throwable ignored) {

        }

        WindMillAd ads = WindMillAd.sharedAds();
        ads.setUserAge(18);
        ads.setAdult(true);//是否成年
        ads.setPersonalizedAdvertisingOn(true);//是否开启个性化推荐接口
        ads.setIsAgeRestrictedUser(WindMillUserAgeStatus.WindAgeRestrictedStatusNO);//coppa//是否年龄限制
        ads.setUserGDPRConsentStatus(WindMillConsentStatus.ACCEPT);//是否接受gdpr协议
        WMNetworkConfig.Builder builder = (new WMNetworkConfig.Builder())
                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.ADMOB))
                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.REKLAMUP))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.VUNGLE, "appId"))//异步
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.UNITYADS, "appId"))//异步
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.IRONSOURCE, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.TOUTIAO, "appId"))
                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.GDT, "1205843912"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.KUAISHOU, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.KLEVIN, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.BAIDU, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.GROMORE, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.ADSCOPE, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.QUMENG, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.PANGLE, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.APPLOVIN, "appKey"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.APPLOVIN_MAX, "appKey"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.MOBVISTA, "appId", "appKey"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.SIGMOB, "appId", "appKey"))
                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.TAPTAP, "1004470", "Tyat3LaCGFiBU5PS9Nrr7TFu3QzSjhvnKGjlMeyTTj21uQxcM8U70Bx8LcVhREJ7"));

        ads.setInitNetworkConfig(builder.build());

        ads.startWithAppId(this, "32737");

    }
}
