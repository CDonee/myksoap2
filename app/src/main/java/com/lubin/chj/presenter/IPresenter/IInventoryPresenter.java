package com.lubin.chj.presenter.IPresenter;

import com.lubin.chj.bean.PcInfo;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/28  11:41
 * @desc ${TODD}
 */
public interface IInventoryPresenter {
    void QueryPc(String key);
    void SavePd(List<PcInfo> list);
}
