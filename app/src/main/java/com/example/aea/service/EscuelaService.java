package com.example.aea.service;

import com.example.aea.model.Escuela;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface EscuelaService {
    @GET("listar")
    Call<List<Escuela>> getEscuelas();

    @POST("agregar")
    Call<Escuela>addEscuela(@Body Escuela escuela);

    @POST("actualizar/{id}")
    Call<Escuela>updateEscuela(@Body Escuela escuela, @Path("id") int id);

    @POST("eliminar/{id}")
    Call<Escuela>deleteEscuela(@Path("id") int id);
}
