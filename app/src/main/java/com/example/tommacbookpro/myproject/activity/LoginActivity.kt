package com.example.tommacbookpro.myproject.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tommacbookpro.myproject.utility.BaseActivity
import com.example.tommacbookpro.myproject.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), GoogleApiClient.OnConnectionFailedListener {

    private val TAG: String = "Login Activity"
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    var mGoogleApiClient: GoogleApiClient? = null
    var mAuth: FirebaseAuth? = null
    val GOOGLE_LOG_IN_RC = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initgmaillogin()

        button.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HomeMenuActivity::class.java))
        }
    }

    private fun initlogin() {
        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@LoginActivity, HomeMenuActivity::class.java))
            finish()
        }


    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        Log.e("currentuser", "currentuser" + user!!.email)

    }

    private fun initgmaillogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.request_client_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this@LoginActivity)
                .enableAutoManage(this@LoginActivity) { }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        mAuth = FirebaseAuth.getInstance()
        btn_sign_in.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        Log.e(TAG, "Starting Google LogIn Flow.")
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, GOOGLE_LOG_IN_RC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "Got Result code ${requestCode}.")
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_LOG_IN_RC) {

            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.e(TAG, "QWERTY" + "\t" + result.status)
            Log.i(TAG, "With Google LogIn, is result a success? ${result.isSuccess}.")
            if (result.isSuccess) {
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
                Toast.makeText(this, "Some error occurred.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.e(TAG, "firebaseAuthWithGoogle():" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val user = mAuth!!.currentUser
                        Log.e(TAG, "User" + user)
                        initFirebase()
                        showProgressDialog()
                        startActivity(Intent(this@LoginActivity, HomeMenuActivity::class.java))

                    } else {
                        // Sign in fails
                        Toast.makeText(this, "Sign in fail.", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(applicationContext, "Google Play Services error.", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        Log.e("CurrentUser", "CurrentUser" + currentUser)
    }
}