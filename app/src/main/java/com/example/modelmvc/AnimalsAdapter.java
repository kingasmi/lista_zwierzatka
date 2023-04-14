package com.example.modelmvc;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {
    private List<Animal> animals;

    public AnimalsAdapter(List<Animal> animals) {
        this.animals = animals;
    }


    public class AnimalViewHolder extends RecyclerView.ViewHolder {

        public TextView animalName;
        public ImageView animalImage;
        public Button animalDeleteButton;

        @RequiresApi(api = Build.VERSION_CODES.P)
        public AnimalViewHolder(View v) {
            super(v);
            Log.d("Info","PackageResourcePath()="+ v.getParent());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @NonNull
    @Override
    public AnimalsAdapter.AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_recycle_animal,parent,false);
        AnimalViewHolder viewHolder = new AnimalViewHolder(v);
        viewHolder.animalName = v.findViewById(R.id.animal_name);
        viewHolder.animalImage = v.findViewById(R.id.animal_image);
        viewHolder.animalDeleteButton = v.findViewById(R.id.delete_animal_button);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position){
        Animal animal = animals.get(position);

        holder.animalName.setText(animal.name);
        holder.animalImage.setImageResource(animal.image);

        int color = Color.GREEN;
        if(position%2==0){
            color=Color.CYAN;
        }
        holder.animalName.setBackgroundColor(color);

        holder.animalDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Toast.makeText(view.getContext(), "Usunełem -"+animals.get(position).name, Toast.LENGTH_SHORT).show();
                animals.remove(position);
                notifyItemRemoved(position);

            }
        });

        holder.animalName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                Toast.makeText(v.getContext(), "onLongClick -"+animals.get(position).name+" |", Toast.LENGTH_SHORT).show();
                Log.d("Info", "setLongClickListener"+v.getPivotX());
                return true;
            }
        });

        holder.animalName.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                Log.d("Info","onGenericMotion"+event.getSource());
                return true;
            }
        });

        holder.animalImage.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int position = holder.getAdapterPosition();
                Toast.makeText(v.getContext(), "onTouch-"+animals.get(position).name, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
