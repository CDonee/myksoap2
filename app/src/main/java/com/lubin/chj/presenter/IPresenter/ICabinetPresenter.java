package com.lubin.chj.presenter.IPresenter;

import com.lubin.chj.bean.Light;

import java.util.List;

/**
 * Created by lubin on 2016/9/22.
 */
public interface ICabinetPresenter {
    void QueryCabinet(String key);
    void SetLight(List<Light> lights);

    void setQueryGWList(int cfsl,String gwdx);

}
