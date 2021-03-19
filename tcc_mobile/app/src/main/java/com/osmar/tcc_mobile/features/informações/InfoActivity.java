package com.osmar.tcc_mobile.features.informações;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;

public class InfoActivity extends AppCompatActivity {
    private ImageView imgVoltar;
    private ComponenteAdpterLed componenteButao;
    private EditText editComponenteName;
    private EditText editComponenteDescricao;
    private TextView txtPinoEscolhido;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences estado = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if(estado.getBoolean("bkey", true) == false){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(estado.getBoolean("bkey", true) == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        imgVoltar=findViewById(R.id.imgVoltar2);
        editComponenteDescricao=findViewById(R.id.editTextComponenteDescricaoRegistro2);
        editComponenteName=findViewById(R.id.editTextComponenteNameRegistro2);
        txtPinoEscolhido=findViewById(R.id.txtPinoEscolhidoInfo);
        Bundle dadosComponente =getIntent().getExtras();
        componenteButao =(ComponenteAdpterLed)dadosComponente.getSerializable("componente");

        editComponenteName.setText(componenteButao.getName());
        editComponenteDescricao.setText(componenteButao.getDescription());
        txtPinoEscolhido.setText(""+componenteButao.getPin());
        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}