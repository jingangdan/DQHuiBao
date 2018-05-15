package com.dq.huibao.ui.memcen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.common.Region;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.FileUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.PhotoUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.GlideCircleTransform;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：个人资料
 * Created by jingang on 2017/11/24.
 */

public class MemcenActivity extends BaseActivity {
    @Bind(R.id.et_member_realname)
    EditText etMemberRealname;
    @Bind(R.id.iv_member_header)
    ImageView ivMemberHeader;
    @Bind(R.id.rel_percen_header)
    RelativeLayout relPercenHeader;
    @Bind(R.id.tv_member_phone)
    TextView tvMemberPhone;
    @Bind(R.id.et_member_weixin)
    EditText etMemberWeixin;
    @Bind(R.id.rb_man)
    RadioButton rbMan;
    @Bind(R.id.rb_wuman)
    RadioButton rbWuman;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;
    @Bind(R.id.et_member_province)
    EditText etMemberProvince;
    @Bind(R.id.tv_member_birth)
    TextView tvMemberBirth;
    @Bind(R.id.but_member_ok)
    Button butMemberOk;
    /*所在城市*/
    private List<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    /*生日*/
    int mYear, mMonth, mDay;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*接收页面传值*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    /*UI显示 ：姓名 头像 手机号 微信号 性别 省 市*/
    private String realname = "", headimgurl = "", mem_phone = "", weixin = "", sex = "", province = "", city = "";
    private String birthyear = "", birthmonth = "", birthday = "";

    private List<Region.DataBean> regionList = new ArrayList<>();
    private String regionid = "";

    private String headerimgurl = "";


    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memcen);
        ButterKnife.bind(this);

        /*选择头像*/
        relPercenHeader.setOnClickListener(new OnClickListener());

        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbMan.getId()) {
                    //toast("男");
                    sex = "1";
                } else if (checkedId == rbWuman.getId()) {
                    //toast("女");
                    sex = "0";
                }
            }
        });

        getRegion();

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        initDate();
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("个人资料");
    }

    @OnClick({R.id.tv_member_phone, R.id.et_member_province, R.id.tv_member_birth, R.id.but_member_ok})
    public void onClick(View view) {
        realname = etMemberRealname.getText().toString();

        switch (view.getId()) {
            case R.id.tv_member_phone:
                //绑定手机
                intent = new Intent(MemcenActivity.this, MobileActivity.class);
                startActivity(intent);

                break;
            case R.id.et_member_province:
                //选择所在城市
                showPickerView();
                break;
            case R.id.tv_member_birth:
                //选择日期
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("WrongConstant") final DatePickerDialog datePicker = new DatePickerDialog(this, null,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(true);
                datePicker.setCanceledOnTouchOutside(true);
                datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //确定的逻辑代码在监听中实现
                                DatePicker picker = datePicker.getDatePicker();
                                int year = picker.getYear();
                                int monthOfYear = picker.getMonth();
                                int dayOfMonth = picker.getDayOfMonth();

                                birthyear = String.valueOf(year);
                                birthmonth = String.valueOf(monthOfYear);
                                birthday = String.valueOf(dayOfMonth);
                                tvMemberBirth.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        });
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消什么也不用做
                                datePicker.cancel();
                            }
                        });
                datePicker.show();

                break;
            case R.id.but_member_ok:
                //修改个人信息
                try {
                    String s = "";
                    try {
                        s = URLEncoder.encode(realname, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
                    ss = ss.replaceAll("[\\s*\t\n\r]", "");

                    dialog(URLEncoder.encode(ss, "UTF-8"), sex, regionid);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
        }
    }


    /**/
    public void initDate() {
        spUserInfo = new SPUserInfo(getApplication());
        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();

            getMember(phone, token);

        } else {
            toast("登录状态出错，请重新登录");
        }

    }

    /**
     * 获取个人信息
     *
     * @param phone
     * @param token
     */
    public void getMember(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.MEM_MEMBER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");
        System.out.println("个人信息 = " + PATH);
        HttpxUtils.Get(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("个人信息 = " + result);
                Login login = GsonUtil.gsonIntance().gsonToBean(result, Login.class);
                realname = login.getData().getNickname();
                headimgurl = login.getData().getHeadimgurl();
                mem_phone = login.getData().getPhone();
                sex = login.getData().getSex();
                province = login.getData().getProvince();
                city = login.getData().getCity();

                setDate();
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

        System.out.println("上传图片 = " + PATH);

        params = new RequestParams(PATH);
        params.addBodyParameter("file", new File(file));  //filePath是手机获取的图片地址
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("timestamp", String.valueOf((System.currentTimeMillis() / 1000)));
        params.addBodyParameter("token", token);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("上传图片 = " + result);
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("图片上传成功");
                    headimgurl = addrReturn.getData().toString();
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

    /**
     * 修改个人信息
     *
     * @param phone
     * @param token
     * @param sex
     * @param region
     */
    public void setMember(String headimgurl, String phone, String token, String nickname, String sex, final String region) {
        MD5_PATH = "headimg=" + headimgurl + "&nickname=" + nickname + "&phone=" + phone + "&region=" + region + "&sex=" + sex + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpPath.MEM_EDITINFO + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        System.out.println("修改个人信息 = " + PATH);
        HttpxUtils.Post(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("修改个人信息 = " + result);
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    toast("" + addrReturn.getData());
                    intent = new Intent();
                    setResult(CodeUtils.MEMBER_EDIT, intent);
                    MemcenActivity.this.finish();
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


    /*组件赋值*/
    public void setDate() {
        etMemberRealname.setText("" + realname);
        Glide.with(this)
                .load(ImageUtils.getImagePath(headimgurl))
                .bitmapTransform(new GlideCircleTransform(this))
                .crossFade(1000)
                .error(R.mipmap.ic_header)
                .into(ivMemberHeader);

        if (sex.equals("1")) {
            rbMan.setChecked(true);
        } else if (sex.equals("0")) {
            rbWuman.setChecked(true);
        }
        tvMemberPhone.setText("" + mem_phone);
        etMemberProvince.setText(province + city);

    }


    private AlertDialog alertDialog;
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private String urlpath;            // 图片本地路径

    /**
     *
     */
    public class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //有待更新 可以使用popupwindow来实现 注意点击出现时候的背景变化
            alertDialog = new AlertDialog.Builder(MemcenActivity.this).create();
            View localView = getLayoutInflater()
                    .inflate(R.layout.personal_header_choice, null);
            localView.setAnimation(AnimationUtils.loadAnimation(
                    MemcenActivity.this, R.anim.slide_bottom_to_top));
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
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                toast("您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(MemcenActivity.this, "com.hb.fileprovider", fileUri);
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
                            imageUri = FileProvider.getUriForFile(MemcenActivity.this, "com.hb.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(this, "com.hb.fileprovider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                        //startPhotoZoom(cropImageUri);
                    } else {
                        toast("设备没有SD卡");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        showImages(bitmap);
                        urlpath = FileUtil.saveFile(MemcenActivity.this, "temphead.jpg", bitmap);
                        setUpImg(urlpath, phone, token);
                    }

                    break;
                default:
            }
        }
    }


    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }

    private void showImages(Bitmap bitmap) {
        ivMemberHeader.setImageBitmap(bitmap);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取省市列表
     */
    public void getRegion() {
        PATH = HttpPath.COMMON_REGION;
        params = new RequestParams(PATH);
        System.out.println("省市列表 = " + PATH);
        HttpxUtils.Get(this,PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("省市列表 = " + result);

                Region region = GsonUtil.gsonIntance().gsonToBean(result, Region.class);
                regionList = region.getData();

                for (int i = 0; i < regionList.size(); i++) {//遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                    options1Items.add(regionList.get(i).getRegion_name());

                    for (int c = 0; c < regionList.get(i).getCity().size(); c++) {
                        //遍历该省份的所有城市
                        String CityName = regionList.get(i).getCity().get(c).getRegion_name();
                        CityList.add(CityName);//添加城市

                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (regionList.get(i).getCity().get(c).getRegion_name() == null
                                || regionList.get(i).getCity().get(c).getDistrict().size() == 0) {
                            City_AreaList.add("");
                        } else {
                            for (int d = 0; d < regionList.get(i).getCity().get(c).getDistrict().size(); d++) {//该城市对应地区所有数据
                                String AreaName = regionList.get(i).getCity().get(c).getDistrict().get(d).getRegion_name();

                                City_AreaList.add(AreaName);//添加该城市所有地区数据
                            }
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }

                    /**
                     * 添加城市数据
                     */
                    options2Items.add(CityList);

                    /**
                     * 添加地区数据
                     */
                    options3Items.add(Province_AreaList);

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

    /*省市二级联动*/

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1) +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                province = options1Items.get(options1);
                city = options2Items.get(options1).get(options2);

                getRegionid(options1, options2, options3);

                etMemberProvince.setText(text);
            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(13)
                .setOutSideCancelable(false)
                .build();
//        pvOptions.setPicker(options1Items);//一级选择器
        // pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /*弹出框*/
    protected void dialog(final String realname, final String sex, final String regionid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认修改吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setMember(headimgurl, phone, token, realname, sex, regionid);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    /**
     * @param position1
     * @param position2
     */
    public void getRegionid(int position1, int position2, int position3) {
        for (int i = 0; i < regionList.size(); i++) {
            for (int j = 0; j < regionList.get(i).getCity().size(); j++) {
                for (int k = 0; k < regionList.get(i).getCity().get(j).getDistrict().size(); k++) {
                    regionid = regionList.get(position1).getCity().get(position2).getDistrict().get(position3).getId();
                }

            }
        }
    }

}
