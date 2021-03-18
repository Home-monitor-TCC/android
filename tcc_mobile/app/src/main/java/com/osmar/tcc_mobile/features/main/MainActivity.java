package com.osmar.tcc_mobile.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.osmar.tcc_mobile.Adpter.Adpter;
import com.osmar.tcc_mobile.Api.RetrofitRequisicao;
import com.osmar.tcc_mobile.ClassesImportadas.RecyclerItemClickListener;
import com.osmar.tcc_mobile.PopUp.AlertDialogAdComponente;
import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.features.config.ConfigActivity;
import com.osmar.tcc_mobile.features.informações.InfoActivity;
import com.osmar.tcc_mobile.features.registrar.RegistrarComponente;
import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerComponentes;
    private ImageView imgButtonConfig;
    private ArrayList<ComponenteAdpter> listaComponentes=new ArrayList<ComponenteAdpter>();



    //
    private RetrofitRequisicao retrofitRequisicao;
    //


    @Override
    protected void onStart() {
        super.onStart();
        listarComponentes();
        Toast.makeText(getApplicationContext(),"veio do onStart",Toast.LENGTH_LONG).show();
    }



    //
    public void criarPopUp(){
        AlertDialogAdComponente alertDialogAdComponente = new AlertDialogAdComponente(this, "Excluir componente", "Você tem certeza que quer excluir este componente ?");
        AlertDialog.Builder alertDialog = alertDialogAdComponente.getAlertDialog();

        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Requisição de excluir componente
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Fecha PopUp
            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //
        retrofitRequisicao = new RetrofitRequisicao(getApplicationContext());
        //



        setContentView(R.layout.activity_main);
        imgButtonConfig=findViewById(R.id.imgBtnConfig);
        imgButtonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = findViewById(R.id.fabPrincipal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), RegistrarComponente.class);
                startActivity(intent);

            }
        });


        recyclerComponentes=findViewById(R.id.recyclerComponentes);
        Adpter adpter =new Adpter(listaComponentes);

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerComponentes.setLayoutManager(layoutManager);
        recyclerComponentes.setHasFixedSize(true);
        recyclerComponentes.setAdapter(adpter);

        recyclerComponentes.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerComponentes,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent =new Intent(getApplicationContext(), InfoActivity.class);

                                ComponenteAdpter componente =listaComponentes.get(position);
                                intent.putExtra("componente",componente);
                                startActivity(intent);



                            }

                            @Override
                            public void onLongItemClick(View view, int position) {


                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )

        );


    }


    //Metodo de testes para os Componentes

    public void listarComponentes()
    {

        listaComponentes=retrofitRequisicao.listarComponentes();

        for(int i=0;i<listaComponentes.size();i++){
            ComponenteAdpter c =listaComponentes.get(i);
            if(c.getType()==1){
                c.setImgEstadoResource(R.drawable.ic_icon_metro_switch);
            }else{
                c.setImgEstadoResource(R.drawable.ic_icon_open_pencil);
            }
            listaComponentes.set(i,c);

        }


    }


}