package com.example.Ajiri;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class View_Adapter extends FirestoreRecyclerAdapter<View, View_Adapter.NoteHolder> {


    public View_Adapter(@NonNull FirestoreRecyclerOptions<View> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull View model) {

        holder.title.setText(model.getTitle());
        holder.prio.setText(String.valueOf(model.getPri()));
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        android.view.View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);


        return new NoteHolder(view);
    }

    public class NoteHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView prio;

        public NoteHolder(@NonNull android.view.View itemView) {
            super(itemView);

            title =(TextView) itemView.findViewById(R.id.text_Title);
            prio =(TextView) itemView.findViewById(R.id.text_P);

        }
    }
}
