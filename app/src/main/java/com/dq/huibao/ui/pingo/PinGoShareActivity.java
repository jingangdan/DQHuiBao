package com.dq.huibao.ui.pingo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.pingo.PinGoShareB;
import com.dq.huibao.utils.CountDownUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ImageUtils;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 拼go分享
 * Created by d on 2018/5/10.
 */

public class PinGoShareActivity extends BaseActivity {
    @Bind(R.id.pingo_share_time)
    TextView pingoShareTime;
    @Bind(R.id.pingo_share_diqu)
    TextView pingoShareDiqu;
    @Bind(R.id.pingo_share_people)
    TextView pingoSharePeople;
    @Bind(R.id.pingo_share_now_youhui)
    TextView pingoShareNowYouhui;
    @Bind(R.id.pingo_share_next_youhui)
    TextView pingoShareNextYouhui;
    @Bind(R.id.pingo_share_goodimage)
    ImageView pingoShareGoodimage;
    @Bind(R.id.pingo_share_goodname)
    TextView pingoShareGoodname;
    @Bind(R.id.pingo_share_option)
    TextView pingoShareOption;
    private String orderid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingo_share);
        ButterKnife.bind(this);
        setTitleName("下单成功");

        orderid = getIntent().getStringExtra("orderid");
        getData();

//        showShare();

    }

    @OnClick(R.id.pingo_share_share)
    public void onclick(){
        showShare();
    }

    /**
     * 获取数据
     */
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("mid", uidBase);
        map.put("id", orderid);
        HttpxUtils.Get(this, HttpPath.PINGO_SHARE, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("拼go下单成功", "拼go下单成功 = " + result);

                PinGoShareB goShareB = GsonUtil.gsonIntance().gsonToBean(result, PinGoShareB.class);
                updateUI(goShareB);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("拼go下单成功", "拼go下单成功 失败= " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    CountDownUtil countDownUtil;
    public void updateUI(PinGoShareB goShareB){
        countDownUtil = new CountDownUtil(Long.parseLong(goShareB.getData().getEndtime()+"") * 1000,pingoShareTime);
        countDownUtil.countdown();
        pingoShareDiqu.setText(goShareB.getData().getRegname() + "还差");
        pingoSharePeople.setText(goShareB.getData().getLastcount() + "人");
        if (goShareB.getData().getDistype().equals("zhe")){//折扣
            pingoShareNowYouhui.setText("第" + goShareB.getData().getNowcount() + "单可享" + goShareB.getData().getZhe() + "折优惠");
            pingoShareNextYouhui.setText(goShareB.getData().getNzhe() + "折(" + goShareB.getData().getNremoney() + "元)");
        }else {//立减
            pingoShareNowYouhui.setText("第" + goShareB.getData().getNowcount() + "单可享" + goShareB.getData().getRemoney() + "元优惠");
            pingoShareNextYouhui.setText(goShareB.getData().getNremoney() + "元");
        }
        if (goShareB.getData().getGoodslist().size() > 0){
            Glide.with(this)
                    .load(ImageUtils.getImagePath(goShareB.getData().getGoodslist().get(0).getThumb()))
                    .placeholder(R.mipmap.icon_empty002)
                    .into(pingoShareGoodimage);
            pingoShareGoodname.setText(goShareB.getData().getGoodslist().get(0).getGoodsname());
            pingoShareOption.setText("规格" + goShareB.getData().getGoodslist().get(0).getOptionname());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownUtil.stopThread();
    }

    /**
     * 分享
     */
    private void showShare() {
        Platform.ShareParams params = new Platform.ShareParams();
//        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        params.setShareType(Platform.SHARE_WEBPAGE);
//        params.setShareType(Platform.SHARE_IMAGE);//分享图片必须
//        params.setImageData(logo);
//        FileProvider.getUriForFile(this, "com.hb.fileprovider", new File());
        params.setTitle("ssss");
        params.setText("eeeee");
        params.setUrl("https://www.baidu.com");
        //FileProvider.getUriForFile(this, "com.hb.fileprovider", new File(newUri.getPath()));
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toast("分享成功");
                finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("fffffffffffffffffffff", "onerror==" + platform.getName() + "  code=" + i + "  errr=" + throwable.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("fffffffffffffffffffff", "取消==");
            }
        });
        wechat.share(params);
    }
}
