package com.lubin.chj.presenter;

import android.util.Log;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.Light;
import com.lubin.chj.modle.MInterface.IPickModel;
import com.lubin.chj.modle.MInterface.ISetLightModel;
import com.lubin.chj.modle.PickModel;
import com.lubin.chj.modle.SetLightModelImpl;
import com.lubin.chj.presenter.IPresenter.ISearchPresenter;
import com.lubin.chj.view.activity.VInterface.ISearchView;

import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/11/8.
 */

public class SearchPresenterImpl implements ISearchPresenter {

    ISearchView view;
    ISetLightModel setLightModel;
    PickModel pickModel;

    public SearchPresenterImpl(ISearchView view) {
        this.view = view;
        this.setLightModel = new SetLightModelImpl();
        this.pickModel = new PickModel();
    }

    @Override
    public void QueryPc(String pcs) {
        pickModel.queryPc(pickModel.getHashMap(pcs), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.ShowData(pickModel.getQueryPcListBean());
                } else {
                    view.ShowDialog(returnMsg);
                }
            }
        });
    }

    @Override
    public void SetLight(final List<Light> lights) {
        setLightModel.setLight(setLightModel.getHashMapForLight(lights), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                StringBuilder sb = new StringBuilder();
                for (Light light : lights) {
                    sb.append(light.gwbh);
                    sb.append("、");
                }
                sb.deleteCharAt(sb.length()-1);
                if (returnCode.equals("0000")) {

                    if (lights.get(0).isOpen) {
                        view.ShowToast("柜位" + ":" + sb.toString() + " 亮灯成功！");
                    } else {
                        view.ShowToast("柜位" + ":" + sb.toString() + " 灭灯成功！");
                    }
                    view.changeBtnView();
                } else {
                    view.ShowToast("柜位" + ":" + sb.toString() + returnMsg);
                }
            }
        });
    }
}
