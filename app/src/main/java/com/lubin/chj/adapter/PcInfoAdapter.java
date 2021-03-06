package com.lubin.chj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.bean.PcInfo;

import java.util.List;

/**
 * Created by lubin on 2016/9/20.
 */
public class PcInfoAdapter extends BaseListAdapter<PcInfo> {

    public List<Boolean> flags;

    public PcInfoAdapter(Context context, List<PcInfo> list, List<Boolean> flags) {
        super(context, list);
        this.flags = flags;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_epc, parent, false);
        }

        PcInfo dataBean = getList().get(position);
        CheckBox cb_clear = ViewHolder.get(convertView, R.id.item_lv_epc_box);
        TextView item_lv_epc_line = ViewHolder.get(convertView, R.id.item_lv_epc_line);
        TextView item_lv_epc_name = ViewHolder.get(convertView, R.id.item_lv_epc_name);
        TextView item_lv_epc_pc = ViewHolder.get(convertView, R.id.item_lv_epc_pc);
        TextView item_lv_epc_qy = ViewHolder.get(convertView, R.id.item_lv_epc_qy);

        cb_clear.setChecked(false);
        cb_clear.setChecked(flags.get(position));
        item_lv_epc_line.setText(String.valueOf(position + 1));
        item_lv_epc_name.setText(dataBean.getPch());
        item_lv_epc_pc.setText(dataBean.getGwbh());
        String substring = null;
        if (dataBean.getGwbh() != null && !dataBean.getGwbh().isEmpty()) {
            if (dataBean.getQybh() != null && dataBean.getQybh().length() > 2) {
                substring = dataBean.getQybh().substring(dataBean.getQybh().length() - 2, dataBean.getQybh().length());
            }
        } else substring = "";
        item_lv_epc_qy.setText(substring);

        return convertView;
    }
}
