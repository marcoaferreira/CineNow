package com.devspacecinenow

import android.app.Application

class CineNowApplication: Application() {

    val repository by lazy{
        CineNowServiceLocator.getRepository(this)
    }
}