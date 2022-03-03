package es.usj.mastertsa.onunez.visitrd.presentation.view.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.usj.mastertsa.onunez.visitrd.R
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    companion object { private val SPLASH_SCREEN_DELAY: Long = 4000 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContentView(R.layout.activity_splash_screen)

//        if(savedInstanceState == null) {
            val task = object : TimerTask() {
                override fun run() {
                    val intent = Intent(
                        this@SplashScreenActivity,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }
            }

            val timer = Timer()
            timer.schedule(task, SPLASH_SCREEN_DELAY)

//            supportFragmentManager.beginTransaction().add(LoginFragment.newInstance())
//        }
    }
}