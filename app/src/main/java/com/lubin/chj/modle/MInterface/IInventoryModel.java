package com.lubin.chj.modle.MInterface;

import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;

import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/27  17:47
 * @desc ${TODD}
 */
public interface IInventoryModel {
    //柜位查询
    void savePD(Map<String,Object> map, OnNetReqFinishListener listener);
    Map<String, Object> getHashMapForSave(List<PcInfo> list);

}
