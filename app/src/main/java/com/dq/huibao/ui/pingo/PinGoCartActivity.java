package com.dq.huibao.ui.pingo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyPinGoCountInterface;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoQuYuAdapter;
import com.dq.huibao.adapter.pingo.PinGoShopCartAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Account;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pingo.PinGoCartList;
import com.dq.huibao.bean.pingo.PinGoiQuSelect;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ShowUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：购物车（activity）
 * Created by jingang on 2017/11/1.
 */
public class PinGoCartActivity extends BaseActivity implements
        CheckInterface,
        ModifyPinGoCountInterface {

    /*登录状态*/
    @Bind(R.id.lin_shopcart_nologin)
    LinearLayout linShopcartNologin;
    @Bind(R.id.rel_shopcart_login)
    RelativeLayout relShopCartLogin;
    @Bind(R.id.tv_nologin_title)
    TextView tvNologinTitle;

    @Bind(R.id.but_percen_login)
    Button butPercenLogin;

    @Bind(R.id.tv_base_title)
    TextView tvBaseTitle;

    @Bind(R.id.ck_all)
    CheckBox ck_all;
    @Bind(R.id.tv_settlement)
    TextView tv_settlement;
    @Bind(R.id.tv_show_price)
    TextView tv_show_price;
    @Bind(R.id.rl_bottom)
    RelativeLayout rl_bottom;

    @Bind(R.id.rel_shopcar_header)
    RelativeLayout relShopcarHeader;

    /*移至收藏夹 删除*/
    @Bind(R.id.but_shopcart_tofavorite)
    Button butShopcartTofavorite;
    @Bind(R.id.but_shopacrt_delete)
    Button butShopacrtDelete;

//    @Bind(R.id.ptrv_fm_cart)
//    PullToRefreshView pullToRefreshView;

    private TextView tv_all_check;

    private boolean flag = false;
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";
    private String phone = "", token = "",uid = "";

    /*UI显示*/
    @Bind(R.id.exListView)
    ExpandableListView exListView;

    private List<PinGoCartList.DataBean.ListBean> shopList = new ArrayList<>();
    private PinGoShopCartAdapter shopCartAdapter;

    //区域数据
    PinGoiQuSelect pinGoiQuSelect = null;
    //
    PinGoCartList pinGoCartList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shopcar);
        ButterKnife.bind(this, this);
        setTitleName("拼go购物车");

        shopCartAdapter = new PinGoShopCartAdapter(new ArrayList<>(Arrays.asList(new String[]{"惠宝商城"})), shopList, this);
        shopCartAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
        shopCartAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(shopCartAdapter);

        findViewById(R.id.iv_base_back).setVisibility(View.GONE);
        findViewById(R.id.rel_shopcar_header).setVisibility(View.GONE);
        isLogin();
    }

    /*
      * 判断登录状态
      *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(this.getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                uid = login.getData().getUid();
                getCart();
                diquData();
            }

            relShopCartLogin.setVisibility(View.VISIBLE);
            linShopcartNologin.setVisibility(View.GONE);
        } else {
            relShopCartLogin.setVisibility(View.GONE);
            linShopcartNologin.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 获取购物车
     *
     * @param phone
     * @param token
     */
    private String cart_string = "";

    public void getCart() {
        PATH = HttpPath.PINGO_CARTLIST + "?mid=" + uid;
        System.out.println("获取拼go购物车 = " + PATH);
        HttpxUtils.Get(this, PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                cart_string = result;
                System.out.println("获取购物车 = " + result);
                pinGoCartList = GsonUtil.gsonIntance().gsonToBean(result, PinGoCartList.class);

                if (pinGoCartList.getStatus() == 1) {
                    shopList.clear();
                    shopList.addAll(pinGoCartList.getData().getList());
                    shopCartAdapter.notifyDataSetChanged();
                    // 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                    exListView.expandGroup(0);
                }

                calculate();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("获取拼go购物车 =失败 " + ex.toString());
                if (!TextUtils.isEmpty(cart_string)) {
                    Account account = GsonUtil.gsonIntance().gsonToBean(cart_string, Account.class);
                    if (account.getData().equals("用户信息错误")) {
                        ShowUtils.showDialog(PinGoCartActivity.this, "提示：用户验证错误", "此账号长时间未登录或在别处已登录，是否重新登录？", new ShowUtils.OnDialogListener() {
                            @Override
                            public void confirm() {
                                intent = new Intent(PinGoCartActivity.this, LoginActivity.class);
                                startActivityForResult(intent, CodeUtils.CART_FM);
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                    }
                }
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
     *
     * 更新购物车
     * @param type:dotype 选项：inc/dec  inc增加；dec减少
     * @param cartid:id
     * @param goodsid:goodid
     * @param count
     */
    public void getUPCart(String cartid,String goodsid,String count,String type){
        Map<String,String> map = new HashMap<>();
        map.put("mid",uid);
        map.put("id",cartid);
        map.put("goodsid",goodsid);
        map.put("count","1");
        map.put("dotype",type);
        System.out.println("更新购物车 = " + map.toString());
        HttpxUtils.Get(this,HttpPath.PINGO_CART_UPDATA, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("更新购物车 = " + result);

                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (aReturn.getStatus() == 1){
//                            getCart();
                            toast(aReturn.getData());
                        }else{
                            toast(aReturn.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("更新= 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }
    Set<String> idSets = new HashSet<>();
    String ids = "";
    /**
     *
     * 删除购物车商品
     */
    public void deleteCart(){
        Map<String,String> map = new HashMap<>();
        map.put("mid","21");
        map.put("id",ids);
        HttpxUtils.Get(this,HttpPath.PINGO_CART_DELETE, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("删除购物车商品 = " + result);

                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (aReturn.getStatus() == 1){
                            getCart();
                            toast(aReturn.getData());
                        }else{
                            toast(aReturn.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("删除购物车商品= 失败" + ex.getMessage());
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
     * 地区
     */
    public void diquData(){
        HttpxUtils.Get(this,HttpPath.PINGO_REGION, null,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("地区地区 = " + result);
                        pinGoiQuSelect = GsonUtil.gsonIntance().gsonToBean(result, PinGoiQuSelect.class);
                        if (diQuAdapter != null){
                            diQuAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("地区地区= 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }
    //
    PopupWindow popupWindow = null;
    PinGoQuYuAdapter diQuAdapter;
    View view;
    /**
     * 选择地区
     */
    public void selectDiqu() {
        if (pinGoiQuSelect == null){
            diquData();
        }
//        if (popupWindow == null){
//            createPop();
//        }
        createPop();
        // 依附的父布局自己设定，我这里为了方便，这样写的。
        popupWindow.showAtLocation(rl_bottom, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 创建pop
     */
    public void createPop(){
        view = View.inflate(this, R.layout.pingo_pop_diqu_choose,
                null);
        // 最后一个参数false 代表：不与其余布局发生交互， true代表：可以与其余布局发生交互事件
        popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false) {

            // 重写popupWindow消失时事件
            @Override
            public void dismiss() {
                super.dismiss();
            }
        };
        // 设置Pop入场动画效果
        popupWindow.setAnimationStyle(R.style.pop_style);
        // 设置Pop响应内部区域焦点
        popupWindow.setFocusable(true);
        // 设置Pop响应外部区域焦点
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow弹出软键盘模式（此处为覆盖式）
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // 响应返回键必须的语句
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 依附的父布局自己设定，我这里为了方便，这样写的。
//        popupWindow.showAtLocation(rl_bottom, Gravity.BOTTOM, 0, 0);

        RecyclerView recyclerView = view.findViewById(R.id.list_pingo_diqu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        diQuAdapter = new PinGoQuYuAdapter(this,pinGoiQuSelect.getData().getList());
        recyclerView.setAdapter(diQuAdapter);
        diQuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                popupWindow.dismiss();
                toast(pinGoiQuSelect.getData().getList().get(position).getRegname());
                //判断是不是同一个类型---立减或者折扣
                String typeYH = "";
                for (PinGoCartList.DataBean.ListBean bean: pinGoCartList.getData().getList()) {
                    if (ids.indexOf(bean.getId()) >= 0){
                        //被选中的
                        if (typeYH.equals("")){
                            typeYH = bean.getDistype();
                        }
                        if (!typeYH.equals(bean.getDistype())){
                            toast("请选择同种优惠类型的商品");
                            return;
                        }
                    }
                }
                intent = new Intent(PinGoCartActivity.this, PinGoSubmitOrderActivity.class);
                intent.putExtra("cartids", ids);
                intent.putExtra("regid", pinGoiQuSelect.getData().getList().get(position).getId());
                intent.putExtra("tag", "0");
                startActivityForResult(intent, CodeUtils.CART_FM);
            }
        });
    }

    @OnClick({R.id.but_percen_login, R.id.ck_all, R.id.tv_settlement,
            R.id.but_shopcart_tofavorite, R.id.but_shopacrt_delete})
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.but_percen_login:
                //登录
                intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, CodeUtils.CART_FM);
                break;
            case R.id.ck_all:
                //全选按钮
                doCheckAll();
                break;

            case R.id.but_shopcart_tofavorite:
                //移至收藏夹
                break;

            case R.id.but_shopacrt_delete:
                //删除
                if (totalCount == 0) {
                    toast("请选择要移除的商品");
                    //Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(this).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？\n总计:\n" + totalCount + "种商品\n" + totalPrice + "元" + "\n商品id = " + ids);
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //doDelete();
                                System.out.println("ids = " + ids);
                                deleteCart();
                                //删除操作
                            }
                        });
                alert.show();
                break;


            case R.id.tv_settlement:
                //提交订单
                if (totalCount == 0) {
                    toast("请选择要支付的商品");
                    return;
                }
                selectDiqu();

                break;

            default:
                break;
        }
    }

    /**
     * 组选框状态改变触发的事件
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        for (int i = 0; i < shopList.size(); i++) {
            shopList.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            ck_all.setChecked(true);
        else
            ck_all.setChecked(false);
        shopCartAdapter.notifyDataSetChanged();
        calculate();

    }

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;
        // 判断改组下面的所有子元素是否是同一种状态
        for (int i = 0; i < shopList.size(); i++) {
            // 不全选中
            if (shopList.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }

        if (isAllCheck()) {
            ck_all.setChecked(true);// 全选
        } else {
            ck_all.setChecked(false);// 反选
        }
        shopCartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked,
                           String id, String goodid, String count) {
        getUPCart(id,goodid,count,"inc");
    }

    /**
     * 删减操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked,
                           String id, String goodid, String count) {

        getUPCart(id,goodid,count,"dec");
//        cartAdd(groupPosition, childPosition, showCountView, isChecked,
//                phone, token, gid, optionid, -1, 0);
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (PinGoCartList.DataBean.ListBean group : shopList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 弹窗确定
     *
     * @param unionid
     * @param ids
     */
    public void setDialog(final String unionid, final String ids) {
        android.support.v7.app.AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("操作提示");
        alert.setMessage("您确定要将这些商品从购物车中移除吗？");
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // modifyCountInterface.childDelete(position);//删除 目前只是从item中移除
                        //setRemova(unionid, ids);
                    }
                });
        alert.show();
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < shopList.size(); i++) {
            shopList.get(i).setChoosed(ck_all.isChecked());
        }
        shopCartAdapter.notifyDataSetChanged();
        calculate();
    }
    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        ids = "";
        for (int j = 0; j < shopList.size(); j++) {
            PinGoCartList.DataBean.ListBean product = shopList.get(j);
            if (product.isChoosed()) {
                totalCount++;
                totalPrice += Double.parseDouble(product.getMarketprice()) * Integer.parseInt(product.getCount());
                if (ids.equals("")) {
                    //ids = product.getGoodsid();
                    ids = product.getId();
                } else {
                    //ids = ids + "," + product.getGoodsid();
                    ids = ids + "," + product.getId();
                }
            }
        }
        totalPrice = new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
        tv_show_price.setText("￥" + totalPrice);
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0) {
            //setCartNum();
        } else {
            //title.setText("购物车" + "(" + totalCount + ")");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.CART_FM) {
            if (resultCode == CodeUtils.CONFIRM_ORDER || resultCode == CodeUtils.LOGIN) {
                isLogin();
            }
        }
    }

//    @Override
//    public void onFooterRefresh(PullToRefreshView view) {
//        pullToRefreshView.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //加载更多数据
//                pullToRefreshView.onFooterRefreshComplete();
//
//            }
//
//        }, 1000);
//    }
//
//    @Override
//    public void onHeaderRefresh(PullToRefreshView view) {
//        pullToRefreshView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新数据
//                pullToRefreshView.onHeaderRefreshComplete("更新于:"
//                        + Calendar.getInstance().getTime().toLocaleString());
//                pullToRefreshView.onHeaderRefreshComplete();
//
//                isLogin();
//
//            }
//
//        }, 1000);
//    }
}
