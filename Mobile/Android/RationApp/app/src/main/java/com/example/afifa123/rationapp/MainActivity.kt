package com.example.afifa123.rationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auth0.android.Auth0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var  auth0:Auth0 =  Auth0(this)
        auth0.isOIDCConformant = true
    }
}
