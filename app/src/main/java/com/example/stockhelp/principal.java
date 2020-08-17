package com.example.stockhelp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class principal extends AppCompatActivity {
    public boolean adicionar = false;
    public boolean remover = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void graficos(View vral){
        Intent graficotela = new Intent(this, graficos.class);
        startActivity(graficotela);
    }

    public void adicionar(View v){
        adicionar = true;
        final Activity atividade = this;
        IntentIntegrator integrator = new IntentIntegrator(atividade);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanner");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    public void remover(View view){
        remover = true;
        final Activity atividade = this;
        IntentIntegrator integrator = new IntentIntegrator(atividade);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanner");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(adicionar == true){
            if(result != null){
                if(result.getContents().equals("204312b43a4e3356b58cf490dd4bb95e")){
                    Intent adicionartela = new Intent(this, adicionaritem.class);
                    startActivity(adicionartela);
                    adicionar = false;
                }else{
                    Toast.makeText(this, R.string.mensagemQRcode, Toast.LENGTH_SHORT).show();
                }
            }else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }else if(remover == true){
            if(result != null){
                if(result.getContents().equals("204312b43a4e3356b58cf490dd4bb95e")){
                    Intent removertela = new Intent(this, removeritem.class);
                    startActivity(removertela);
                    remover = false;
                }else{
                    Toast.makeText(this, R.string.mensagemQRcode, Toast.LENGTH_SHORT).show();
                }
            }else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }


    }
}
