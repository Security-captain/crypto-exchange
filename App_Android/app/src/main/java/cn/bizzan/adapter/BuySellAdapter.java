package cn.bizzan.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.bizzan.R;
import cn.bizzan.app.MyApplication;
import cn.bizzan.entity.Exchange;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class BuySellAdapter extends BaseQuickAdapter<Exchange, BaseViewHolder> {
    public BuySellAdapter(@LayoutRes int layoutResId, @Nullable List<Exchange> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Exchange item) {
        int red = ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed);
        int green = ContextCompat.getColor(MyApplication.getApp(), R.color.typeGreen);
        if (item.getType() == 1) {
            helper.setTextColor(R.id.tvType, green).setText(R.id.tvType, item.getIdText())
                    .setTextColor(R.id.tvPrice, green).setText(R.id.tvPrice, item.getPrice())
                    .setText(R.id.tvAmount, item.getAmount());
        } else helper.setTextColor(R.id.tvType, red).setText(R.id.tvType, item.getIdText())
                .setTextColor(R.id.tvPrice, red).setText(R.id.tvPrice, item.getPrice())
                .setText(R.id.tvAmount, item.getAmount());
    }
}
