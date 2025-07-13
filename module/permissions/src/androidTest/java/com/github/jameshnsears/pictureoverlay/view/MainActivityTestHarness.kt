package com.github.jameshnsears.pictureoverlay.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.github.jameshnsears.pictureoverlay.common.MethodLineLoggingTree
import com.github.jameshnsears.pictureoverlay.permissions.BuildConfig
import timber.log.Timber

class MainActivityTestHarness : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG && Timber.treeCount == 0) {
            Timber.plant(MethodLineLoggingTree())
        }
    }
}
