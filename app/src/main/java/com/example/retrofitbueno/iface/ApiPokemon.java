package com.example.retrofitbueno.iface;

import com.example.retrofitbueno.common.Constantes;
import com.example.retrofitbueno.domain.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiPokemon {
    //Que tipo de archivo vas a utilizar
    @Headers({"Accept: application/json , Content-Type: application/json"})
    //GET para recibir recursos
    @GET(Constantes.POKEMON_POKEMON)
    //este get seria con un path
  //  @GET("/{name}")
    //Tipo de la libreria retrofit para recibir los datos  @Query para pasar un parametro (latitud longitud etc)
   // Call<JsonResponse> getListPokemon(@Query("latitude")Double latitud);  tu nombre, datos y nombre de API
    //@Path para pasarle un path
   // Call<JsonResponse> getListPokemon(@Path("name") String name);
        Call<JsonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offsetv);
}
