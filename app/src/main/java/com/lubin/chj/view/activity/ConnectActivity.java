package com.lubin.chj.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lubin.chj.R;
import com.lubin.chj.service.ScanServiceWithUHF;
import com.wifi.ibase.ICallbackWifiInfo;
import com.wifi.ibase.WifiModInfo;
import com.wifi.udpserver.TUdpManage;
import com.wifi.utils.FindDeviceIP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectActivity extends BaseActivity {

    @BindView(R.id.radio_connect_a8b)
    RadioButton radioConnectA8b;
    @BindView(R.id.radio_connect_h7)
    RadioButton radioConnectH7;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_port)
    EditText etPort;

    private ScanServiceWithUHF mService = ScanServiceWithUHF.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        ButterKnife.bind(this);
        radioConnectA8b.setChecked(true);
        radioConnectA8b.setOnCheckedChangeListener(setConnectType);
        radioConnectH7.setOnCheckedChangeListener(setConnectType);
        initTcp();
    }

    // 显示&隐藏tcp参数
    private void ShowTcpVisible(boolean bVisible) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_tcp_para);
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.ll_tcp_para2);
        ll.setVisibility(bVisible ? View.VISIBLE : View.INVISIBLE);
        ll2.setVisibility(bVisible ? View.VISIBLE : View.INVISIBLE);
    }

    // 获取服务器IP
    private String GetServerIP() {
        String host = etAddress.getText().toString();
        if (host == null || host.length() <= 0) {
            Toast.makeText(getApplicationContext(), "无效IP地址",
                    Toast.LENGTH_SHORT).show();
            return "";
        }
        return host;
    }

    // 获取服务器端口号
    private int GetServerPort() {
        String strPort = etPort.getText().toString();
        if (strPort == null || strPort.length() <= 0) {
            Toast.makeText(getApplicationContext(), "无效端口号", Toast.LENGTH_SHORT)
                    .show();
            return 0;
        }
        int port = Integer.parseInt(strPort);
        return port;
    }

    // 查询型号
    private int GetModType() {
        int iModType = 1;
        if (radioConnectA8b.isChecked())
            iModType = 1;
        else if (radioConnectH7.isChecked())
            iModType = 2;
        return iModType;
    }

    // 连接读写器
    private void ConnectReader() {
        int iModType = GetModType();
        Log.d("test",String.valueOf(iModType));
        if (!mService.init(iModType)) {
            Toast.makeText(getApplicationContext(), "初始化失败", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (2 != iModType) {
            if (!mService.Open()) {
                Toast.makeText(getApplicationContext(), "连接失败",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            String IP = GetServerIP();
            if ("" == IP) {
                Toast.makeText(getApplicationContext(), "IP地址无效",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            int port = GetServerPort();
            if (0 == port) {
                Toast.makeText(getApplicationContext(), "端口无效",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (!mService.OpenByTcp(IP, port)) {
                Toast.makeText(getApplicationContext(), "连接失败",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Intent localIntent = new Intent(this, MainActivity.class);
        startActivity(localIntent);
        finish();
    }

    private OnCheckedChangeListener setConnectType = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            if (!isChecked)
                return;
            switch (buttonView.getId()) {
                case R.id.radio_connect_a8b:
                    ShowTcpVisible(false);
                    break;
                case R.id.radio_connect_h7:
                    ShowTcpVisible(true);
                    break;
            }
        }
    };

    private void initTcp() {
        TUdpManage.getInstance().SetContext(ConnectActivity.this);
        TUdpManage.getInstance().mWifiInfoBack = mCallback;
    }

    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FindDeviceIP.IS_DEVICE_IP) { // 内容
                etAddress.setText(msg.getData().getString("ip"));
            } else if (msg.what == 2) {
                WifiModInfo wifiInfo = (WifiModInfo) msg.obj;
                etAddress.setText(wifiInfo.IP);
                etPort.setText(String.valueOf(wifiInfo.ServerPort));
            }
        }
    };

    private ICallbackWifiInfo mCallback = new ICallbackWifiInfo() {
        @Override
        public void execute(WifiModInfo wifiInfo) {
            Message msg = new Message();
            msg.what = 2;
            msg.obj = wifiInfo;
            msgHandler.sendMessage(msg);
        }
    };

    @OnClick({R.id.btn_search, R.id.tv_connect_reader, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                FindDeviceIP find = new FindDeviceIP();
                find.sendUdpCommand(ConnectActivity.this, msgHandler);
                TUdpManage.getInstance().ScanWifiMod();
                break;
            case R.id.tv_connect_reader:
                ConnectReader();
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
