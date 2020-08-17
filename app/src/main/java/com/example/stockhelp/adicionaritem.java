package com.example.stockhelp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class adicionaritem extends AppCompatActivity {
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionaritem);
        Spinner spinner = (Spinner) findViewById(R.id.spnTipoJoia);
        Spinner spinner2 = (Spinner) findViewById(R.id.spnMarcaJoia);
        Spinner spinner3 = (Spinner) findViewById(R.id.spnTipoItem);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.array_joias, R.layout.spinnerstyle);
        adapter.setDropDownViewResource(R.layout.spinnerstyleitem);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.array_item, R.layout.spinnerstyle);
        adapter2.setDropDownViewResource(R.layout.spinnerstyleitem);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.array_marca, R.layout.spinnerstyle);
        adapter3.setDropDownViewResource(R.layout.spinnerstyleitem);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter3);
        spinner3.setAdapter(adapter2);
    }

    public void enviar(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enviando...");
        builder.setMessage("Dados enviados com sucesso!");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setIcon(R.drawable.sendimage);
        alerta = builder.create();
        alerta.show();
    }
}
