package com.osmar.tcc_mobile;

import com.osmar.tcc_mobile.Api.PlacaInterfaceApi;
import com.osmar.tcc_mobile.model.Componente;


import junit.framework.TestCase;


import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.core.Is.is;

public class AdicionarComponenteTest extends TestCase {

    private List<Componente> listComponentes =new ArrayList<>();
    private Retrofit retrofit;


    @Before
    public void setUp(){


        retrofit=new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:2233")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //16 e 2
        Componente componente=new Componente("luz da sala", "ilumina a sala",1,16);
        listComponentes.add(componente);

        componente=new Componente("luz da cozinha", "ilumina a cozinha",1,2);
        listComponentes.add(componente);


    }

    @Test
    public void testCriarCompenente(){



        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        for(int i=0;i<listComponentes.size();i++){
            final Componente c=listComponentes.get(i);
            Call<Componente> call = placaInterfaceApi.adicionarComponente(c);
            call.enqueue(new Callback<Componente>() {
                @Override
                public void onResponse(Call<Componente> call, Response<Componente> response) {
                    if(response.isSuccessful()){


                        assertEquals(2,3);
                    }

                }

                @Override
                public void onFailure(Call<Componente> call, Throwable t) {

                }
            });

        }
    }

    @After
    public void tearDown(){
        listComponentes.clear();
    }
}
