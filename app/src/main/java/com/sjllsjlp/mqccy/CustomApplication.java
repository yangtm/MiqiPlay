package com.sjllsjlp.mqccy;

import android.app.Application;
import android.location.Location;

import com.qq.e.comm.managers.GDTAdSdk;
import com.windmill.sdk.WMAdConfig;
import com.windmill.sdk.WMAdnInitConfig;
import com.windmill.sdk.WMCustomController;
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
        //GDTAdSdk.init(applicationContext, "1205843912");
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
        WMNetworkConfig.Builder builder = (new WMNetworkConfig.Builder());
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.ADMOB))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.REKLAMUP))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.VUNGLE, "appId"))//异步
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.UNITYADS, "appId"))//异步
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.IRONSOURCE, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.TOUTIAO, "appId"))
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.GDT, "1205843912"))
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
//                .addInitConfig(new WMAdnInitConfig(WMNetworkConfig.TAPTAP, "1004470", "Tyat3LaCGFiBU5PS9Nrr7TFu3QzSjhvnKGjlMeyTTj21uQxcM8U70Bx8LcVhREJ7"));

//        ads.setInitNetworkConfig(builder.build());

        //ads.startWithAppId(this, "32737");
        ads.startWithAppId(this, "32737", new WMAdConfig.Builder().customController(new WMCustomController() {
            /**
             * 是否允许SDK主动使用地理位置信息
             *
             * @return true可以获取，false禁止获取。默认为true
             */
            @Override
            public boolean isCanUseLocation() {
                return super.isCanUseLocation();
            }

            /**
             * 当isCanUseLocation=false时，可传入地理位置信息，ToBid使用您传入的地理位置信息
             *
             * @return 地理位置参数
             */
            @Override
            public Location getLocation() {
                return super.getLocation();
            }

            /**
             * 是否允许SDK主动使用手机硬件参数，如：imei
             *
             * @return true可以使用，false禁止使用。默认为true
             */
            @Override
            public boolean isCanUsePhoneState() {
                return super.isCanUsePhoneState();
            }

            /**
             * 当isCanUsePhoneState=false时，可传入imei信息，ToBid使用您传入的imei信息
             *
             * @return imei信息
             */
            @Override
            public String getDevImei() {
                return super.getDevImei();
            }

            /**
             * 是否允许SDK主动使用手机硬件参数，如：android
             *
             * @return true可以使用，false禁止使用。默认为true
             */
            @Override
            public boolean isCanUseAndroidId() {
                return super.isCanUseAndroidId();
            }

            /**
             * isCanUseAndroidId=false时，可传入android信息，ToBid使用您传入的android信息
             *
             * @return android信息
             */
            @Override
            public String getAndroidId() {
                return super.getAndroidId();
            }

            /**
             * 开发者可以传入oaid
             *
             * @return oaid
             */
            @Override
            public String getDevOaid() {
                return super.getDevOaid();
            }

            /**
             * 是否允许SDK主动获取设备上应用安装列表的采集权限
             *
             * @return true可以使用，false禁止使用。默认为true
             */
            @Override
            public boolean isCanUseAppList() {
                return super.isCanUseAppList();
            }

            /**
             * 是否允许SDK主动使用ACCESS_WIFI_STATE权限
             *
             * @return true可以使用，false禁止使用。默认为true
             */
            @Override
            public boolean isCanUseWifiState() {
                return super.isCanUseWifiState();
            }

            /**
             * isCanUseWifiState=false时，可传入MacAddress，ToBid使用您传入的MacAddress信息
             *
             * @return MacAddress参数
             */
            @Override
            public String getMacAddress() {
                return super.getMacAddress();
            }

            /**
             * 是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限
             *
             * @return true可以使用，false禁止使用。默认为true
             */
            @Override
            public boolean isCanUseWriteExternal() {
                return super.isCanUseWriteExternal();
            }

            /**
             * 是否允许SDK在申明和授权了的情况下使用录音权限
             * return true 允许 false 不允许
             *
             * @return
             */
            @Override
            public boolean isCanUsePermissionRecordAudio() {
                return super.isCanUsePermissionRecordAudio();
            }


        }).build());
    }
}
