package com.example.movieup.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieup.R
import com.example.movieup.ui.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, HomeActivity::class.java)


        /*
        Имитация загрузочного экрана
        Так загрузку не надо делать, но в моем случае нечего загужать.
        */
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            this.launch(Dispatchers.Main) {
                startActivity(intent)
                finish()
            }
        }
    }
}