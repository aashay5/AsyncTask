package com.example.asynctask;


import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class ExampleAsyncTask extends AsyncTask<Integer, Void, Void> {
    private static final String TAG = "ExampleAsyncTask";
    @Override
    protected Void doInBackground(Integer... integers) {
        for (int i=integers[0]; i>0; i--){
            Log.d(TAG, "doInBackground: i: " + i);
            SystemClock.sleep(1000);
        }
        return null;
    }
}