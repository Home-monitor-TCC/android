package com.osmar.tcc_mobile.Api;

import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;
import com.osmar.tcc_mobile.model.ListaDeComponentes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlacaInterfaceApi {

    @GET("/db")
    Call<ListaDeComponentes> listarComponentes();

    @GET("/componentes/pinos")
    Call<List<Integer>> listarPinos();

    @POST("/componentes/adicionar")
    Call<ComponenteAdpter> adicionarComponente(@Body ComponenteAdpter componente);

    @POST("/componentes/remover")
    Call<Void> removerComponente(@Body ComponenteAdpter componente);

    @POST("/componentes/editar")
    Call<ComponenteAdpter> editarComponente(@Body ComponenteAdpter componente);

    @POST("/componentes/led/acender")
    Call<ComponenteAdpterLed> acenderComponente(@Body ComponenteAdpter componente);

    @POST("/componentes/led/apagar")
    Call<ComponenteAdpterLed> apagarComponente(@Body ComponenteAdpter componente);

}
