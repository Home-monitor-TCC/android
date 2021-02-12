package com.osmar.tcc_mobile.features.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.osmar.tcc_mobile.Adpter.Adpter;
import com.osmar.tcc_mobile.ClassesImportadas.RecyclerItemClickListener;
import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.features.config.ConfigActivity;
import com.osmar.tcc_mobile.features.informações.InfoActivity;
import com.osmar.tcc_mobile.features.registrar.RegistrarComponente;
import com.osmar.tcc_mobile.model.Componente;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerComponentes;
    private ArrayList<Componente> arrayComponentes=new ArrayList<>();
    private ImageView imgButtonConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        criarComponentes();
        recyclerComponentes=findViewById(R.id.recyclerComponentes);
        Adpter adpter =new Adpter(arrayComponentes);

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

                                Componente componente =arrayComponentes.get(position);
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

    public void criarComponentes()
    {
        Componente componente =new Componente("Luz da Sala","o led que acende a sala","LED",123456,R.drawable.ic_icon_metro_switch,true);
        arrayComponentes.add(componente);

        componente =new Componente("Luz da Cozinha","o led que acende a Cozinha","LED",139784,R.drawable.ic_icon_metro_switch,true);
        arrayComponentes.add(componente);

        componente =new Componente("Luz da Casa do Fifi","o led que acende a casa do filardi","LED",193126,R.drawable.ic_icon_metro_switch,true);
        arrayComponentes.add(componente);
        componente =new Componente("Luz do osmose","A toca do osmar tem que ter luz","LED",46556,R.drawable.ic_icon_metro_switch,true);
        arrayComponentes.add(componente);
        componente =new Componente("Luz da Comoda","o led que acende algo","LED",156765,R.drawable.ic_icon_metro_switch,true);
        arrayComponentes.add(componente);



    }


}