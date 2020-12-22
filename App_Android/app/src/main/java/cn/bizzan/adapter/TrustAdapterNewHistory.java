package cn.bizzan.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.bizzan.R;
import cn.bizzan.app.MyApplication;
import cn.bizzan.entity.EntrustHistory;
import cn.bizzan.utils.WonderfulDateUtils;
import cn.bizzan.utils.WonderfulToastUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class TrustAdapterNewHistory extends BaseQuickAdapter<EntrustHistory, BaseViewHolder> {
    boolean isCurrent;

    public TrustAdapterNewHistory(@Nullable List<EntrustHistory> data, boolean isCurrent) {
        super(R.layout.adapter_trust_new, data);
        this.isCurrent = isCurrent;
    }

    @Override
    protected void convert(final BaseViewHolder helper, EntrustHistory item) {
//        WonderfulLogUtils.logi("miao",item.getSymbol()+"//////****");
        //买入/卖出
        if ("BUY".equals(item.getDirection())) {
            helper.setText(R.id.trust_direction, WonderfulToastUtils.getString(R.string.text_buy)).setTextColor(R.id.trust_direction,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen));
        } else {
            helper.setText(R.id.trust_direction, WonderfulToastUtils.getString(R.string.text_sale)).setTextColor(R.id.trust_direction,
                    ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        }

        //交易对
        helper.setText(R.id.trust_symbol, item.getSymbol());
        helper.setText(R.id.trust_count_key,"数量"+"(" + item.getCoinSymbol() + ")");
        //交易状态
        if (isCurrent) {
            helper.setText(R.id.trust_state, WonderfulToastUtils.getString(R.string.text_repeal));
            helper.getView(R.id.trust_state).setSelected(true);
//            helper.getView(R.id.trust_state).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cancleLitener.onCancel(helper.getAdapterPosition());
//                }
//            });
            helper.addOnClickListener(R.id.trust_state);
        } else {
            if ("COMPLETED".equals(item.getStatus())) {
                helper.setText(R.id.trust_state, WonderfulToastUtils.getString(R.string.traded_trade));
            } else {
                helper.setText(R.id.trust_state, WonderfulToastUtils.getString(R.string.undone));
            }
        }
        //委托时间
        String[] times = WonderfulDateUtils.getFormatTime(null, new Date(item.getTime())).split(" ");
        helper.setText(R.id.trust_time_value, times[0].substring(5, times[0].length()) + " " + times[1].substring(0, 5));
        //价格类型
        if ("LIMIT_PRICE".equals(item.getType())) { // 限价
            helper.setText(R.id.trust_price_type_value, "限价");
        } else { // 市价
            helper.setText(R.id.trust_price_type_value, "市价");
        }
        //价格
        helper.setText(R.id.trust_price_key, WonderfulToastUtils.getString(R.string.price) + "(" + item.getBaseSymbol() + ")");
        String format3 = new DecimalFormat("#0.00000000").format(item.getPrice());
        BigDecimal bg3 = new BigDecimal(format3);
        String v3 = bg3.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.trust_price_value, v3);
        // 数量
        helper.setText(R.id.trust_count_value, WonderfulToastUtils.getString(R.string.amount) + "(" + item.getCoinSymbol() + ")");
        String format = new DecimalFormat("#0.00000000").format(item.getAmount());
        BigDecimal bg = new BigDecimal(format);
        String v = bg.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.trust_count_value, v);
        // 成交量
        String format1 = new DecimalFormat("#0.00000000").format(item.getTradedAmount());
        BigDecimal bg1 = new BigDecimal(format1);
        String v1 = bg1.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.trust_sold_value, v1);
        // 委托总额
        String format2 = new DecimalFormat("#0.00000000").format(item.getTurnover());
        BigDecimal bg2 = new BigDecimal(format2);
        String v2 = bg2.setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        helper.setText(R.id.trust_total_value, v2);

    }

    OnclickListenerCancel cancleLitener;

    public void setOnCanleListener(OnclickListenerCancel cancleLitener) {
        this.cancleLitener = cancleLitener;
    }


   public interface OnclickListenerCancel {

        void onCancel(int position);
    }


}

