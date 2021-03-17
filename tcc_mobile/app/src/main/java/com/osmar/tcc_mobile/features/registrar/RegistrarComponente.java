package com.osmar.tcc_mobile.features.registrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
    private TextView txtComponenteTipo;
    private ListView listView;
    private ListView listViewTipo;
    private String[] tipo={"Led", "Temperatura"};
    private String[] pinos={"1", "2"};;

    @Override
    protected void onStart() {
        super.onStart();
        //Puxar os pinos disponiveis aqui


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofitRequisicao=new RetrofitRequisicao(getApplicationContext());
        setContentView(R.layout.activity_registrar_componente);


        btnConfirmar=findViewById(R.id.btnConfirmarRegistro2);
        editTextComponenteName=findViewById(R.id.editTextComponenteNameRegistro2);
        editTextComponenteDes=findViewById(R.id.editTextComponenteDescricaoRegistro2);
        txtComponentePino=findViewById(R.id.txtPinoEscolhidoRegistar2);
        txtComponenteTipo=findViewById(R.id.txtTipoComponenteRegistrar);
        btnSair=findViewById(R.id.btnSairRegistrar2);
        //Lista
        listView=findViewById(R.id.list_Pinos);
        listViewTipo=findViewById(R.id.list_Tipo);


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                pinos
        );

        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tipo
        );





        listView.setAdapter(adaptador);
        listViewTipo.setAdapter(adaptador2);


        Log.i("depois do ListView","fodas listView");

        txtComponentePino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    listView.setVisibility(View.VISIBLE);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            txtComponentePino.setText(listView.getItemAtPosition(position).toString());
                            listView.setVisibility(View.GONE);
                        }
                    });

            }
        });

        txtComponenteTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    listViewTipo.setVisibility(View.VISIBLE);
                    listViewTipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            txtComponenteTipo.setText(listViewTipo.getItemAtPosition(position).toString());
                            listViewTipo.setVisibility(View.GONE);
                        }
                    });

            }
        });



        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome=editTextComponenteName.getText().toString();
                String des=editTextComponenteDes.getText().toString();
                int pino= Integer.parseInt(txtComponentePino.getText().toString());
                int tipo2;
                //Coloquei o tipo sempre como um porque sei la, temos que ver este trem de tipo.
                String tipoComponente=txtComponenteTipo.getText().toString();
                switch (tipoComponente){
                    case "Led":
                        tipo2=1;
                        break;


                    case "Temperatura":
                        tipo2=2;
                        break;

                    default:
                        tipo2=0;
                }


                Componente componente =new Componente(nome,des,pino,tipo2);
                retrofitRequisicao.criarComponente(componente,getApplicationContext());
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