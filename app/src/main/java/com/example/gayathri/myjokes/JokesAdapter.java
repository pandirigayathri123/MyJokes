package com.example.gayathri.myjokes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.ViewHolder>{

    Context context;
    ArrayList<JokesModel> jokesModels;

    public JokesAdapter(JokeActivity jokeActivity, ArrayList<JokesModel> jokesModels) {
     this.context=jokeActivity;
     this.jokesModels=jokesModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.design,viewGroup,false);
        return new ViewHolder(v);
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull JokesAdapter.ViewHolder viewHolder, int i) {
    viewHolder.textView.setText(jokesModels.get(i).getJoke());

    }

    @Override
    public int getItemCount() {
        return jokesModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv);

        }
    }
}
