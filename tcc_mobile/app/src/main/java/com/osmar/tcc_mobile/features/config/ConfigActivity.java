package com.osmar.tcc_mobile.features.config;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.osmar.tcc_mobile.R;

public class ConfigActivity extends AppCompatActivity {
    private ImageView imageViewVoltar;
    private Switch fundo_app;
    //SaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // saveState = new SaveState(this);
       // if(saveState.getState() == true){
        //    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
       //     fundo_app.setChecked(true);
       // }else{
       //     getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
       //     fundo_app.setChecked(false);
       // }

        setContentView(R.layout.activity_config);
        imageViewVoltar=findViewById(R.id.imgVoltar2);
        fundo_app=findViewById(R.id.switchTema);

        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fundo_app.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b!=true)
                {
                    //saveState.setState(false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    //Osmar, se o switch ta como true , que ´o padrão dele , então o fundo fica no escuro , se for falso voce muda para claro
                }
                else{
                    //saveState.setState(true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //Aqui fica no claro , obviamente
                }


            }
        });


    }
}