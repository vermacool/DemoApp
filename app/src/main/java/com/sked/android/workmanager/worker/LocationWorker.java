package com.sked.android.workmanager.worker;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.sked.android.workmanager.log.LoggerUtility;

public class LocationWorker extends Worker {
    Context context;
    private final String TAG = LocationWorker.class.getSimpleName();

    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Worker.Result doWork() {
        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        myCompress();

        // Indicate success or failure with your return value:
        return Result.success();
        // (Returning Result.retry() tells WorkManager to try this task again
        // later; Result.failure() says not to try again.)
    }

    private void myCompress() {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "work executed", Toast.LENGTH_LONG).show();
                Log.d("LocationWorker", "work executed");
                LoggerUtility.Print(TAG, "work executed");
            }
        });

    }
}
