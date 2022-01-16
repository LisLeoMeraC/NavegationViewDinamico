package com.example.navegationviewlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.navegationviewlogin.activityNavigation;
import com.example.navegationviewlogin.modelo.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private Button ingreso;
    private EditText usuario, clave;
    private RequestQueue requestQue;
    private String url = "https://my-json-server.typicode.com/LisLeoMeraC/Servidor/Users";
    private int pos;
    List<User> listUsuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingreso = (Button) findViewById(R.id.btningresar);
        usuario = (EditText) findViewById(R.id.txtNombre);
        clave = (EditText) findViewById(R.id.txtClave);

        obtenerUsuarios();
    }

    public void iniciarSesion(View view) {
        if (validarUsuario(listUsuario)){
            User unUsuario=listUsuario.get(pos);
            Intent it=new Intent(this, activityNavigation.class);
            it.putExtra("usuario",unUsuario);
            startActivity(it);

        }else{
            Toast.makeText(this,"Ingreso incorrecto. ",Toast.LENGTH_LONG).show();
        }

    }


    private void obtenerUsuarios() {

        JsonArrayRequest requestJson = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        cargarUsuarios(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al conectarse:" + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        );
        requestQue = Volley.newRequestQueue(this);
        requestQue.add(requestJson);
    }


    private List<User> cargarUsuarios(JSONArray jArray) {

        for (int i=0;i<jArray.length();i++){
            try {
                JSONObject objetoJson=new JSONObject(jArray.get(i).toString());

                listUsuario.add(new User(objetoJson.getString("user"),
                        objetoJson.getString("name"),
                        objetoJson.getString("password"),
                        objetoJson.getString("image"),
                        objetoJson.getJSONArray("options"),
                        objetoJson.getString("rol"))
                );


            }
            catch (JSONException e){
                Toast.makeText(this,"Error al cargar los datos al objeto: "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        return listUsuario;
    }


    private boolean validarUsuario(List<User> listausuario){
        String auxNombreUsuario=usuario.getText().toString();
        String auxContrasena=clave.getText().toString();
        pos=0;

        try {
            for(User usu : listausuario) {
                if ((auxNombreUsuario.equals(usu.getNombre().toString()))&&(auxContrasena.equals(usu.getClave().toString()))) {
                    return true;
                }
                pos++;
            }

        }
        catch (Exception e){
            Toast.makeText(this,"Error al validar el usuario: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return  false;
    }
}