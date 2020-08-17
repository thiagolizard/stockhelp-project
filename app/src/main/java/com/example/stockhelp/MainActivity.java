package com.example.stockhelp;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText loginLogin, senhaLogin;
    private Button botaoLogar;
    private ProgressBar loadingLogin;
    private static String URL_LOGIN = "http://54.209.43.179/data/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingLogin = findViewById(R.id.loadingLogin);
        loginLogin = findViewById(R.id.txtUsuario);
        senhaLogin = findViewById(R.id.txtSenha);
        botaoLogar = findViewById(R.id.btnEntrar);
    }

    public void entrar(View view){
        String sUsuario = loginLogin.getText().toString();
        String sSenha = senhaLogin.getText().toString();

        if(!sUsuario.isEmpty() || !sSenha.isEmpty()){
            validar(sUsuario, sSenha);
        }else{
            loginLogin.setError("Usuário inválido!");
            senhaLogin.setError("Senha inválida!");
        }

        validar(sUsuario, sSenha);
    }

    public void validar(final String usuario, final String senha){
        loadingLogin.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for (int i = 0; i< jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String login = object.getString("login").trim();
                                    String senha = object.getString("senha").trim();

                                    Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Erro" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Erro" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("login", usuario);
                params.put("senha", senha);
                return params ;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void criar(View view){
        Intent registrar = new Intent(this, registro.class);
        startActivity(registrar);
    }



}
