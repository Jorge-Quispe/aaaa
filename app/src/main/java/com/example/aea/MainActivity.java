package com.example.aea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.aea.adapter.EscuelaAdapter;
import com.example.aea.model.Escuela;
import com.example.aea.service.EscuelaService;
import com.example.aea.service.apis;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EscuelaService escuelaService;
    List<Escuela> listEscuela=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.listView);

        listEscuela();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EscuelaActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");
                startActivity(intent);
            }
        });

    }
    public void listEscuela(){
        escuelaService= apis.getEscuelaService();
        Call<List<Escuela>> call=escuelaService.getEscuelas();
        call.enqueue(new Callback<List<Escuela>>() {
            @Override
            public void onResponse(Call<List<Escuela>> call, Response<List<Escuela>> response) {
                if(response.isSuccessful()) {
                    listEscuela = response.body();
                    listView.setAdapter(new EscuelaAdapter(MainActivity.this,R.layout.content_main,listEscuela));
                }
            }

            @Override
            public void onFailure(Call<List<Escuela>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}