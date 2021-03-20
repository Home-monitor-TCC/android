package com.osmar.tcc_mobile.Api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;
import com.osmar.tcc_mobile.model.ComponenteAdpterSensor;
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


    public MutableLiveData<List<String>> listMutableLiveDataPinos=new MutableLiveData<>();
    private List<String> listaDePinos=new ArrayList<>();




    public List<ComponenteAdpter> getListaDeComponentes() {
        return listaDeComponentes;
    }

    public void setListaDeComponentes(List<ComponenteAdpter> listaDeComponentes) {
        this.listaDeComponentes = listaDeComponentes;
    }

    public RetrofitRequisicao(Context context){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.228:2233")
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

    public void listarPinos(){
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<List<Integer>> call = placaInterfaceApi.listarPinos();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.isSuccessful()){
                    for(int i=0;i<response.body().size();i++){
                        listaDePinos.add(response.body().get(i)+"");
                    }

                    listMutableLiveDataPinos.setValue(listaDePinos);

                }else{

                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });




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

    public void criarComponente(ComponenteAdpter componente, final Context context){

        Log.e("A Pino", "" + componente.getPin());
        Log.e("A type", "" + componente.getType());
        Log.e("A name", "" + componente.getName());
        Log.e("A descri", "" + componente.getDescription());

        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<ComponenteAdpter> call = placaInterfaceApi.adicionarComponente(componente);
        call.enqueue(new Callback<ComponenteAdpter>() {
            @Override
            public void onResponse(Call<ComponenteAdpter> call, Response<ComponenteAdpter> response) {
                if(response.isSuccessful()){
                    ComponenteAdpter componenteResposta =response.body();
                    Toast.makeText(context,"O novo componente foi registrado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ComponenteAdpter> call, Throwable t) {

            }


        });
    }


    public void removerComponente(ComponenteAdpter componente, final Context context){
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<Void> call =placaInterfaceApi.removerComponente(componente);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context,"Componente apagado",Toast.LENGTH_LONG).show();


                }else{

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }

    public void editarComponente( ComponenteAdpter componente, final Context context){
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Log.e("Componente id nome ",""+componente.getId()+"/"+componente.getName()+"/"+componente.getDescription());
        Call<ComponenteAdpter> call = placaInterfaceApi.editarComponente(componente);
        call.enqueue(new Callback<ComponenteAdpter>() {
            @Override
            public void onResponse(Call<ComponenteAdpter> call, Response<ComponenteAdpter> response) {
                if(response.isSuccessful()){
                    ComponenteAdpter componenteResposta =response.body();
                    Log.e(" Mudando o nome ","/"+componenteResposta.getName()) ;
                Toast.makeText(context,"O componente foi editado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ComponenteAdpter> call, Throwable t) {

            }
        });
    }



}
