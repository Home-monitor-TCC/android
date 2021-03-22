package com.osmar.tcc_mobile.features.registrar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.osmar.tcc_mobile.Api.RetrofitRequisicao;
import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.ComponenteAdpter;

import java.util.ArrayList;
import java.util.List;

public class RegistrarComponente extends AppCompatActivity {

    //Aqui é nosso obersvador
    private LiveData<List<String>> listaComponentesObservadoPinos;
    //Iremos criar aqui nossa lista que sera atualizada de acordo com oque acontecer na listaMutavel da outra classe
    private List<String> listaAtualizaveldePinos=new ArrayList<>();

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


    @Override
    protected void onStart() {
        super.onStart();
        //Puxar os pinos disponiveis aqui
        ;
        listaAtualizaveldePinos.clear();
        retrofitRequisicao.listarPinos();

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

        retrofitRequisicao=new RetrofitRequisicao(getApplicationContext());
        setContentView(R.layout.activity_registrar_componente);


        btnConfirmar=findViewById(R.id.btnOnOffLed);
        editTextComponenteName=findViewById(R.id.editTextComponenteNameRegistro2);
        editTextComponenteDes=findViewById(R.id.editTextComponenteDescricaoRegistro2);
        txtComponentePino=findViewById(R.id.txtPinoEscolhidoRegistar2);
        txtComponenteTipo=findViewById(R.id.txtTipoComponenteRegistrar);
        btnSair=findViewById(R.id.btnSairRegistrar2);
        //Lista
        listView=findViewById(R.id.list_Pinos);
        listViewTipo=findViewById(R.id.list_Tipo);

         Observer<List<String>> listaObservadorPinos= new Observer<List<String>>() {
             @Override
             public void onChanged(List<String> strings) {
                 listaAtualizaveldePinos=strings;
                 ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                         getApplicationContext(),
                         android.R.layout.simple_list_item_1,
                         android.R.id.text1,
                         listaAtualizaveldePinos
                 );







                 listView.setAdapter(adaptador);


                 UIUtils.setListViewHeightBasedOnItems(listView);



             }
         } ;
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tipo
        );
        listViewTipo.setAdapter(adaptador2);

        UIUtils.setListViewHeightBasedOnItems(listViewTipo);
        listaComponentesObservadoPinos=retrofitRequisicao.listMutableLiveDataPinos;
        listaComponentesObservadoPinos.observe(this,listaObservadorPinos);







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


        listView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // Indicates that this has been handled by you and will not be forwarded further.
                }
                return false;
            }
        });

        listViewTipo.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // Indicates that this has been handled by you and will not be forwarded further.
                }
                return false;
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


                ComponenteAdpter componente =new ComponenteAdpter(nome,des,tipo2,pino);
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