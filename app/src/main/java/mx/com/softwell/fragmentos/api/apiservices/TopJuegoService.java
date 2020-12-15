package mx.com.softwell.fragmentos.api.apiservices;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TopJuegoService {
    @GET("juegos/top")
    Call<JsonObject> getAll();

    @GET("juegos/top/{id}")
    Call<JsonObject> getByID(@Path("id") int id);
}
