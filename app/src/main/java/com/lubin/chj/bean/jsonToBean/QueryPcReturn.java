package com.lubin.chj.bean.jsonToBean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author DaiJiCheng
 * @time 2016/9/23  13:00
 * @desc ${TODD}
 */
public class QueryPcReturn {

    /**
     * returnCode : 0000
     * returnMsg : 查询成功！
     * rightID :
     * list : [{"isPick":true,"gtbh":null,"gwbh":null,"pch":"4903592331","wlh":null,"rkpzh":null,"qybh":null,"cpzt":null,"cpx":null,"cpxms":null,"ks":null,"ksms":null,"pl":null,"plms":null,"hlhx":null,"hlhxms":null,"zchicun":null,"ztjtf":0,"cjry":null,"cjsj":"/Date(-62135596800000)/","xgry":null,"xgsj":"/Date(-62135596800000)/","wqhyy":null,"fkxx":"0003|系统不存在此批次号！","xnpzh":null,"pdjg":null,"dqqybh":null}]
     */

    private String returnCode;
    private String returnMsg;
    private String rightID;
    /**
     * isPick : true
     * gtbh : null
     * gwbh : null
     * pch : 4903592331
     * wlh : null
     * rkpzh : null
     * qybh : null
     * cpzt : null
     * cpx : null
     * cpxms : null
     * ks : null
     * ksms : null
     * pl : null
     * plms : null
     * hlhx : null
     * hlhxms : null
     * zchicun : null
     * ztjtf : 0
     * cjry : null
     * cjsj : /Date(-62135596800000)/
     * xgry : null
     * xgsj : /Date(-62135596800000)/
     * wqhyy : null
     * fkxx : 0003|系统不存在此批次号！
     * xnpzh : null
     * pdjg : null
     * dqqybh : null
     */

    private List<ListBean> list;

    public static QueryPcReturn objectFromData(String str) {

        return new Gson().fromJson(str, QueryPcReturn.class);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getRightID() {
        return rightID;
    }

    public void setRightID(String rightID) {
        this.rightID = rightID;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private Object gtbh;
        private Object gwbh;
        private String pch;
        private Object wlh;
        private Object rkpzh;
        private Object qybh;
        private Object cpzt;
        private Object cpx;
        private Object cpxms;
        private Object ks;
        private Object ksms;
        private Object pl;
        private Object plms;
        private Object hlhx;
        private Object hlhxms;
        private Object zchicun;
        private int ztjtf;
        private Object cjry;
        private String cjsj;
        private Object xgry;
        private String xgsj;
        private Object wqhyy;
        private String fkxx;
        private Object xnpzh;
        private Object pdjg;
        private Object dqqybh;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public ListBean() {
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    ", gtbh=" + gtbh +
                    ", gwbh=" + gwbh +
                    ", pch='" + pch + '\'' +
                    ", wlh=" + wlh +
                    ", rkpzh=" + rkpzh +
                    ", qybh=" + qybh +
                    ", cpzt=" + cpzt +
                    ", cpx=" + cpx +
                    ", cpxms=" + cpxms +
                    ", ks=" + ks +
                    ", ksms=" + ksms +
                    ", pl=" + pl +
                    ", plms=" + plms +
                    ", hlhx=" + hlhx +
                    ", hlhxms=" + hlhxms +
                    ", zchicun=" + zchicun +
                    ", ztjtf=" + ztjtf +
                    ", cjry=" + cjry +
                    ", cjsj='" + cjsj + '\'' +
                    ", xgry=" + xgry +
                    ", xgsj='" + xgsj + '\'' +
                    ", wqhyy=" + wqhyy +
                    ", fkxx='" + fkxx + '\'' +
                    ", xnpzh=" + xnpzh +
                    ", pdjg=" + pdjg +
                    ", dqqybh=" + dqqybh +
                    '}';
        }

        public Object getGtbh() {
            return gtbh;
        }

        public void setGtbh(Object gtbh) {
            this.gtbh = gtbh;
        }

        public Object getGwbh() {
            return gwbh;
        }

        public void setGwbh(Object gwbh) {
            this.gwbh = gwbh;
        }

        public String getPch() {
            return pch;
        }

        public void setPch(String pch) {
            this.pch = pch;
        }

        public Object getWlh() {
            return wlh;
        }

        public void setWlh(Object wlh) {
            this.wlh = wlh;
        }

        public Object getRkpzh() {
            return rkpzh;
        }

        public void setRkpzh(Object rkpzh) {
            this.rkpzh = rkpzh;
        }

        public Object getQybh() {
            return qybh;
        }

        public void setQybh(Object qybh) {
            this.qybh = qybh;
        }

        public Object getCpzt() {
            return cpzt;
        }

        public void setCpzt(Object cpzt) {
            this.cpzt = cpzt;
        }

        public Object getCpx() {
            return cpx;
        }

        public void setCpx(Object cpx) {
            this.cpx = cpx;
        }

        public Object getCpxms() {
            return cpxms;
        }

        public void setCpxms(Object cpxms) {
            this.cpxms = cpxms;
        }

        public Object getKs() {
            return ks;
        }

        public void setKs(Object ks) {
            this.ks = ks;
        }

        public Object getKsms() {
            return ksms;
        }

        public void setKsms(Object ksms) {
            this.ksms = ksms;
        }

        public Object getPl() {
            return pl;
        }

        public void setPl(Object pl) {
            this.pl = pl;
        }

        public Object getPlms() {
            return plms;
        }

        public void setPlms(Object plms) {
            this.plms = plms;
        }

        public Object getHlhx() {
            return hlhx;
        }

        public void setHlhx(Object hlhx) {
            this.hlhx = hlhx;
        }

        public Object getHlhxms() {
            return hlhxms;
        }

        public void setHlhxms(Object hlhxms) {
            this.hlhxms = hlhxms;
        }

        public Object getZchicun() {
            return zchicun;
        }

        public void setZchicun(Object zchicun) {
            this.zchicun = zchicun;
        }

        public int getZtjtf() {
            return ztjtf;
        }

        public void setZtjtf(int ztjtf) {
            this.ztjtf = ztjtf;
        }

        public Object getCjry() {
            return cjry;
        }

        public void setCjry(Object cjry) {
            this.cjry = cjry;
        }

        public String getCjsj() {
            return cjsj;
        }

        public void setCjsj(String cjsj) {
            this.cjsj = cjsj;
        }

        public Object getXgry() {
            return xgry;
        }

        public void setXgry(Object xgry) {
            this.xgry = xgry;
        }

        public String getXgsj() {
            return xgsj;
        }

        public void setXgsj(String xgsj) {
            this.xgsj = xgsj;
        }

        public Object getWqhyy() {
            return wqhyy;
        }

        public void setWqhyy(Object wqhyy) {
            this.wqhyy = wqhyy;
        }

        public String getFkxx() {
            return fkxx;
        }

        public void setFkxx(String fkxx) {
            this.fkxx = fkxx;
        }

        public Object getXnpzh() {
            return xnpzh;
        }

        public void setXnpzh(Object xnpzh) {
            this.xnpzh = xnpzh;
        }

        public Object getPdjg() {
            return pdjg;
        }

        public void setPdjg(Object pdjg) {
            this.pdjg = pdjg;
        }

        public Object getDqqybh() {
            return dqqybh;
        }

        public void setDqqybh(Object dqqybh) {
            this.dqqybh = dqqybh;
        }
    }

    @Override
    public String toString() {
        return "QueryPcReturn{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", rightID='" + rightID + '\'' +
                ", list=" + list +
                '}';
    }
}
