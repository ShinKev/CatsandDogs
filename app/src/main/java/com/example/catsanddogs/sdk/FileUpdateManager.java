package com.example.catsanddogs.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class FileUpdateManager {
    String fileName;
    Context context;

    FileUpdateManager(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    public void notifyUpdate() throws IOException {
        String message = getMessageFromFile();

        // Give the Toast task to the MainLooper (because Toast is done on the UI thread)
        Handler h = new Handler(Looper.getMainLooper());
        h.post(new ToastMessageRunnable(message, context));
    }

    private String getMessageFromFile() throws IOException {
        String message = "";
        InputStream inputStream = context.openFileInput(fileName);

        // Read the file and retrieve it in the "message" string
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine();
            inputStream.close();
        }

        return message;
    }
}
