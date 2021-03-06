package com.lubin.chj.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.lubin.chj.Listener.OnNetReqFinishListener;
import com.lubin.chj.bean.PcInfo;
import com.lubin.chj.bean.jsonToBean.QueryPCByCKpzhReturn;
import com.lubin.chj.bean.jsonToBean.QueryPcReturn;
import com.lubin.chj.utils.GlobleConfig;
import com.lubin.chj.utils.SoapUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DaiJiCheng
 * @time 2016/9/22  16:36
 * @desc ${TODD}
 */
public class PickModel {

    private QueryPcReturn mQueryPcReturn;
    private QueryPCByCKpzhReturn mQueryPCByCKpzhReturn;

    //凭证拣货查询
    public void queryPCByCkpzh(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                if (hashMap.get("result") != null) {
                    String result = hashMap.get("result").toString();
                    mQueryPCByCKpzhReturn = new QueryPCByCKpzhReturn().objectFromData(result);
                    hashMap.put("returnCode", mQueryPCByCKpzhReturn.getReturnCode());
                    hashMap.put("returnMsg", mQueryPCByCKpzhReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    public Map<String, Object> getHashMapForPZH(String pzbh) {
        Map<String, Object> map = new HashMap<>();
        map.put("ckpzh", pzbh);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPCByCkpzh");
        return map;
    }

    public Map<String, Object> getHashMapForGw(String gwbh) {
        Map<String, Object> map = new HashMap<>();

        map.put("bhlx", "gwbh");
        map.put("bh", gwbh);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPC");
        return map;
    }

    public Map<String, Object> getHashMapForQybh(String qybh) {
        Map<String, Object> map = new HashMap<>();
        map.put("bhlx", "qybh");
        map.put("bh", qybh);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPC");
        return map;
    }

    public List<QueryPCByCKpzhReturn.ListBean> getQueryPcBypzhListBean() {
        List<QueryPCByCKpzhReturn.ListBean> list = mQueryPCByCKpzhReturn.getList();
        List<QueryPCByCKpzhReturn.ListBean> container = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            QueryPCByCKpzhReturn.ListBean listBean = list.get(i);
            if (listBean.getQybh() != null)
                container.add(listBean);
        }
        //按照区域编号对数据集合排序
        Collections.sort(container, new Comparator<QueryPCByCKpzhReturn.ListBean>() {
            @Override
            public int compare(QueryPCByCKpzhReturn.ListBean t1, QueryPCByCKpzhReturn.ListBean t2) {
                int i = Integer.parseInt((String) t1.getQybh());
                int j = Integer.parseInt((String) t2.getQybh());
                if (i > j) {
                    return 1;
                }
                if (i == j) {
                    return 0;
                }
                return -1;
            }
        });
        return container;
    }

    //直接拣货查询
    public void queryPc(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                Log.d("test", hashMap.toString());
                if (hashMap.get("result") != null) {
                    mQueryPcReturn = new QueryPcReturn().objectFromData(hashMap.get("result").toString());
                    hashMap.put("returnCode", mQueryPcReturn.getReturnCode());
                    hashMap.put("returnMsg", mQueryPcReturn.getReturnMsg());
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    public Map<String, Object> getHashMap(String pchs) {
        Map<String, Object> map = new HashMap<>();

        map.put("bhlx", "pch");
        map.put("bh", pchs);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "QueryPC");
        return map;
    }


    public List<QueryPcReturn.ListBean> getQueryPcListBean() {
        List<QueryPcReturn.ListBean> list = mQueryPcReturn.getList();
        List<QueryPcReturn.ListBean> container = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            QueryPcReturn.ListBean listBean = list.get(i);
            if (listBean.getQybh() != null)
                container.add(listBean);
        }
        //按照区域编号对数据集合排序
        Collections.sort(container, new Comparator<QueryPcReturn.ListBean>() {
            @Override
            public int compare(QueryPcReturn.ListBean t1, QueryPcReturn.ListBean t2) {
                int i = Integer.parseInt((String) t1.getQybh());
                int j = Integer.parseInt((String) t2.getQybh());
                if (i > j) {
                    return 1;
                }
                if (i == j) {
                    return 0;
                }
                return -1;
            }
        });
        return container;
    }


    //拣货
    public void fetchPC(List<PcInfo> list, String pzh, String pzlx, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        String jsonPcInfoList = new Gson().toJson(list);
        map.put("jsonPcInfoList", jsonPcInfoList);
        map.put("pzh", pzh);
        map.put("pzlx", pzlx);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "FetchPC");

        SoapUtil.GetWebServiceData(map, listener);
    }

    //未取货原因保存
    public void noFetching(Map<String, Object> map, final OnNetReqFinishListener listener) {
        SoapUtil.GetWebServiceData(map, new OnNetReqFinishListener() {
            @Override
            public void OnNetReqFinish(Map<String, Object> hashMap) {
                if (hashMap.get("result") != null) {
                    try {
                        JSONObject result = new JSONObject(hashMap.get("result").toString());
                        hashMap.put("returnCode", result.getString("returnCode"));
                        hashMap.put("returnMsg", result.getString("returnMsg"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    hashMap.put("returnCode", "9999");
                    hashMap.put("returnMsg", "网络请求失败");
                }
                listener.OnNetReqFinish(hashMap);
            }
        });
    }

    /**
     * @param list PcInfo只需要包含“批次号-pch"和“未取货原因-wqhyy”两个属性
     *@param wqhyy @return
     */
    public Map<String, Object> getHashMapNofetching(List<PcInfo> list, String pzh, String wqhyy) {
        Map<String, Object> map = new HashMap<>();

        String s = new Gson().toJson(list);
        map.put("jsonPcInfoList", s);
        map.put("pzh",pzh);
        map.put("bz",wqhyy);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "FinishFetching");
        return map;
    }


    public void GetMyPzhList(String pzlx, String orderType, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pzlx", pzlx);
        map.put("orderType", orderType);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "GetMyPzhList");

        SoapUtil.GetWebServiceData(map, listener);
    }

    public void GetPzhDetailResult(String pzlx, String pzh, final OnNetReqFinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("pzh", pzh);
        map.put("pzlx", pzlx);
        map.put("rightID", GlobleConfig.rightId);
        map.put("doMethod", "GetPzhDetail");

        SoapUtil.GetWebServiceData(map, listener);
    }



}
