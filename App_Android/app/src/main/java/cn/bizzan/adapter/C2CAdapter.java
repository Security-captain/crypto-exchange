package cn.bizzan.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.bizzan.R;
import cn.bizzan.entity.C2C;
import cn.bizzan.utils.WonderfulMathUtils;
import cn.bizzan.utils.WonderfulStringUtils;
import cn.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */

public class C2CAdapter extends BaseQuickAdapter<C2C.C2CBean, BaseViewHolder> {
    private Context context;

    public C2CAdapter(Context context, int layoutResId, @Nullable List<C2C.C2CBean> data) {
        super(layoutResId, data);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, C2C.C2CBean item) {
        String name=item.getMemberName();
        name=name.substring(0,name.length()-2);
        helper.setText(R.id.tvName, name+"**").setText(R.id.tvLimit,WonderfulToastUtils.getString(R.string.text_quota)+" "+WonderfulStringUtils.getThousand(String.valueOf(item.getMinLimit())) + "~" + WonderfulStringUtils.getThousand(String.valueOf(item.getMaxLimit())) + " CNY")
                .setText(R.id.tvPrice, WonderfulMathUtils.getRundNumber(item.getPrice(),2,null) + "CNY").setText(R.id.tvBuy, ("0".equals(item.getAdvertiseType()) ?
                WonderfulToastUtils.getString(R.string.text_chu_sho) : WonderfulToastUtils.getString(R.string.text_gou_mai)))
                .setText(R.id.tvTradeAmount, ""+item.getTransactions());
        helper.setBackgroundRes(R.id.tvBuy,("0".equals(item.getAdvertiseType()) ?
                R.drawable.shape_c2c_tv_sell : R.drawable.shape_c2c_tv_buy));
        helper.setText(R.id.tvNumber,"数量 "+new BigDecimal(item.getRemainAmount()).setScale(8,BigDecimal.ROUND_DOWN).toPlainString());
        if (!WonderfulStringUtils.isEmpty(item.getAvatar()))
            Glide.with(context).load(item.getAvatar()).into((ImageView) helper.getView(R.id.ivHeader));
        else Glide.with(context).load(R.mipmap.icon_default_header).into((ImageView) helper.getView(R.id.ivHeader));
        List<String> pays = Arrays.asList(item.getPayMode().split(","));
        if (pays.contains("支付宝")) helper.setVisible(R.id.ivZhifubao, true);
        else helper.setGone(R.id.ivZhifubao, false);
        if (pays.contains("微信")) helper.setVisible(R.id.ivWeChat, true);
        else helper.setGone(R.id.ivWeChat, false);
        if (pays.contains("银联") || pays.contains("银行卡")) helper.setVisible(R.id.ivUnionPay, true);
        else helper.setGone(R.id.ivUnionPay, false);
    }
}
