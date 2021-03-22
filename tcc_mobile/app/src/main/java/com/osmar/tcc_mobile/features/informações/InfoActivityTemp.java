package com.osmar.tcc_mobile.features.informações;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.osmar.tcc_mobile.Api.RetrofitRequisicao;
import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;
import com.osmar.tcc_mobile.model.ComponenteAdpterSensor;

public class InfoActivityTemp extends AppCompatActivity{
    private ImageView imgVoltar;
    private ComponenteAdpterSensor componenteButao;
    private EditText editComponenteName;
    private EditText editComponenteDescricao;
    private TextView txtPinoEscolhido;
    private TextView temp;
    private Button btnSalvar;
    private Button  btnExcluir;
    private RetrofitRequisicao retrofitRequisicao;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences estado = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if(estado.getBoolean("bkey", true) == false){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if(estado.getBoolean("bkey", true) == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        temp.setText(componenteButao.getTemperature() + "°C");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sensor);
        imgVoltar=findViewById(R.id.imgVoltar2);
        editComponenteDescricao=findViewById(R.id.editTextComponenteDescricaoRegistro2);
        editComponenteName=findViewById(R.id.editTextComponenteNameRegistro2);
        txtPinoEscolhido=findViewById(R.id.txtPinoEscolhidoInfo);
        btnSalvar=findViewById(R.id.btnSalvarLed);
        btnExcluir=findViewById(R.id.btnExcluirLed);
        temp = findViewById(R.id.temperature);
        retrofitRequisicao=new RetrofitRequisicao(getApplicationContext());
        Bundle dadosComponente =getIntent().getExtras();
        componenteButao =(ComponenteAdpterSensor) dadosComponente.getSerializable("componente");

        editComponenteName.setText(componenteButao.getName());
        editComponenteDescricao.setText(componenteButao.getDescription());
        txtPinoEscolhido.setText(""+componenteButao.getPin());
        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome =editComponenteName.getText().toString();
                String des=editComponenteDescricao.getText().toString();
                ComponenteAdpter componenteAdpter=new ComponenteAdpter(componenteButao.getId(),nome,des);
                retrofitRequisicao.editarComponente(componenteAdpter,getApplicationContext());
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitRequisicao.removerComponente(componenteButao,getApplicationContext());
                finish();
            }
        });

    }

}
