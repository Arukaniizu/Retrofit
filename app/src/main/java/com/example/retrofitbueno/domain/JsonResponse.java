package com.example.retrofitbueno.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    //creamos una lista de PokemonsList
    @SerializedName("results")
    @Expose
    public List<PokemonList> results = null;
}
