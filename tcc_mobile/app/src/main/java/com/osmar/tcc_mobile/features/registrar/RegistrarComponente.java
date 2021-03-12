package com.osmar.tcc_mobile.features.registrar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.osmar.tcc_mobile.Api.RetrofitRequisicao;
import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.Componente;

public class RegistrarComponente extends AppCompatActivity {
    private ImageView btnConfirmar;
    private ImageView btnSair;
    private RetrofitRequisicao retrofitRequisicao;
    private EditText editTextComponenteName;
    private EditText editTextComponenteDes;
    private TextView txtComponentePino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retrofitRequisicao=new RetrofitRequisicao(null); Isto aqui que ta dando pau, mas imagino que seja porque a base url n faz sentido nenhum
        setContentView(R.layout.activity_registrar_componente);


        btnConfirmar=findViewById(R.id.btnConfirmarRegistro2);
        editTextComponenteName=findViewById(R.id.editTextComponenteNameRegistro2);
        editTextComponenteDes=findViewById(R.id.editTextComponenteDescricaoRegistro2);
        txtComponentePino=findViewById(R.id.txtPinoEscolhidoRegistar2);


        btnSair=findViewById(R.id.btnSairRegistrar2);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome=editTextComponenteName.getText().toString();
                String des=editTextComponenteDes.getText().toString();
                int pino= Integer.parseInt(txtComponentePino.getText().toString());
                //Coloquei o tipo sempre como um porque sei la, temos que ver este trem de tipo.
                int tipo=1;

                Componente componente =new Componente(nome,des,pino,tipo);
                //retrofitRequisicao.criarComponente(componente,getApplicationContext());
                finish();
            }
        });



        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}