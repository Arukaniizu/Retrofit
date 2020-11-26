package com.example.retrofitbueno.acivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.retrofitbueno.R;
import com.example.retrofitbueno.common.Constantes;
import com.example.retrofitbueno.common.PokemonAdapter;
import com.example.retrofitbueno.domain.JsonResponse;
import com.example.retrofitbueno.domain.PokemonList;
import com.example.retrofitbueno.iface.ApiPokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static int LIMIT = 100;
    public static int OFFSET = 200;

    final String TAG = getClass().getName();
    private List<PokemonList> mPokemonList;
    ListView mListView = null;
    PokemonAdapter mPokemonAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllPokemon();

        mListView = findViewById(R.id.lvPokemon);


    }

    public void getAllPokemon(){
        Retrofit retrofit = new Retrofit.Builder()
                //la url base desde la que trabaja retrofit "url base"
                .baseUrl(Constantes.BASE_URL)
                //la forma en la que parseamos esos datos
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                //decalaramos un objeto de la interfaz
        ApiPokemon apiPokemon = retrofit.create(ApiPokemon.class);

        apiPokemon.getListPokemon(LIMIT, OFFSET).enqueue(new Callback<JsonResponse>() {
            @Override
                    //Hacemos una peticion y nos van llegando los datos
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                    //Si no viene vacio y el Json (body)
                if (response != null && response.body() != null){
                    //Del cuerpo de la respuesta cogeme el que tenga la clave results (la clave del jsonResponse)
                    mPokemonList = response.body().results;
                        //Añade un pokemon  a la pokemon list con estos datos.
               /*     for (PokemonList p : mPokemonList) {
                        Log.d(TAG,p.getName());
                        Log.d(TAG,p.getUrl());
                    }*/
                    mPokemonAdapter = new PokemonAdapter(MainActivity.this, mPokemonList);
                    mListView.setAdapter(mPokemonAdapter);
                    mPokemonAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}