package com.example.catsanddogs.sdk;

import android.util.Log;

import java.util.concurrent.Callable;

class LogCallable implements Callable<String> {
    String message;

    LogCallable(String message) {
        this.message = message;
    }

    @Override
    public String call() {
        Log.println(Log.DEBUG, "LogCallable", message);
        return message;
    }
}