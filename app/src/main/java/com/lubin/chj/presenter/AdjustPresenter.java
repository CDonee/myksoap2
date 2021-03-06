package com.lubin.chj.presenter;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.modle.AdjustModel;
import com.lubin.chj.view.activity.VInterface.IAdjustView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lubin on 2017/2/7.
 */

public class AdjustPresenter {
    private final AdjustModel model;
    IAdjustView view;

    public AdjustPresenter(IAdjustView view) {
        this.view = view;
        model = new AdjustModel();
    }

    public void QueryPc(String pchs) {
        model.QueryPc(pchs, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Object result = hashMap.get("result");
                if (result == null) {
                    return;
                }
                QueryPcReturn data = QueryPcReturn.objectFromData(result.toString());
                if (data.getReturnCode().equals("0000")) {
                    view.ShowPc(data.getList());
                }
            }
        });
    }

    public void MovePC(final String pchList, String qybh) {
        if (pchList.equals("")) {
            return;
        }
        model.MovePC(pchList, qybh, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Object result = hashMap.get("result");
                if (result == null) return;
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String returnCode = jsonObject.getString("returnCode");
                    String returnMsg = jsonObject.getString("returnMsg");
                    if (returnCode.equals("0000")) {
                        QueryPc(pchList);
                        view.ShowDialog(returnMsg);
                    } else {
                        view.ShowDialog(returnMsg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
