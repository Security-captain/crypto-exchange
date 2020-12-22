package cn.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.bizzan.R;
import cn.bizzan.app.MyApplication;
import cn.bizzan.entity.EntrustHistory;
import cn.bizzan.utils.WonderfulDateUtils;
import cn.bizzan.utils.WonderfulMathUtils;
import cn.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 10:18
 * desc  : 当前委托的适配器
 */

public class TrustAdapter extends BaseQuickAdapter<EntrustHistory, BaseViewHolder> {

    public TrustAdapter(@Nullable List<EntrustHistory> data) {
        super(R.layout.adapter_trust_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustHistory item) {
        if ("BUY".equals(item.getDirection())) {
            helper.setText(R.id.trust_type, WonderfulToastUtils.getString(R.string.text_buy)).setTextColor(R.id.trust_type,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
        } else {
            helper.setText(R.id.trust_type, WonderfulToastUtils.getString(R.string.text_sale)).setTextColor(R.id.trust_type,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        }
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getTime())).split(" ");
        helper.setText(R.id.trust_time, times[0].substring(5, times[0].length()) + " " + times[1].substring(0, 5));
        if ("LIMIT_PRICE".equals(item.getType())) { // 限价
            helper.setText(R.id.trust_price, String.valueOf(item.getPrice()));
            // 数量
            String format = new DecimalFormat("#0.00000000").format(item.getAmount());
            BigDecimal bg = new BigDecimal(format);
            String v =  bg.setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            helper.setText(R.id.trust_num,v);
        } else { // 市价
            helper.setText(R.id.trust_price, String.valueOf(WonderfulToastUtils.getString(R.string.text_best_prices)));
            // 数量 如果是市价并买入情况就是--
            if ("BUY".equals(item.getDirection())) {
                helper.setText(R.id.trust_num, String.valueOf("--"));
            }else {

                helper.setText(R.id.trust_num, WonderfulMathUtils.getRundNumber(Double.valueOf(String.valueOf(BigDecimal.valueOf(item.getAmount()))),2,null));
            }
        }
        String symbol = item.getSymbol();
        int i = symbol.indexOf("/");
        helper.setText(R.id.trust_one, WonderfulToastUtils.getString(R.string.text_number) + "(" + symbol.substring(0, i) + ")");
        helper.setText(R.id.trust_two, WonderfulToastUtils.getString(R.string.text_actual_deal) + "(" + symbol.substring(0, i) + ")");
        helper.setText(R.id.trust_ones, WonderfulToastUtils.getString(R.string.text_price) + "(" + symbol.substring(i + 1, symbol.length()) + ")");
        // 已成交
        helper.setText(R.id.trust_done, String.valueOf(item.getTradedAmount()));
        helper.addOnClickListener(R.id.trust_back);
    }
}
