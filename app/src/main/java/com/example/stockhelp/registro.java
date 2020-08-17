package com.example.stockhelp;

import android.content.DialogInterface;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {

    private EditText usuario, senha, email;
    private Button btn_registrar;
    private ProgressBar loading;
    private AlertDialog alerta;
    private static String URL_REGIST = "http://54.209.43.179/data/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.txtEmailRegistrar);
        usuario = findViewById(R.id.txtUsuarioRegistrar);
        senha = findViewById(R.id.txtSenhaRegistrar);
        btn_registrar = findViewById(R.id.btnEnviarRegistro);

        loading.setVisibility(View.GONE);
    }

    public void clicouRegistro(View v){
        enviarRegistro();
    }

    private void enviarRegistro(){
        loading.setVisibility(View.VISIBLE);
        btn_registrar.setVisibility(View.GONE);

        final String usuario = this.usuario.getText().toString().trim();
        final String senha = this.senha.getText().toString().trim();
        final String email = this.email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                alertaRegistro();
                                loading.setVisibility(View.GONE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registro.this, R.string.mensagemErro + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_registrar.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registro.this, R.string.mensagemErro + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_registrar.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("login", usuario);
                params.put("senha", senha);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void alertaRegistro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastrar");
        builder.setMessage("Cadastro feito com sucesso!");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setIcon(R.drawable.sendimage);
        alerta = builder.create();
        alerta.show();
    }



}
