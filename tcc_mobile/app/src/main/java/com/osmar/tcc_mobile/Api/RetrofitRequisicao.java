package com.osmar.tcc_mobile.Api;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.osmar.tcc_mobile.features.main.MainActivity;
import com.osmar.tcc_mobile.model.Componente;
import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;
import com.osmar.tcc_mobile.model.ComponenteAdpterSensor;
import com.osmar.tcc_mobile.model.ComponenteResposta;
import com.osmar.tcc_mobile.model.ListaDeComponentes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequisicao {
    private static Retrofit retrofit;
    private Context context;
    public RetrofitRequisicao(Context context){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.15.23:2233")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.context=context;

    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ArrayList<ComponenteAdpter> listarComponentes(){

        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ListaDeComponentes> call=placaInterfaceApi.listarComponentes();
        final ArrayList<ComponenteAdpter> finalListaDeComponentes = new ArrayList<ComponenteAdpter>();
        call.enqueue(new Callback<ListaDeComponentes>() {
            @Override
            public void onResponse(Call<ListaDeComponentes> call, Response<ListaDeComponentes> response) {
                if(response.isSuccessful()){
                    ListaDeComponentes lista=response.body();
                    ComponenteAdpter componenteAdpter;
                    for(ComponenteAdpterLed componenteAdpterLed : lista.getLeds()){
                        componenteAdpter=componenteAdpterLed;
                        finalListaDeComponentes.add(componenteAdpter);
                    }

                    for(ComponenteAdpterSensor componenteAdpterSensor : lista.getTemperatureSensors()){
                        componenteAdpter=componenteAdpterSensor;
                        finalListaDeComponentes.add(componenteAdpter);
                    }



                }else{

                    Toast.makeText(context,"deu pau",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ListaDeComponentes> call, Throwable t) {

            }
        });

        return finalListaDeComponentes;
    }

    public void criarComponente(Componente componente, final Context context){

        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ComponenteResposta> call = placaInterfaceApi.adicionarComponente(componente);
        call.enqueue(new Callback<ComponenteResposta>() {
            @Override
            public void onResponse(Call<ComponenteResposta> call, Response<ComponenteResposta> response) {
                if(response.isSuccessful()){
                    ComponenteResposta componenteResposta =response.body();
                    Toast.makeText(context,"O novo componente foi registrado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ComponenteResposta> call, Throwable t) {

            }
        });
    }

    public void removerComponente(Componente componente, final Context context){
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ComponenteResposta> call = placaInterfaceApi.removerComponente(componente);
        call.enqueue(new Callback<ComponenteResposta>() {
            @Override
            public void onResponse(Call<ComponenteResposta> call, Response<ComponenteResposta> response) {
                if(response.isSuccessful()){
                    ComponenteResposta componenteResposta =response.body();
                    Toast.makeText(context,"O componente foi apagado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ComponenteResposta> call, Throwable t) {

            }
        });
    }

    public void editarComponente(Componente componente, final Context context){
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ComponenteResposta> call = placaInterfaceApi.editarComponente(componente);
        call.enqueue(new Callback<ComponenteResposta>() {
            @Override
            public void onResponse(Call<ComponenteResposta> call, Response<ComponenteResposta> response) {
                if(response.isSuccessful()){
                    ComponenteResposta componenteResposta =response.body();
                    Toast.makeText(context,"O componente foi editado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ComponenteResposta> call, Throwable t) {

            }
        });
    }
}
