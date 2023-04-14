package com.example.modelmvc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimalsAdapter mAdapter;
    private final boolean wybor=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewAnimal);
        Animal[] possibleAnimals = {
                new Animal("Pies",R.drawable.dog_image),
                new Animal("Kot",R.drawable.cat_image),
                new Animal("Słoń",R.drawable.slon),
                new Animal("Leniwiec",R.drawable.leniwiec),
                new Animal("Lemur",R.drawable.lemur),
                new Animal("Lama",R.drawable.lama),
                new Animal("Hiena",R.drawable.hiena),
                new Animal("Borsuk",R.drawable.borsuk),
                new Animal("Koń",R.drawable.kon)};
        List<Animal> animals = new ArrayList<>(100);
        Random random = new Random();
        for (int i=0;i<100; i++){
            Animal randolAnimal = possibleAnimals[random.nextInt(possibleAnimals.length)];
            Animal animal = new Animal(randolAnimal.name + " " + (i + 1), randolAnimal.image);
            animals.add(animal);
        }

        if (wybor){
            GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            mAdapter = new AnimalsAdapter(animals);
            recyclerView.setAdapter(mAdapter);
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setAdapter(new AnimalsAdapter(animals));
    }
}