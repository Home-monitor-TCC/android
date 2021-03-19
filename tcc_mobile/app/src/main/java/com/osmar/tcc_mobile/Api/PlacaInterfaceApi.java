package com.osmar.tcc_mobile.Api;

import com.google.gson.JsonObject;
import com.osmar.tcc_mobile.model.Componente;
import com.osmar.tcc_mobile.model.ComponenteResposta;
import com.osmar.tcc_mobile.model.ListaDeComponentes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface PlacaInterfaceApi {

    @GET("/db")
    Call<ListaDeComponentes> listarComponentes();

    @GET("/componentes/pinos")
    Call<List<Integer>> listarPinos();

    @POST("/componentes/adicionar")
    Call<ComponenteResposta> adicionarComponente(@Body Componente componente);

    @POST("/componentes/remover")
    Call<ComponenteResposta> removerComponente(@Body Componente componente);

    @POST("/componentes/editar")
    Call<ComponenteResposta> editarComponente(@Body Componente componente);

    @POST("/componentes/led/acender")
    Call<Componente> acenderComponente(@Body Componente componente);

    @POST("/componentes/led/apagar")
    Call<Componente> apagarComponente(@Body Componente componente);

}
