package com.example.aea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aea.model.Escuela;
import com.example.aea.service.EscuelaService;
import com.example.aea.service.apis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscuelaActivity extends AppCompatActivity {

    EscuelaService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela);
      EditText   ides = (EditText) findViewById(R.id.txtIdescuela);
       TextView escuela = (TextView) findViewById(R.id.IdEscuela);
    final EditText mombre = (EditText) findViewById(R.id.txtNombres);
     TextView nombres = (TextView) findViewById(R.id.nombres);



        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOMBRE");

        ides.setText(id);
        mombre.setText(nom);

        if(id.trim().length()==0||id.equals("")){
            escuela.setVisibility(View.INVISIBLE);
            ides.setVisibility(View.INVISIBLE);

    }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Escuela p=new Escuela();
                p.setNombre(mombre.getText().toString());
                if(id.trim().length()==0||id.equals("")){
                    addEscuela(p);
                    Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updateEscuela(p,Integer.valueOf(id));
                    Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEscuela(Integer.valueOf(id));
                Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


}
    public void addEscuela(Escuela p){
        service= apis.getEscuelaService();
        Call<Escuela> call=service.addEscuela(p);
        call.enqueue(new Callback<Escuela>() {
            @Override
            public void onResponse(Call<Escuela> call, Response<Escuela> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EscuelaActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Escuela> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }



        });
        Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updateEscuela(Escuela p,int id){
        service= apis.getEscuelaService();
        Call<Escuela>call=service.updateEscuela(p,id);
        call.enqueue(new Callback<Escuela>() {
            @Override
            public void onResponse(Call<Escuela> call, Response<Escuela> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EscuelaActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Escuela> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void deleteEscuela(int id){
        service=apis.getEscuelaService();
        Call<Escuela>call=service.deleteEscuela(id);
        call.enqueue(new Callback<Escuela>() {
            @Override
            public void onResponse(Call<Escuela> call, Response<Escuela> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EscuelaActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Escuela> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(EscuelaActivity.this,MainActivity.class);
        startActivity(intent);
    }
}