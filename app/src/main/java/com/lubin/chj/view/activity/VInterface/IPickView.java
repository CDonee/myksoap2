package com.lubin.chj.view.activity.VInterface;

import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;

import java.util.List;

/**
 * Created by lubin on 2016/9/23.
 */
public interface IPickView<T> extends IShowDialogView {
    void ShowPc(T t);

    void RemovePC(List<PcInfo> list);

    void ActivityFinish();

    void changeBtnView();

    void ShowToast(String text);
}
