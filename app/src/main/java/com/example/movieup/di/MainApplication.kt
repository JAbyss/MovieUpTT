package com.example.movieup

import android.app.Application
import com.example.movieup.network.RetrofitModule
import com.example.movieup.network.api.FilmApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface ApplicationGraph{

    fun film(): FilmApi

    fun inject(app: MyApp)
}

class MyApp(): Application() {
    companion object {
        lateinit var instance: MyApp
            private set
    }

    lateinit var appComponent: ApplicationGraph
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerApplicationGraph.builder()
            .build()
        appComponent.inject(this)
    }
}