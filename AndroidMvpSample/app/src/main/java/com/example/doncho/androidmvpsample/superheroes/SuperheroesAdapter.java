package com.example.doncho.androidmvpsample.superheroes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.doncho.androidmvpsample.R;
import com.example.doncho.androidmvpsample.data.models.Superhero;

/**
 * Created by doncho on 9/12/17.
 */

public class SuperheroesAdapter extends ArrayAdapter<Superhero> {
    private final OnSuperheroesItemClickListener mListener;

    public SuperheroesAdapter(@NonNull Context context, OnSuperheroesItemClickListener listener) {
        super(context, -1);
        mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.superhero_item, parent, false);
        }

        final Superhero superhero = getItem(position);

        String name = superhero.getName();

        ((TextView)view.findViewById(R.id.tv_name)).setText(name);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(superhero);
            }
        });

        return view;
    }

    interface OnSuperheroesItemClickListener {

        void onClick(Superhero superhero);
    }
}
