package com.osmar.tcc_mobile.features.config;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveState {
    Context context;
    SharedPreferences sharedPreferences;

    public SaveState(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void setState(Boolean b) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("bkey", b);
        editor.apply();
        editor.commit();
    }

    public Boolean getState(){
        return sharedPreferences.getBoolean("bkey", true);
    }
}
