package com.lubin.chj.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.Light;
import com.lubin.chj.bean.jsonToBean.SetLightReturn;
import com.lubin.chj.modle.MInterface.ISetLightModel;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lubin on 2016/9/29.
 */

public class SetLightModelImpl implements ISetLightModel {

    private SetLightReturn mSetLightReturn;

    //控灯
    @Override
    public Map<String, Object> getHashMapForLight(List<Light> list) {
        Map<String, Object> map = new HashMap<>();
        String s = new Gson().toJson(list);
        map.put("jsonGwLightList", s);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "SetLight");
        return map;
    }

    @Override
    public void setLight(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                Object result = hashMap.get("result");
                if (result != null) {
                    mSetLightReturn = SetLightReturn.objectFromData(result.toString());
                    hashMap.put("returnCode", mSetLightReturn.getReturnCode());
                    hashMap.put("returnMsg", mSetLightReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    @Override
    public List<SetLightReturn.ListBean> getLightListBean() {
        return mSetLightReturn.getList();
    }
}
