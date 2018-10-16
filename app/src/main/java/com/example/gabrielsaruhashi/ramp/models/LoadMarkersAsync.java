package com.example.gabrielsaruhashi.ramp.models;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by Masayuki on 10/5/18.
 */

//public class LoadMarkersAsync extends AsyncTask<String> {
//    protected void onPreExecute() {
//        // Runs on the UI thread before doInBackground
//        // Good for toggling visibility of a progress indicator
//        //progressBar.setVisibility(ProgressBar.VISIBLE);
//    }
//
//    protected Bitmap doInBackground(String... strings) {
//        // Some long-running task like downloading an image.
//        Bitmap = downloadImageFromUrl(strings[0]);
//        return someBitmap;
//    }
//
//    protected void onProgressUpdate(Progress... values) {
//        // Executes whenever publishProgress is called from doInBackground
//        // Used to update the progress indicator
//        progressBar.setProgress(values[0]);
//    }
//
//    protected void onPostExecute(Bitmap result) {
//        // This method is executed in the UIThread
//        // with access to the result of the long running task
//        imageView.setImageBitmap(result);
//        // Hide the progress bar
//        progressBar.setVisibility(ProgressBar.INVISIBLE);
//    }
//}
