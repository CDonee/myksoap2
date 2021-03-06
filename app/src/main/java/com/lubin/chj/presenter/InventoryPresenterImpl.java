package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.modle.InventoryModelImpl;
import com.lubin.chj.modle.MInterface.IInventoryModel;
import com.lubin.chj.modle.MInterface.IPickModel;
import com.lubin.chj.modle.PickModel;
import com.lubin.chj.presenter.IPresenter.IInventoryPresenter;
import com.lubin.chj.view.activity.VInterface.IInventoryView;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/28  11:44
 * @desc ${TODD}
 */
public class InventoryPresenterImpl implements IInventoryPresenter {

    private IInventoryModel model;
    private PickModel mIPickModel;
    private IInventoryView view;

    public InventoryPresenterImpl(IInventoryView view) {
        this.view = view;
        this.model = new InventoryModelImpl();
        this.mIPickModel = new PickModel();
    }


    @Override
    public void QueryPc(String key) {
        mIPickModel.queryPc(mIPickModel.getHashMapForGw(key), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                if (returnCode.equals("0000")) {
                    view.showQueryPcResult(mIPickModel.getQueryPcListBean());
                } else {
                    view.ShowDialog(returnMsg);
                }
            }
        });
    }

    @Override
    public void SavePd(List<PcInfo> list) {
        model.savePD(model.getHashMapForSave(list), new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                String returnCode = hashMap.get("returnCode").toString();
                String returnMsg = hashMap.get("returnMsg").toString();
                view.ShowDialog(returnMsg);
              /*  if (returnCode.equals("0000")) {
                    view.showQueryPcResult(mIPickModel.getQueryPcListBean());
                } else {*/
                //    view.ShowDialog(returnMsg);
                // }
            }
        });
    }
}
