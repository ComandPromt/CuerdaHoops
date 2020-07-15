package com.example.cuerdahoops.ui.gallery;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerdahoops.MainActivity;
import com.example.cuerdahoops.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private ListView lista;
    ArrayList<String> listaBd = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listaBd.add("1");
        listaBd.add("2");
        listaBd.add("3");
        lista = (ListView) view.findViewById(R.id.lista);
        adaptador = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, listaBd);
        lista.setAdapter(adaptador);

        MainActivity.lista = this.lista;

    }

}