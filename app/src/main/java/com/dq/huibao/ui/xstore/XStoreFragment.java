package com.dq.huibao.ui.xstore;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.xstore.XStoreInfo;
import com.dq.huibao.ui.memcen.MemcenActivity;
import com.dq.huibao.utils.FileUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.PhotoUtils;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by d on 2018/4/2.
 */

public class XStoreFragment extends BaseFragment {
    View view = null;
    @Bind(R.id.xstore_info_name)
    EditText xstoreInfoName;
    @Bind(R.id.xstore_info_head)
    ImageView xstoreInfoHead;
    @Bind(R.id.xstore_info_dianzhao)
    ImageView xstoreInfoDianzhao;
    @Bind(R.id.xstore_info_jieshao)
    EditText xstoreInfoJieshao;
    @Bind(R.id.xstore_info_ok)
    Button xstoreInfoOk;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    //
    XStoreInfo xStoreInfo;
    /*图片地址:头像，店招，当前选择修改的*/
    private String imageHeadURl, imageDianZhaoUrl;
    ImageView nowImageView;
    private boolean isDianZhao = false;
    /**
     * 图片相关操作
     */
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xstore_info, null);
        ButterKnife.bind(this, view);

        getStoreInfo();
        return view;
    }

    public static XStoreFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("phone", phone);
        args.putString("token", token);
        XStoreFragment fragment = new XStoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uid = getArguments().getString("uid");
            phone = getArguments().getString("phone");
            token = getArguments().getString("token");
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.xstore_info_head, R.id.xstore_info_dianzhao, R.id.xstore_info_ok})
    public void imgUpdateClick(View view) {
        switch (view.getId()) {
            case R.id.xstore_info_head://头像
                nowImageView = xstoreInfoHead;
                isDianZhao = false;
                imageUpdate();
                break;
            case R.id.xstore_info_dianzhao://店招
                nowImageView = xstoreInfoDianzhao;
                isDianZhao = true;
                imageUpdate();
                break;
            case R.id.xstore_info_ok:
                if (!TextUtils.isEmpty(imageHeadURl) && !TextUtils.isEmpty(imageDianZhaoUrl)) {
                    submit();
                } else {
                    toast("未加载成功");
                }
                break;
        }
    }

    /**
     * 获取小店信息
     */
    public void getStoreInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("mid", uid);
        map.put("set", "1");
        HttpxUtils.Get(getActivity(),HttpPath.XSHOP_INFO, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        xStoreInfo = GsonUtil.gsonIntance().gsonToBean(result, XStoreInfo.class);
                        updateUI(xStoreInfo);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 更新ui
     */
    public void updateUI(XStoreInfo info) {
        //默认值
        imageHeadURl = info.getData().getThumb();
        imageDianZhaoUrl = info.getData().getFocusthumb();
        Glide.with(getActivity())
                .load(ImageUtils.getImagePath(info.getData().getThumb()))
//                .placeholder(R.mipmap.)
                .crossFade(1000)
                .placeholder(R.mipmap.ic_header)
                .error(R.mipmap.ic_header)
                .into(xstoreInfoHead);
        Glide.with(getActivity())
                .load(ImageUtils.getImagePath(info.getData().getFocusthumb()))
                .crossFade(1000)
                .placeholder(R.mipmap.icon_empty001)
                .error(R.mipmap.icon_error001)
                .into(xstoreInfoDianzhao);
        xstoreInfoName.setText(TextUtils.isEmpty(info.getData().getShopname()) ? "" : info.getData().getShopname());
        xstoreInfoJieshao.setText(info.getData().getIntro());
    }

    /**
     * 提交修改
     */
    public void submit() {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.XSHOP_SAVA_SHOP_INFO;
        Map<String, String> map = new HashMap<>();
        map.put("sign", MD5Util.getMD5String("phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpPath.KEY));
        map.put("mid", uid);
        map.put("shopname", xstoreInfoName.getText().toString());
        map.put("thumb", imageHeadURl);
        map.put("focusthumb", imageDianZhaoUrl);
        map.put("intro", xstoreInfoJieshao.getText().toString());
        map.put("phone", phone);
        map.put("timestamp", String.valueOf((System.currentTimeMillis() / 1000)));
        map.put("token", token);
        HttpxUtils.Post(getActivity(),PATH, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("修改成功");
                } else {
                    toast("修改失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /**
     * 上传头像
     *
     * @param file
     * @param phone
     * @param token
     */
    public void setUpImg(String file, String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpPath.MEM_UPIMG + "sign=" +
                MD5Util.getMD5String("phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpPath.KEY);
        params = new RequestParams(PATH);
        params.addBodyParameter("file", new File(file));  //filePath是手机获取的图片地址
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("timestamp", String.valueOf((System.currentTimeMillis() / 1000)));
        params.addBodyParameter("token", token);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("图片上传成功");
                    if (isDianZhao) {
                        imageDianZhaoUrl = addrReturn.getData().toString();
                    } else {
                        imageHeadURl = addrReturn.getData().toString();
                    }
                } else {
                    toast("图片上传失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private AlertDialog alertDialog;
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
//    private String urlpath;            // 图片本地路径

    /**
     * 图片上传
     */
    public void imageUpdate() {
        //有待更新 可以使用popupwindow来实现 注意点击出现时候的背景变化
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        View localView = getLayoutInflater()
                .inflate(R.layout.personal_header_choice, null);
        localView.setAnimation(AnimationUtils.loadAnimation(
                getActivity(), R.anim.slide_bottom_to_top));
        Window localWindow = alertDialog.getWindow();
        localWindow.getAttributes();
        alertDialog.show();
        localWindow.setContentView(localView);
        localWindow.setGravity(Gravity.BOTTOM);
        localWindow.setLayout(-1, 380);

        btn_take_photo = (Button) localView.findViewById(R.id.btn_take_photo);
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照
                alertDialog.dismiss();//关闭AlertDialog
                autoObtainCameraPermission();

            }
        });

        btn_pick_photo = (Button) localView.findViewById(R.id.btn_pick_photo);
        btn_pick_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册
                alertDialog.dismiss();
                autoObtainStoragePermission();
            }
        });

        btn_cancel = (Button) localView.findViewById(R.id.btn_cancel);
        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                alertDialog.dismiss();
            }
        });
    }

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                toast("您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(getActivity(), "com.hb.fileprovider", fileUri);
                }
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                toast("设备没有SD卡");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(getActivity(), "com.hb.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        toast("设备没有SD卡");
                    }
                } else {
                    toast("请允许打开相机！");
                }
                break;

            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    toast("请允许操作SD卡");
                }
                break;
            default:
        }
    }

    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    if (isDianZhao) {
                        //店招图片,不需要裁切
                        cropImageUri = data.getData();
                        setImageShow();
                    } else {
                        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    }
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(getActivity(), data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(getActivity(), "com.hb.fileprovider", new File(newUri.getPath()));
                        }
                        if (isDianZhao) {
                            //店招图片,不需要裁切
                            cropImageUri = data.getData();
                            setImageShow();
                        } else {
                            PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                        }
                        //startPhotoZoom(cropImageUri);
                    } else {
                        toast("设备没有SD卡");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    setImageShow();
                    break;
                default:
            }
        }
    }

    /**
     * 设置显示图片并上传图片
     */
    public void setImageShow() {
        Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
        if (bitmap != null) {
            showImages(bitmap);
            setUpImg(FileUtil.saveFile(getActivity(), "temphead.jpg", bitmap), phone, token);
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }

    private void showImages(Bitmap bitmap) {
        nowImageView.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
