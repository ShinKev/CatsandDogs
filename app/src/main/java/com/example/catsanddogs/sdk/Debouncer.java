package com.example.catsanddogs.sdk;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

class Debouncer<T> implements Runnable {
    long debounceTimeInMillisec;
    long timeLeftToWait;
    Callable<T> callable;

    Debouncer(Callable<T> callable) {
        this.callable = callable;
    }

    public void run() {
        try {
            sleep(timeLeftToWait);
            callable.call();
            resetTimeToWaitUntilCall();
        } catch (InterruptedException e) {
            Log.println(Log.DEBUG, "Debouncer", "Previous call was cancelled. A new call is ongoing now...");
        } catch (IOException e) {
            Log.e("IOException", "File write failed: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetTimeToWaitUntilCall() {
        timeLeftToWait = debounceTimeInMillisec;
    }

    public void setCallable(Callable<T> callable) {
        this.callable = callable;
    }

    public void setTimeLeftToWait(long timeLeftToWait) {
        this.timeLeftToWait = timeLeftToWait;
    }
}
