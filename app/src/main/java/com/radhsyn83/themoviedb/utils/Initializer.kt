package com.radhsyn83.themoviedb.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class Initializer : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}