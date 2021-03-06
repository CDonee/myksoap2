package com.lubin.chj.modle;

import android.util.Log;

import com.lubin.chj.Listener.OnLoginFinshListener;
import com.lubin.chj.bean.LoginReturn;
import com.lubin.chj.bean.User;
import com.lubin.chj.modle.MInterface.ILoginModel;
import com.lubin.chj.utils.SoapUtil;

import org.ksoap2.serialization.SoapObject;

/**
 * @author DaiJiCheng
 * @time 2016/9/29  9:38
 * @desc ${TODD}
 */
public class LoginModelImpl implements ILoginModel {
    private LoginReturn mLoginReturn;

    @Override
    public void login(User user, final OnLoginFinshListener listener) {
        SoapUtil.Login(user, new OnLoginFinshListener() {

            @Override
            public void onLoginFinish(Object result) {
                try {
                    Log.d("test", "res---------->: "+result.toString());
                    SoapObject info = (SoapObject) result;
                    mLoginReturn = new LoginReturn();
                    mLoginReturn.setReturnCode(info.getProperty("returnCode").toString());
                    mLoginReturn.setReturnMsg(info.getProperty("returnMsg").toString());
                    mLoginReturn.setRightID(info.getProperty("rightID").toString());
                    listener.onLoginFinish(mLoginReturn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
