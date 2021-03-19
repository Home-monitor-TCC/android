package com.osmar.tcc_mobile.features.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.osmar.tcc_mobile.features.informações.InfoActivity;
import com.osmar.tcc_mobile.features.registrar.RegistrarComponente;
import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerComponentes;
    private ImageView imgButtonConfig;





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

        listarC();
        Toast.makeText(getApplicationContext(),"veio do onStart",Toast.LENGTH_LONG).show();
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
                Log.e("TAGcomponenteAdapter", "" + componenteAdpters.size());
                listaAtualizaveldeComponentes = componenteAdpters;
                for(int i=0;i<listaAtualizaveldeComponentes.size();i++){
                    ComponenteAdpter c = listaAtualizaveldeComponentes.get(i);
                    c.setImgEstadoResource(R.drawable.ic_icon_metro_switch);
                    listaAtualizaveldeComponentes.set(i,c);
                }
                Log.e("TamanhoAdptergfdsfg","" + listaAtualizaveldeComponentes.get(0).getDescription());
                Log.e("TAGcomponenteatuali", "" + listaAtualizaveldeComponentes.size());
                Adpter adpter =new Adpter((ArrayList<ComponenteAdpter>) listaAtualizaveldeComponentes);
                //Tudo parada do recycler
                recyclerComponentes=findViewById(R.id.recyclerComponentes);


                RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
                recyclerComponentes.setLayoutManager(layoutManager);
                recyclerComponentes.setHasFixedSize(true);
                Log.e("atenoadpterdapau","tamanhodalistaque vai pro adpter"+adpter.getItemCount());
                recyclerComponentes.setAdapter(adpter);

                recyclerComponentes.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerComponentes,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent =new Intent(getApplicationContext(), InfoActivity.class);

                                        ComponenteAdpter componente =listaAtualizaveldeComponentes.get(position);
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
                Log.e("Tag Jesus","Tamanho abencoado:"+listaAtualizaveldeComponentes.size());
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
         retrofitRequisicao.listarComponentes();
         Log.e("tamanhoLista", "tamanho"+listaAtualizaveldeComponentes.size());
    }


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


}