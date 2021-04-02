package com.example.catsanddogs.sdk;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CsAnalytics {

    static String CAT = "cat";
    static String DOG = "dog";
    private int numberOfCats = 0;
    private int numberOfDogs = 0;
    private List<Pet> petList = new ArrayList<>();

    public CsAnalytics(@NonNull Context context) {
    }

    public void trigger(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Displays the pop-up (Toast) with the dog or cat count.
        //Don't forget the thread and debounce timer.

        Pet pet = petList.get(position);
        Log.println(Log.DEBUG, "CsAnalytics", "Position " + position + ". There are " + pet.numberOfPets + " " + pet.petType + "(s).");
    }

    public void track(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Registers all the pets from each grid.

        if (holder.itemView.getContentDescription() == CAT) {
            numberOfCats += 1;
            petList.add(new Pet(CAT, numberOfCats));
        }
        else {
            numberOfDogs += 1;
            petList.add(new Pet(DOG, numberOfDogs));
        }
    }

    public void clear() {
        //Clears the log file.
    }

}
