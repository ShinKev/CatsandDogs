package com.example.catsanddogs.sdk;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CsAnalytics {

    static final String CAT = "cat";
    static final String DOG = "dog";
    private int numberOfCats = 0;
    private int numberOfDogs = 0;
    List<Pet> petList = new ArrayList<>();

    private long lastTriggerTimeMillis;
    static final long totalTimeToWaitInMillisec = 2000;
    private final Debouncer<String> debouncer = new Debouncer<>(null);
    private Thread thread;

    private Context context;

    public CsAnalytics(@NonNull Context context) {
        this.context = context;
    }

    public void trigger(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Displays the pop-up (Toast) with the dog or cat count.
        //Don't forget the thread and debounce timer.

        Pet pet = petList.get(position);
        String messageToPrint = "Position " + position + ". There are " + pet.numberOfPets + " " + pet.petType + "(s).";
        Callable<String> callable = new LogCallable(messageToPrint, context);

        if (thread == null || !thread.isAlive()) {
            lastTriggerTimeMillis = System.currentTimeMillis();
            debouncer.setTimeLeftToWait(totalTimeToWaitInMillisec);
        } else {
            thread.interrupt();
            long diffTime = System.currentTimeMillis() - lastTriggerTimeMillis;
            lastTriggerTimeMillis = System.currentTimeMillis();
            debouncer.setTimeLeftToWait(debouncer.timeLeftToWait - diffTime);
        }

        debouncer.setCallable(callable);
        thread = new Thread(debouncer);
        thread.start();
    }

    public void track(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Registers all the pets from each grid.

        if (holder.itemView.getContentDescription() == CAT) {
            numberOfCats += 1;
            petList.add(new Pet(CAT, numberOfCats));
        } else {
            numberOfDogs += 1;
            petList.add(new Pet(DOG, numberOfDogs));
        }
    }

    public void clear() {
        //Clears the log file.
    }

}