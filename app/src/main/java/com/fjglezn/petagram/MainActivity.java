package com.fjglezn.petagram;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables para actualizar
    SwipeRefreshLayout sfiMiIndicadorRefresh;
    ListView lstMiLista;
    Adapter adaptador;//Funciona como auxiliar para manejar los datos del archivo strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregarFAB();//Se llama el metodo

        sfiMiIndicadorRefresh = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicadorRefresh);
        lstMiLista = (ListView) findViewById(R.id.lstMiLista);

        //Toda la lista que tenemos de planetas se la asignamos al arreglo
        String[] planetas = getResources().getStringArray(R.array.planetas);

        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));

        //Acción para cuando se deslice haciaabajo
        sfiMiIndicadorRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

             @Override
            public void onRefresh() {
                 refrescandoContenido();
             }
        });
    }

    //Método para refrescar el contenido
    public void refrescandoContenido() {

        //Aquí va lo que se va actualizar puede ir un webserver
        String[] planetas2 = getResources().getStringArray(R.array.planetas2);

        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas2));

        sfiMiIndicadorRefresh.setRefreshing(false);

    }

    //Metodo para el floating action button
    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabMiFAB);

        //Metodo para que escuche cuando se de un click
        miFAB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){   //Aqui va la accion que va ejecutar cuando se de click al boton
            //Toast ya no se utiliza en Material Design
         //   Toast.makeText(getBaseContext(), getResources().getString(R.string.texto_favorito), Toast.LENGTH_LONG).show();
            // ===================== ********** ==================================================
            //Los Snackbar son los que vienen a remplazar a los Toast para utilizarlos en Material Design
          //  Snackbar.make(v, getResources().getString(R.string.texto_favorito), Snackbar.LENGTH_LONG).show();

            // ==========================*************************==============================================
            // Para ejecutar una acción en un Snackbar
            Snackbar.make(v, getResources().getString(R.string.texto_favorito), Snackbar.LENGTH_LONG)
                    .setAction(getResources().getString(R.string.texto_accion), new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            Log.i("SNACKBAR", "Click en Snackbar");//Se muestra en consola
                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorPrimary))//Cambia el color de las letras accion
                    .show();

            }
        });

    }
}
