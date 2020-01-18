package com.example.afifa123.rationapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.ResponseType
import com.auth0.android.provider.WebAuthProvider


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton:Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            login()
        }

    }
    fun login() {
        val auth0: Auth0 =  Auth0("com_auth0_client_id", "com_auth0_domain")
        auth0.isOIDCConformant = true


        WebAuthProvider.init(auth0)
            .withScheme("demo")
            .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
            .withResponseType(ResponseType.CODE)
            .start(this@LoginActivity,  authCallback)

                    }
    //Declare the callback that will receive the result
    val authCallback: AuthCallback = object : AuthCallback {
        override fun onSuccess(credentials: com.auth0.android.result.Credentials) {
            runOnUiThread {
                Toast.makeText(this@LoginActivity, "Log In - Success", Toast.LENGTH_SHORT)
                    .show()
            }
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()

        }

        override fun onFailure(dialog: Dialog) { //failed with a dialog
            runOnUiThread { dialog.show() }
        }

        override fun onFailure(exception: AuthenticationException?) { //failed with an exception
            runOnUiThread {
                Toast.makeText(
                    this@LoginActivity,
                    "Error: " + exception!!.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }



}
