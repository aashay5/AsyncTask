package com.example.asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView txtNumber;
    private Spinner spinner;

    private InnerAsyncTask innerAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);

//        ExampleAsyncTask exampleAsyncTask = new ExampleAsyncTask();
//        exampleAsyncTask.execute(10);

        innerAsyncTask = new InnerAsyncTask();
        innerAsyncTask.execute(10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != innerAsyncTask) {
            innerAsyncTask.cancel(true);
        }
    }

    private class InnerAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtNumber = (TextView) findViewById(R.id.txtNumber);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            for (int i=integers[0]; i>=0; i--) {
                SystemClock.sleep(1000);
                publishProgress(i);
            }

            return "Finished!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            txtNumber.setText(String.valueOf(values[0]));
            Log.d(TAG, "onProgressUpdate: progress: "+ values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            txtNumber.setText(s);
        }
    }
}