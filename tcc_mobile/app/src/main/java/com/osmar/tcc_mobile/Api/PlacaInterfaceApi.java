package com.osmar.tcc_mobile.Api;

import com.osmar.tcc_mobile.model.Componente;
import com.osmar.tcc_mobile.model.ComponenteResposta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface PlacaInterfaceApi {

    @POST("/componentes/adicionar")
    Call<ComponenteResposta> adicionarComponente(@Body Componente componente);

    @DELETE("/componentes/remover")
    Call<ComponenteResposta> removerComponente(@Body Componente componente);

    @PATCH("/componentes/editar")
    Call<ComponenteResposta> editarComponente(@Body Componente componente);

    @PATCH("/componentes/led/acender")
    Call<Componente> acenderComponente(@Body Componente componente);

    @PATCH("/componentes/led/apagar")
    Call<Componente> apagarComponente(@Body Componente componente);

}
