package com.miu.edu.sports.news.information.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.auth.SignInActivity
import com.miu.edu.sports.news.information.screen.MainActivity
import com.miu.edu.sports.news.information.utils.SharedPreferenceStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferenceStorage: SharedPreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (sharedPreferenceStorage.userEmail?.isNotEmpty() == true){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }
}