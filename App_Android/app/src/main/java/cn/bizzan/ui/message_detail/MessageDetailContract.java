package cn.bizzan.ui.message_detail;


import cn.bizzan.base.Contract;
import cn.bizzan.entity.Message;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface MessageDetailContract {
    interface View extends Contract.BaseView<Presenter> {

        void messageDetailSuccess(Message obj);

        void messageDetailFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void messageDetail(String id);
    }
}
