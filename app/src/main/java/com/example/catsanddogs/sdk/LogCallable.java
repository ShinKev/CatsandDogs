package com.example.catsanddogs.sdk;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Callable;

class LogCallable implements Callable<String> {
    String message;
    Context context;

    LogCallable(String message, Context context) {
        this.message = message;
        this.context = context;
    }

    @Override
    public String call() throws IOException {
        // Write in Logcat
        Log.println(Log.DEBUG, "LogCallable", message);

        // Write to a file called messageToToast.txt
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("messageToToast.txt", Context.MODE_PRIVATE));
        outputStreamWriter.write(message);
        outputStreamWriter.close();

        return message;
    }
}