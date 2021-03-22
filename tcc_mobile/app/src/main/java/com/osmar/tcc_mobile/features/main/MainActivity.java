package com.osmar.tcc_mobile.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintProperties;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.osmar.tcc_mobile.features.config.SaveState;
import com.osmar.tcc_mobile.features.informações.InfoActivity;
import com.osmar.tcc_mobile.features.informações.InfoActivityTemp;
import com.osmar.tcc_mobile.features.registrar.RegistrarComponente;
import com.osmar.tcc_mobile.model.ComponenteAdpter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerComponentes;
    private ImageView imgButtonConfig;

    private ConstraintLayout mainC;

    //
    private RetrofitRequisicao retrofitRequisicao;
    //Aqui é nosso obersvador
    private LiveData<List<ComponenteAdpter>> listaComponentesObservador;
    //Iremos criar aqui nossa lista que sera atualizada de acordo com oque acontecer na listaMutavel da outra classe
    private List<ComponenteAdpter> listaAtualizaveldeComponentes=new ArrayList<>();




    public List<ComponenteAdpter> getListaAtualizaveldeComponentes() {
        return listaAtualizaveldeComponentes;
    }

    public void setListaAtualizaveldeComponentes(List<ComponenteAdpter> listaAtualizaveldeComponentes) {
        this.listaAtualizaveldeComponentes = listaAtualizaveldeComponentes;
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences estado = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if(estado.getBoolean("bkey", true) == false){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else if(estado.getBoolean("bkey", true) == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        listarC();
    }



    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        retrofitRequisicao = new RetrofitRequisicao(getApplicationContext());
        setContentView(R.layout.activity_main);


        //é o parametro transmitido no observador que vai ativar o onchanged quando acontecer algo na lista
        Observer<List<ComponenteAdpter>> listaObservador= new Observer<List<ComponenteAdpter>>() {
            @Override
            public void onChanged(List<ComponenteAdpter> componenteAdpters) {

                listaAtualizaveldeComponentes = componenteAdpters;
                for(int i=0;i<listaAtualizaveldeComponentes.size();i++){
                    ComponenteAdpter c = listaAtualizaveldeComponentes.get(i);

                    c.setImgEstadoResource(R.drawable.ic_icon_metro_switch);
                    listaAtualizaveldeComponentes.set(i,c);
                }

                Adpter adpter =new Adpter((ArrayList<ComponenteAdpter>) listaAtualizaveldeComponentes);
                //Tudo parada do recycler
                recyclerComponentes=findViewById(R.id.recyclerComponentes);


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



                                        ComponenteAdpter componente =listaAtualizaveldeComponentes.get(position);

                                        if(componente.getType() == 1){
                                            Intent intent =new Intent(getApplicationContext(), InfoActivity.class);
                                            intent.putExtra("componente",componente);
                                            startActivity(intent);
                                        }else{
                                            Intent intent =new Intent(getApplicationContext(), InfoActivityTemp.class);
                                            intent.putExtra("componente",componente);
                                            startActivity(intent);
                                        }




                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {
                                        //ComponenteAdpter componente =listaAtualizaveldeComponentes.get(position);


                                        //criarPopUp(retrofitRequisicao,componente);

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    }
                                }
                        )

                );

            }

        };






        // A gente ta mandando nosso observador observar a lista mutavel
        listaComponentesObservador=retrofitRequisicao.listMutableLiveData;

        listaComponentesObservador.observe(this, listaObservador);
        imgButtonConfig=findViewById(R.id.imgBtnConfig);
        imgButtonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(intent);

            }
        });


        //FAB
        FloatingActionButton fab = findViewById(R.id.fabPrincipal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), RegistrarComponente.class);
                startActivity(intent);

            }
        });
    }




    public void listarC()
    {
        listaAtualizaveldeComponentes.clear();
         retrofitRequisicao.listarComponentes();

    }




    /*public void criarPopUp(final RetrofitRequisicao retrofitDoPop, final ComponenteAdpter componente){
        AlertDialogAdComponente alertDialogAdComponente = new AlertDialogAdComponente(this, "Excluir componente", "Você tem certeza que quer excluir este componente ?");
        AlertDialog.Builder alertDialog = alertDialogAdComponente.getAlertDialog();

        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                retrofitDoPop.removerComponente(componente,getApplicationContext());

                listarC();
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Fecha PopUp
            }
        });

        alertDialog.create();
        alertDialog.show();
    }*/


}