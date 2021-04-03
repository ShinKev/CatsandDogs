package com.example.catsanddogs.sdk;

import android.content.Context;
import android.widget.Toast;

class ToastMessageRunnable implements Runnable {
    String message;
    Context context;

    ToastMessageRunnable(String message, Context context) {
        this.message = message;
        this.context = context;
    }

    @Override
    public void run() {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
