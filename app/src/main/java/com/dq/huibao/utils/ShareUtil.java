package com.dq.huibao.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 微信分享
 * Created by d on 2018/5/12.
 */

public class ShareUtil {
    public static Dialog dialog = null;
    /**
     * 分享
     */
    public static void shareDialog(final Context context, final String titel, final String content, final String iamgePath, final String sharePath) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share,null);
        builder.setTitle("分享");
        builder.setView(view);
        view.findViewById(R.id.pingo_share_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(Wechat.NAME,context,titel,content,iamgePath,sharePath);
            }
        });
        view.findViewById(R.id.pingo_share_share_quan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(WechatMoments.NAME,context,titel,content,iamgePath,sharePath);
            }
        });
        view.findViewById(R.id.pingo_share_share_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 分享
     */
    private static void showShare(String type, final Context context, String shareTitle, String shareContent, String imagePath, String sharePath) {

        Platform wechat = ShareSDK.getPlatform(type);
        Platform.ShareParams params = new Platform.ShareParams();
//        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap logo;
        try {
            logo = Glide.with(context).load(imagePath).asBitmap().centerCrop().into(300,300).get();
            params.setImageData(logo);
        } catch (Exception e) {
            e.printStackTrace();
//            logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
//        params.setShareType(Platform.SHARE_IMAGE);//分享图片必须
//        FileProvider.getUriForFile(this, "com.hb.fileprovider", new File(iamgePath));
        params.setTitle(shareTitle);
        params.setText(shareContent);
        if (type.equals(WechatMoments.NAME)){
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
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                if (dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
//                toast(throwable.toString());
                if (dialog != null && dialog.isShowing()){
                    Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                Log.e("fffffffffffffffffffff", "onerror==" + platform.getName() + "  code=" + i + "  errr=" + throwable.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {
//                toast("分享取消");
                Log.e("fffffffffffffffffffff", "取消==");
            }
        });
        wechat.share(params);
    }
}
