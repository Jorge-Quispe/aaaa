package com.example.aea.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aea.EscuelaActivity;
import com.example.aea.R;
import com.example.aea.model.Escuela;

import java.util.List;

public class EscuelaAdapter  extends ArrayAdapter<Escuela> { private Context context;
    private List<Escuela> escuelas;

    public EscuelaAdapter(@NonNull Context context, int resource, @NonNull List<Escuela> objects) {
        super(context, resource, objects);
        this.context=context;
        this.escuelas=objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);
        TextView txtidPersona=(TextView)rowView.findViewById(R.id.Idescuela);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.nombres);;

        txtidPersona.setText(String.format("ID:%d",escuelas.get(position).getIdescuela()));
        txtNombre.setText(String.format("NOMBRE:%s",escuelas.get(position).getNombre()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EscuelaActivity.class);
                intent.putExtra("ID",String.valueOf(escuelas.get(position).getIdescuela()));
                intent.putExtra("NOMBRE",escuelas.get(position).getNombre());

                context.startActivity(intent);
            }
        });
        return rowView;

    }

}
