package com.example.mobileundcloudpraktikum

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val RC_SIGN_IN: Int = 9001
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val TAG = "MyFirebaseMsgService"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<SignInButton>(R.id.sign_in_button).setOnClickListener(this)
        findViewById<Button>(R.id.logout).setOnClickListener(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Execute order 66", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    fun updateUI(account: GoogleSignInAccount?) {
        when (account) {
            // wenn kein account verbunden ist (Kein nutzer eingeloggt)
            null -> {
                findViewById<AppBarLayout>(R.id.appbar).visibility = View.INVISIBLE
                findViewById<TextView>(R.id.tvHeader).text = "Willkommen bei unserer App!"

                findViewById<TextView>(R.id.email).visibility = View.INVISIBLE
                findViewById<TextView>(R.id.accountid).visibility = View.INVISIBLE

                findViewById<TextView>(R.id.secondText).text = "Bitte loggen Sie sich mit ihrem google konto ein."
                findViewById<Button>(R.id.logout).visibility = View.INVISIBLE
                findViewById<TextView>(R.id.secondText).visibility = View.VISIBLE
                findViewById<SignInButton>(R.id.sign_in_button).visibility = View.VISIBLE
            }
            // wenn ein account verbunden ist (Nutzer ist eingeloggt)
            else -> {
                findViewById<AppBarLayout>(R.id.appbar).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tvHeader).text = "Willkommen zurück " + account.displayName

                findViewById<TextView>(R.id.email).text = "Email: " + account.email
                findViewById<TextView>(R.id.accountid).text = "Account-ID: " + account.id
                findViewById<TextView>(R.id.email).visibility = View.VISIBLE
                findViewById<TextView>(R.id.accountid).visibility = View.VISIBLE

                findViewById<Button>(R.id.logout).visibility = View.VISIBLE
                findViewById<TextView>(R.id.secondText).visibility = View.INVISIBLE
                findViewById<SignInButton>(R.id.sign_in_button).visibility = View.INVISIBLE

                val random = Random()
                val fm = FirebaseMessaging.getInstance()
                val projectID = "1047518041749"
                Log.d(TAG, "Try To send Message at Server: $projectID")
                fm.send(RemoteMessage.Builder("$projectID@gcm.googleapis.com")
                    .setMessageId("" + random.nextInt())
                    .addData("vorname", "" + account.givenName)
                    .addData("nachname", "" + account.familyName)
                    .addData("email", "" + account.email)
                    .addData("googleID", "" + account.id)
                    .addData("clientToken", "" + account.idToken)
                    .addData("action", "REGISTER")
                    .build())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.activity1 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.activity2 -> {
                val intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
                true
            }
            R.id.activity3 -> {
                val intent = Intent(this, Main3Activity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.sign_in_button -> {
                val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
            R.id.logout -> {
                mGoogleSignInClient.signOut()
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account!!)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("MainAktivity", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }
}
