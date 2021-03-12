package com.osmar.tcc_mobile.Api;

import android.content.Context;
import android.widget.Toast;

import com.osmar.tcc_mobile.model.Componente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequisicao {
    private static Retrofit retrofit;
    public RetrofitRequisicao(Context context){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void criarComponente(Componente componente, final Context context){
        /*
        PlacaInterfaceApi placaInterfaceApi=retrofit.create(PlacaInterfaceApi.class);
        Call<Componente> call = placaInterfaceApi.adicionarComponente(componente);
        call.enqueue(new Callback<Componente>() {
            @Override
            public void onResponse(Call<Componente> call, Response<Componente> response) {
                if(response.isSuccessful()){
                    Componente c =response.body();
                    Toast.makeText(context,"O novo componente foi registrado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    ApiError apiError=ErrorUtils.parseError(response);
                    Toast.makeText(context,apiError.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Componente> call, Throwable t) {

            }
        });

         */
    }
}
