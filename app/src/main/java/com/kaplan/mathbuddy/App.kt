package com.kaplan.mathbuddy

import android.app.Application

class App : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
