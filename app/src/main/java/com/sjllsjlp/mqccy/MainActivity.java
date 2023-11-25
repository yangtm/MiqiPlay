package com.sjllsjlp.mqccy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MainActivity extends Activity {
    private Button btn_stimulate_video;
    private TextView tv_video_count;
    private TextView tv_last_play_time;
    private TextView tv_user_id;

    private RewardVideoAD rewardVideoAD;

    //定义本地存储的Sp文件名
    private static final String SP_NAME = "reward_video_sp";
    //定义本地存储的Sp文件中的key
    private static final String KEY_VIDEO_COUNT = "video_count";
    private static final String KEY_LAST_PLAY_TIME = "last_play_time";
    //定义本地存储的Sp文件中的key，存储用户id
    private static final String KEY_USER_ID = "user_id";
    //定义本地存储类
    private SharedPreferences sp;

    // 定义一个变量，标识播放完成的handler message
    private static final int MSG_PLAY_COMPLETE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        btn_stimulate_video = findViewById(R.id.btn_stimulate_video);
        tv_video_count = findViewById(R.id.tv_video_count);
        tv_last_play_time = findViewById(R.id.tv_last_play_time);
        tv_user_id = findViewById(R.id.tv_user_id);
        //生成一个数字串作为用户的唯一标识，随机生成保障唯一
        //如果本地Sp中已经有了用户id，就不再生成，直接显示
        String userId = sp.getString(KEY_USER_ID, null);
        if (userId!= null && userId != "") {
            tv_user_id.setText("用户id：" + sp.getString(KEY_USER_ID, null));
        } else {
            userId = generateUserId();
            //将用户id存储到本地Sp中
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(KEY_USER_ID, userId);
            editor.apply();
        }
        //将用户id显示到页面中
        tv_user_id.setText("用户id：" + userId);
        //显示用户观看视频的次数和最后一次播放视频的时间
        tv_video_count.setText(sp.getInt(KEY_VIDEO_COUNT, 0) + "/20");
        tv_last_play_time.setText("上次观看视频时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(sp.getLong(KEY_LAST_PLAY_TIME, 0))));
    }

    //定义一个随机生成的用户id的方法，试用随机生成的数字串作为用户的唯一标识
    private String generateUserId() {
        // 定义生成随机数的范围
        int max = 100000000;
        int min = 1;
        // 生成一个随机数
        int randomNum = (int) (Math.random() * (max - min) + min);
        // 将随机数转换为字符串
        return String.valueOf(randomNum);
    }

    //showStimulateVideo事件
    public void showStimulateVideo(View view) {
        //如果超过20次，提示用户不能再观看
        if (sp.getInt(KEY_VIDEO_COUNT, 0) >= 20) {
            Toast.makeText(this, "今日观看次数已达上限", Toast.LENGTH_SHORT).show();
            return;
        }
        showYouLiangAd();
    }

    private void showYouLiangAd() {

        rewardVideoAD = new RewardVideoAD(this, "4097958464414325", new RewardVideoADListener() {
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
                //激励视频播放完毕，给予奖励,记录用户观看视频的次数
//                Toast.makeText(MainActivity.this, "激励视频播放完毕", Toast.LENGTH_SHORT).show();
                //将用户观看视频的次数和最后一次播放视频的时间记录到本地Sp中
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(KEY_VIDEO_COUNT, sp.getInt(KEY_VIDEO_COUNT, 0) + 1);
                editor.putLong(KEY_LAST_PLAY_TIME, System.currentTimeMillis());
                editor.apply();

                //发送消息，更新页面中的次数和时间
                handler.sendEmptyMessage(MSG_PLAY_COMPLETE);
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
                Toast.makeText(MainActivity.this, "广告加载失败,请稍后5s后再次点击", Toast.LENGTH_SHORT).show();
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

    //定义Handler，用于处理线程间的通信，更新页面中的次数和时间
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_PLAY_COMPLETE:
                    //获取本地Sp中的数据
                    int videoCount = sp.getInt(KEY_VIDEO_COUNT, 0);
                    long lastPlayTime = sp.getLong(KEY_LAST_PLAY_TIME, 0);
                    //更新页面中的次数和时间
                    tv_video_count.setText(videoCount + "/20");
                    tv_last_play_time.setText("上次观看视频时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastPlayTime)));
                    break;
            }
        }
    };
}