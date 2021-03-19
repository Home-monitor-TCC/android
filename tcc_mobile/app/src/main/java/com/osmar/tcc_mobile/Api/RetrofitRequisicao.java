package com.osmar.tcc_mobile.Api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

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
    private List<ComponenteAdpter> listaDeComponentes=new ArrayList<>();
    public MutableLiveData<List<ComponenteAdpter>> listMutableLiveData=new MutableLiveData<List<ComponenteAdpter>>();

    public List<ComponenteAdpter> getListaDeComponentes() {
        return listaDeComponentes;
    }

    public void setListaDeComponentes(List<ComponenteAdpter> listaDeComponentes) {
        this.listaDeComponentes = listaDeComponentes;
    }

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

    public void listarComponentes(){


        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ListaDeComponentes> call=placaInterfaceApi.listarComponentes();

        call.enqueue(new Callback<ListaDeComponentes>() {
            @Override
            public void onResponse(Call<ListaDeComponentes> call, Response<ListaDeComponentes> response) {
                if(response.isSuccessful()){
                    ListaDeComponentes lista=response.body();

                    ComponenteAdpter componenteAdpter;


                    for(ComponenteAdpterLed componenteAdpterLed : lista.getLeds()){
                        componenteAdpter=componenteAdpterLed;

                        Log.e("Led id", componenteAdpter.getId());
                        Log.e("Led pino", "" + componenteAdpter.getPin());

                        listaDeComponentes.add(componenteAdpter);


                    }

                    for(ComponenteAdpterSensor componenteAdpterSensor : lista.getTemperatureSensors()){
                        componenteAdpter=componenteAdpterSensor;



                        listaDeComponentes.add(componenteAdpter);
                    }

                    listMutableLiveData.setValue(listaDeComponentes);
                    Log.e("tamanhoLista", "listaDeComponentes " + listaDeComponentes.size());

                }else{

                    Toast.makeText(context,"deu pau",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ListaDeComponentes> call, Throwable t) {
            }
        });

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

    /*public List<Integer> listarPinos(){


        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<List<Integer>> call = placaInterfaceApi.listarPinos();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                List<Integer> lista =new ArrayList<>();


            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
      return lis;
    }*/

}
