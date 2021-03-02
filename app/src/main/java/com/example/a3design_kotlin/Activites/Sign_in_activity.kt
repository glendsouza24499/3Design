package com.example.a3design_kotlin.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.a3design_kotlin.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Sign_in_activity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_activity)
        auth = FirebaseAuth.getInstance()

         //Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,gso)

        //google sign in button
        val google_signin=findViewById<Button>(R.id.google_acc_signup_bttn)
        google_signin.setOnClickListener {
            signIn()
        }

        //submit sign in button
        val submit_signin=findViewById<Button>(R.id.submit_signup)
        submit_signin.setOnClickListener {
           SignUp()
        }
    }

    //this fun for sign up new user
    private fun SignUp(){
        val emailid_signin=findViewById<EditText>(R.id.signup_email_id)
        val password_signin=findViewById<EditText>(R.id.signin_password)

        if (emailid_signin.text.toString().isEmpty()){
            emailid_signin.error="Please enter Email Id"
            emailid_signin.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid_signin.text.toString()).matches()){
            emailid_signin.error="Please enter valid Email Id"
            emailid_signin.requestFocus()
            return
        }
        if (password_signin.text.toString().isEmpty()){
            password_signin.error="Please enter Password"
            password_signin.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(emailid_signin.text.toString(), password_signin.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user=auth.currentUser
                        user!!.sendEmailVerification()
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(baseContext, "Verification Email Sent",
                                                Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this,Log_In_Activity::class.java))
                                        finish()
                                    }
                                }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Sign Up failed try again later.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    //sign in for gmail accounts
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if (task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("Google SignIn Activiy", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("Google SignIn Activiy", "Google sign in failed", e)
                }
            }else{
                Log.w("Google SignIn Activiy", exception.toString())

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Google SignIn Activiy", "signInWithCredential:success")
                    val mainpage=Intent(this,MainActivity::class.java)
                    startActivity(mainpage)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Google SignIn Activiy", "signInWithCredential:failure", task.exception)

                }

            }
    }
}