package com.lubin.chj.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lubin.chj.R;
import com.lubin.chj.adapter.QybhAdapter;
import com.lubin.chj.bean.jsonToBean.CabinetReturn;
import com.lubin.chj.presenter.CabinetPresenterImpl;
import com.lubin.chj.presenter.QuYuPresenterImpl;
import com.lubin.chj.view.activity.VInterface.ICabinetView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/9/29  17:41
 * @desc ${TODD}
 */
public class QuYuActivity extends BaseActivity implements ICabinetView<CabinetReturn.ListBean> {
    @BindView(R.id.tb_common)
    Toolbar mTbCommon;
    @BindView(R.id.common_title)
    AppBarLayout mCommonTitle;
    @BindView(R.id.lv_query_qybh)
    ListView mLvQueryQybh;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    private QybhAdapter mQybhAdapter;
    private QuYuPresenterImpl mQuYuPresenter;
    private CabinetPresenterImpl mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quyu);
        ButterKnife.bind(this);
        initHeader();
        init();
    }

    private void init() {
//        mQuYuPresenter = new QuYuPresenterImpl(this);
        String gwbh = this.getIntent().getStringExtra("gwbh");
        mPresenter = new CabinetPresenterImpl(this);
//        mQuYuPresenter.QueryPCbyQybh(gwbh);
        mPresenter.QueryCabinet(gwbh);
    }

    private void initHeader() {
        mTbCommon.setNavigationIcon(R.mipmap.back);
        mTbCommon.setTitle("区域信息");
        mTbCommon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    //展示区域信息 将柜位信息换成区域信息修改头部信息
//    @Override
//    public void ShowQyMsg(final List<QueryPcReturn.ListBean> list) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                tvSum.setText(String.valueOf(list.size()));
//                mQybhAdapter = new QybhAdapter(QuYuActivity.this, list);
//                mLvQueryQybh.setAdapter(mQybhAdapter);
//
//            }
//        });
//
//
//    }

    @Override
    public void ShowCabinets(final List<CabinetReturn.ListBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvSum.setText(String.valueOf(list.size()));
                mQybhAdapter = new QybhAdapter(QuYuActivity.this, list);
                mLvQueryQybh.setAdapter(mQybhAdapter);
            }
        });
    }

    @Override
    public void changeBtnStatus() {

    }

    @Override
    public void ShowDialog(String result) {
        super.ShowDialog(result);
    }
}
