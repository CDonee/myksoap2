package com.lubin.chj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lubin on 2016/8/31.
 */
public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_WORLD_WRITEABLE);
        editor = sp.edit();
    }


    public void setPower(int power) {
        editor.putInt("power", power);
        editor.commit();
    }

    public int getPower() {
        return sp.getInt("power", 30);
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public String getName() {
        return sp.getString("name","");
    }

    public void setPassword(String password){
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword(){
        return sp.getString("password","");
    }

}