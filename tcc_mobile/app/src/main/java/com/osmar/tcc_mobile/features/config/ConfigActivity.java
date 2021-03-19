package com.osmar.tcc_mobile.features.config;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.osmar.tcc_mobile.R;

public class ConfigActivity extends AppCompatActivity {
    private ImageView imageViewVoltar;
    private Switch fundo_app;
    SaveState saveState = new SaveState(this);


    @Override
    protected void onStart() {
        super.onStart();

        fundo_app.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveState.setState(true);
                    //Osmar, se o switch ta como true , que ´o padrão dele , então o fundo fica no escuro , se for falso voce muda para claro
                }
                else{
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveState.setState(false);
                    //Aqui fica no claro , obviamente
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        imageViewVoltar=findViewById(R.id.imgVoltar2);
        fundo_app=findViewById(R.id.switchTema);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}