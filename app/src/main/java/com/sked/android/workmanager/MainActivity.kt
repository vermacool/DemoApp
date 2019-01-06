package com.sked.android.workmanager

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sked.android.workmanager.worker.LocationWorker

import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val MY_PERMISSIONS_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        var checkPermission = AttMgmtCheckPermission()

        fab.setOnClickListener { view ->
            if (checkPermission.isComponentEnabled(this) == true) {
                val periodicWorkTask = PeriodicWorkRequest.Builder(LocationWorker::class.java, 3, TimeUnit.MINUTES)
                var periodicWorkRequest = periodicWorkTask.build()
                WorkManager.getInstance()
                    .enqueueUniquePeriodicWork("jobTag", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)

            } else {
                ActivityCompat.requestPermissions(
                    this,
                    checkPermission.getPermissionList(),
                    MY_PERMISSIONS_REQUEST
                )
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
