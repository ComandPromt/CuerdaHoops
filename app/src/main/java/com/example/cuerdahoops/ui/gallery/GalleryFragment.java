package com.example.cuerdahoops.ui.gallery;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerdahoops.MainActivity;
import com.example.cuerdahoops.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class GalleryFragment extends Fragment {
    private ListView lista;
    public static LinkedList<String> listaBd = new LinkedList<String>();
    ArrayAdapter<String> adaptador;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        return view;
    }

    public LinkedList<String> leerDatoArhivo() {

        LinkedList<String>lista=new LinkedList<String>();

        String archivos [] = view.getContext().fileList();

        if(MainActivity.archivoExiste(archivos, "cuerdahoops.txt")){

            try {

                InputStreamReader archivo = new InputStreamReader(view.getContext().openFileInput("cuerdahoops.txt"));

                BufferedReader br = new BufferedReader(archivo);

                String linea = br.readLine();

                String bitacoraCompleta = "";


                while(linea != null){

                    bitacoraCompleta = linea + "\n";

                    linea = br.readLine();

                    if(!lista.contains(bitacoraCompleta)){
                        lista.add(bitacoraCompleta);
                    }

                }

                br.close();

                archivo.close();

            }catch (IOException e){

            }

        }

        return lista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        String archivos [] = view.getContext().fileList();

        if(MainActivity.archivoExiste(archivos, "cuerdahoops.txt")){

            listaBd=leerDatoArhivo();

          /*  try {

                InputStreamReader archivo = new InputStreamReader(view.getContext().openFileInput("cuerdahoops.txt"));

                BufferedReader br = new BufferedReader(archivo);

                String linea = br.readLine();

                String bitacoraCompleta = "";

                listaBd.clear();

                while(linea != null){

                    bitacoraCompleta =linea + "\n";

                    linea = br.readLine();

                    if(!listaBd.contains(bitacoraCompleta)){
                        listaBd.add(bitacoraCompleta);
                    }



                }

                br.close();

                archivo.close();

            }catch (IOException e){

            }*/

        }

        lista = (ListView) view.findViewById(R.id.lista);

        adaptador = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, listaBd);
        lista.setAdapter(adaptador);
        registerForContextMenu(lista);
        MainActivity.seleccionABorrar.clear();
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        lista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                if(!MainActivity.seleccionABorrar.contains(lista.getItemAtPosition(i).toString())){
                    MainActivity.seleccionABorrar.add(lista.getItemAtPosition(i).toString());
                }

                int checkedCount = lista.getCheckedItemCount();

                actionMode.setTitle(checkedCount + " selected");

                MainActivity.selectedItem=lista.getItemAtPosition(i).toString();

                MainActivity.listaABorrar=listaBd;

                MainActivity.view=view;

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MainActivity.seleccionABorrar.clear();
                actionMode.getMenuInflater().inflate(R.menu.main_drawler, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }

        });

    }

    }
