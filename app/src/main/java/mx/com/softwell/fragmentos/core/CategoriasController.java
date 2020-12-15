package mx.com.softwell.fragmentos.core;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import mx.com.softwell.fragmentos.api.API;
import mx.com.softwell.fragmentos.api.apiservices.CategoriasService;
import mx.com.softwell.fragmentos.gui.Categorias2;
import mx.com.softwell.fragmentos.gui.MainActivity;
import mx.com.softwell.fragmentos.model.Categorias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriasController {
    private static CategoriasController instance = null;
    private static MiscController miscController = MiscController.Instance();
    private boolean status = false;
    private String message = "";
    private String data = "";
    private List<Categorias> categorias;
    Type categoriasType = new TypeToken<List<Categorias>>(){}.getType();
    private static String TAG = "CategoriasController";

    private CategoriasController(){}

    public static CategoriasController Instance(){
        if (instance == null)
            instance = new CategoriasController();
        return instance;
    }

    public void getAll(){
        API.getApi().create(CategoriasService.class).getAll().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jobj = new JSONObject(response.body().toString());
                    status = jobj.getBoolean("status");
                    message = jobj.getString("message");
                    if (status){
                        //data = jsonObject.getJSONObject("data").toString();
                        data = jobj.getJSONArray("data").toString();
                        categorias = new Gson().fromJson(data, categoriasType);
                        miscController.CloseWait();
                        Log.e(TAG, data);
                        Log.e(TAG, categorias.toString());
                        //mandar informacion al recycler
                        ((Categorias2) MainActivity.GLOBALS.get("categorias2Fragment")).actualizar(categorias);
                }else {
                        miscController.CloseWait();
                        Log.e(TAG, message);
                    }
                    } catch(JSONException e) {
                    e.printStackTrace();
                    }
                }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e(TAG, t.getMessage());
            }
        });
    }
}

