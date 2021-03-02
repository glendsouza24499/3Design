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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class Log_In_Activity : AppCompatActivity() {

    companion object{
        private const val RC_LOG_IN = 120
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log__in_)
        auth = FirebaseAuth.getInstance()

        //submit button for login
        val submit_login=findViewById<Button>(R.id.submit_login)
        submit_login.setOnClickListener {
            Login_submmit_fun()
        }


        //skip login button
        val skip_login=findViewById<Button>(R.id.skip_login_bttn)
        skip_login.setOnClickListener {
            val intentsk=Intent(this,MainActivity::class.java)
            startActivity(intentsk)
        }



        //submit button
        val signin_act_link=findViewById<TextView>(R.id.signup_page_link_from_login)
        signin_act_link.setOnClickListener {
            val intents=Intent(this,Sign_in_activity::class.java)
            startActivity(intents)
        }

        //google login button
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient= GoogleSignIn.getClient(this,gso)

        val google_login=findViewById<Button>(R.id.google_acc_login_bttn)
        google_login.setOnClickListener {
            logIn()
        }
    }

    private fun Login_submmit_fun(){
        val emailid_login=findViewById<EditText>(R.id.login_email_id)
        val password_login=findViewById<EditText>(R.id.login_password)

        if (emailid_login.text.toString().isEmpty()){
            emailid_login.error="Please enter Email Id"
            emailid_login.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid_login.text.toString()).matches()){
            emailid_login.error="Please enter valid Email Id"
            emailid_login.requestFocus()
            return
        }
        if (password_login.text.toString().isEmpty()) {
            password_login.error = "Please enter Password"
            password_login.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emailid_login.text.toString(), password_login.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }

    private fun logIn() {
        val logInIntent = googleSignInClient.signInIntent
        startActivityForResult(logInIntent, Log_In_Activity.RC_LOG_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Log_In_Activity.RC_LOG_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if (task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("Google Login Activiy", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("Google Login Activiy", "Google login failed", e)
                }
            }else{
                Log.w("Google Login Activiy", exception.toString())

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Google Login Activiy", "signInWithCredential:success")
                        val mainpage=Intent(this,MainActivity::class.java)
                        startActivity(mainpage)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Google Login Activiy", "signInWithCredential:failure", task.exception)

                    }

                }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser:FirebaseUser?){
        if(currentUser!=null){
            if(currentUser.isEmailVerified){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                Toast.makeText(baseContext, "Please verify your Email Id first",
                    Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(baseContext, "Login Failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}
