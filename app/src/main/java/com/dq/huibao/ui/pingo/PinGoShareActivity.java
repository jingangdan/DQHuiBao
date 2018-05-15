package com.dq.huibao.ui.pingo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
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
import com.dq.huibao.utils.ShareUtil;

import org.xutils.common.Callback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

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
    private String shareTitle = "",shareContent = "",imagePath = "",sharePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingo_share);
        ButterKnife.bind(this);
        setTitleName("分享");

        orderid = getIntent().getStringExtra("orderid");
        getData();

//        showShare();

    }

    @OnClick({R.id.pingo_share_share,R.id.pingo_share_share_quan,R.id.pingo_share_btn})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.pingo_share_share://微信好友分享
                showShare(Wechat.NAME);
                break;
            case R.id.pingo_share_share_quan://微信朋友圈
                showShare(WechatMoments.NAME);
                break;
            case R.id.pingo_share_btn://分享
                ShareUtil.shareDialog(this,shareTitle, shareContent, imagePath, sharePath);
                break;
        }
    }

    /**
     * 获取数据
     */
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("mid", uidBase);
        map.put("id", orderid);
        HttpxUtils.Get(this,HttpPath.PINGO_SHARE, map, new Callback.CommonCallback<String>() {
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
        sharePath = goShareB.getData().getShareurl();

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
            imagePath = ImageUtils.getImagePath(goShareB.getData().getGoodslist().get(0).getThumb());
            Glide.with(this)
                    .load(imagePath)
                    .placeholder(R.mipmap.icon_empty002)
                    .into(pingoShareGoodimage);
            pingoShareGoodname.setText(goShareB.getData().getGoodslist().get(0).getGoodsname());
            pingoShareOption.setText("规格:" + goShareB.getData().getGoodslist().get(0).getOptionname());
        }

        shareTitle = pingoShareDiqu.getText().toString() + pingoSharePeople.getText().toString();
        shareContent = "再来一人每人享受" + pingoShareNextYouhui.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtil != null){
            countDownUtil.stopThread();
        }
    }

    /**
     * 分享
     */
    private void showShare(String ss) {

        Platform wechat = ShareSDK.getPlatform(ss);
        Platform.ShareParams params = new Platform.ShareParams();
//        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap logo;
        try {
            logo = Glide.with(this).load(imagePath).asBitmap().centerCrop().into(300,300).get();
            params.setImageData(logo);
        } catch (Exception e) {
            e.printStackTrace();
//            logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
//        params.setShareType(Platform.SHARE_IMAGE);//分享图片必须
//        FileProvider.getUriForFile(this, "com.hb.fileprovider", new File(iamgePath));
        params.setTitle(shareTitle);
        params.setText(shareContent);
        if (ss.equals(WechatMoments.NAME)){
            params.setFilePath(imagePath);
            params.setTitle(shareTitle + shareContent);
        }
        params.setImagePath(imagePath);
        params.setUrl(sharePath);
        params.setShareType(Platform.SHARE_WEBPAGE);
        //FileProvider.getUriForFile(this, "com.hb.fileprovider", new File(newUri.getPath()));
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toast("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toast(throwable.toString());
                Log.e("fffffffffffffffffffff", "onerror==" + platform.getName() + "  code=" + i + "  errr=" + throwable.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                toast("分享取消");
                Log.e("fffffffffffffffffffff", "取消==");
            }
        });
        wechat.share(params);
    }
}
