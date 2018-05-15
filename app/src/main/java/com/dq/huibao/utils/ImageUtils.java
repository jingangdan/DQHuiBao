package com.dq.huibao.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 * Description：
 * Created by jingang on 2017/11/5.
 */
public class ImageUtils {
    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(final Context context, final String imageUrl, int emptyImageId, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(getImagePath(imageUrl))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);

                        return false;
                    }
                })
                .placeholder(emptyImageId)
                .error(errorImageId)
                .into(imageView);

    }

    public static void loadIntoUseFitWidths(final Context context, final String imageUrl, int emptyImageId, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(getImagePath(imageUrl))
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(emptyImageId)
                .error(errorImageId)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        //获取imageView的宽
                        int imageViewWidth = imageView.getWidth();
                        //计算缩放比例
                        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
                        //计算图片等比例放大后的高
                        int imageViewHeight = (int) (height * sy);

                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = imageViewHeight;
                        imageView.setLayoutParams(params);
                    }
                });

    }
    public static void loadIntoUseFitWidths(final Context context, final String imageUrl, int emptyImageId, final ImageView imageView) {
        Glide.with(context)
                .load(getImagePath(imageUrl))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(emptyImageId)
                .skipMemoryCache(true)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        //获取imageView的宽
                        int imageViewWidth = imageView.getWidth();
                        //计算缩放比例
                        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
                        //计算图片等比例放大后的高
                        int imageViewHeight = (int) (height * sy);

                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = imageViewHeight;
                        imageView.setLayoutParams(params);
                    }
                });

    }

    public static void loadIntoUseFitWidth2(final Context context, final String imageUrl, int emptyImageId, final ImageView imageView) {
        Glide.with(context)
                .load(getImagePath(imageUrl))
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(emptyImageId)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        //获取imageView的宽
                        int imageViewWidth = imageView.getWidth();
                        //计算缩放比例
                        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
                        //计算图片等比例放大后的高
                        int imageViewHeight = (int) (height * sy);

                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = imageViewHeight;
                        imageView.setLayoutParams(params);
                    }
                });

    }

    /**
     * 判断是否是完整地址（是否含有htpp://
     * @param path
     * @return
     */
    public static String getImagePath(String path){
        if (path == null)
            return "";
        if (path.indexOf("http://") >= 0){

        }else if(path.indexOf("https://") >= 0){

        }else {
            path = HttpPath.NEW_HEADER + path;
        }
//        Log.e("图片地址","图片地址="+path);
        return path;
    }

    /**
     * 自动计算图片高度
     * @param context
     * @param path：图片地址
     * @param view：要设置高度的view
     * @param params：ViewGroup.LayoutParams params
     */
    public static void setImageParamsHeight(Context context, String path, final View view, final ViewGroup.LayoutParams params){
        Glide.with(context)
                .load(getImagePath(path))
                .asBitmap()//强制Glide返回一个Bitmap对象
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
//                        Log.e("ffffffffffff", "width " + width); //200px
//                        Log.e("fffffffffff", "height " + height); //200px
                        params.height = AppUtil.getWidth() * height / width;
                        view.setLayoutParams(params);
                    }
                });
    }
}
