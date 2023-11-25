package com.sjllsjlp.mqccy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;

import java.util.Map;

public class StimulateVideoActivity extends Activity {
    //广点通激励视频
    private RewardVideoAD rewardVideoAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulate_video);
        showYouLiangAd();
    }


    private void showYouLiangAd() {

        rewardVideoAD = new RewardVideoAD(this, "4097958464414325 ", new RewardVideoADListener() {
            @Override
            public void onADLoad() {
                if (rewardVideoAD != null) {
                    showGDTAd(rewardVideoAD);
                }
            }

            @Override
            public void onVideoCached() {

            }

            @Override
            public void onADShow() {

            }

            @Override
            public void onADExpose() {

            }

            @Override

            public void onReward(Map map) {

            }

            @Override
            public void onADClick() {

            }

            @Override

            public void onVideoComplete() {

            }

            @Override
            public void onADClose() {

            }

            @Override
            public void onError(AdError adError) {

            }

        }, true);

        rewardVideoAD.loadAD();

    }

    /**
     * 展示激励视频 放到onADLoad中回调
     */

    public void showGDTAd(RewardVideoAD rewardVideoAD) {
        if (rewardVideoAD != null) {//广告展示检查1：广告成功加载，此处也可以使用videoCached来实现视频预加载完成后再展示激励视频广告的逻辑
            if (!rewardVideoAD.hasShown()) {//广告展示检查2：当前广告数据还没有展示过
                //广告展示检查3：展示广告前判断广告数据未过期
                if (rewardVideoAD.isValid()) {
                    rewardVideoAD.showAD();
                } else {
                    Toast.makeText(this, "激励视频广告已过期，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "此条广告已经展示过，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "成功加载广告后再进行广告展示！", Toast.LENGTH_LONG).show();
        }
    }
}