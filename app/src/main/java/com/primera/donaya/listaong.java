package com.primera.donaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class listaong extends AppCompatActivity {

    ListView lista;

    String[][] datos= {
            {"Caritas de El Salvador","Brinda apoyo legal, hospedaje y alimentación, apoyo en salud, educación y charlas, de realizar estadísticas sobre migrantes y apoyo a familias."},
            {"Cruz Roja", "Somos una institución humanitaria que prevenimos y aliviamos el sufrimiento humano; a través del servicio voluntario, respetando la dignidad de las personas y con absoluto apego a los Principios Fundamentales del Movimiento de la Cruz Roja y Media Luna Roja."},
            {"Cruz Verde", "Cruz Verde se dedica a la prevencion, respuesta intergral a emergencias cotidianas, incidentes,eventos y desastres."},
            {"ISNA", "Entidad publica perteneciente al Sistema Nacional de Proteccion Integral de Niñez y de la Adolecencia,comprometida con la supervision de programas de atencion a niñas,niños y adolecentes."},
            {"Hogar del Anciano San Vicente Paúl", "Es una institución comprometida con la atención y cuidado de los adultos mayores, conformada por un equipo de profesionales y voluntarios preparados para brindar a sus residentes, la atención digna y el cariño que todo ser humano anhela."},
            {"Teleton","Somos una institución sin fines de lucro, dedicada a la rehabilitación integral de niños, niñas y jóvenes con discapacidad motora, en el mejoramiento de su calidad de vida y al desarrollo de sus capacidades para lograr su inclusión social."},
    };

    int[] datosImg = {R.drawable.carita, R.drawable.cruzroja, R.drawable.cruzverde, R.drawable.insa, R.drawable.hogarancia, R.drawable.teleton};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lvLista);

        lista.setAdapter(new Adaptador(this, datos, datosImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDetalles = new Intent(view.getContext(), Detallesinstitucion.class);
                visorDetalles.putExtra("TIT", datos[position][1]);
                visorDetalles.putExtra("DET", datos[position][2]);
                startActivity(visorDetalles);

            }
        });
    }
}