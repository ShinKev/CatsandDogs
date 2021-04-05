package com.example.catsanddogs.sdk;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Callable;

class LogCallable implements Callable<String> {
    static final String fileName = "messageToToast.txt";
    String message;
    Context context;
    FileUpdateManager fileUpdateManager;

    LogCallable(String message, Context context, FileUpdateManager fileUpdateManager) {
        this.message = message;
        this.context = context;
        this.fileUpdateManager = fileUpdateManager;
    }

    @Override
    public String call() throws IOException {
        // Write in Logcat
        Log.d("LogCallable", message);

        // Write to a file
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
        outputStreamWriter.write(message);
        outputStreamWriter.close();

        // Notify the FileUpdateManager instance
        fileUpdateManager.notifyUpdate();

        return message;
    }
}