package com.sparklead.cagui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()


        tv_signUp.setOnClickListener(this)
        btn_signIn.setOnClickListener(this)
        tv_forget_password.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if(view!=null)
        {
            when(view.id)
            {
                R.id.tv_signUp ->
                {
                    startActivity(Intent(this, SignUpActivity::class.java))
                }
                R.id.tv_forget_password ->
                {
                    startActivity(Intent(this, ForgetPasswordActivity::class.java))
                }
                R.id.btn_signIn ->
                {
                    logInRegisteredUser()
                }
            }
        }
    }

    private fun validateLoginDetails():Boolean {

        return when {
            TextUtils.isEmpty(et_email_signTn.text.toString().trim{it<= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_email),true)
                false
            }
            TextUtils.isEmpty(et_password_signIn.text.toString().trim{it<= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_password),true)
                false
            }
            else ->
            {
//                showErrorSnackBar("Your details are valid.",false)
                true
            }
        }
    }

    private fun logInRegisteredUser() {

        if(validateLoginDetails()){

            showProgressbar(resources.getString(R.string.please_wait))

            val email =et_email_signTn.text.toString().trim{ it <= ' '}
            val password = et_password_signIn.text.toString().trim{ it <= ' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{ task ->


                if(task.isSuccessful)
                {
                    //Todo - send user_id to Main Activity
                    FirestoreClass().getUserDetails(this)
                    showErrorSnackBar("You are logged in successfully.",false)
//                    startActivity(Intent(this,MainActivity::class.java))
//                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                }
                else
                {
                    hideProgressDialog()
                    showErrorSnackBar(task.exception!!.message.toString(),true)
                }
            }
        }
    }
    fun userLoggedInSuccess(user: User){
        hideProgressDialog()

        if(user.profileCompleted==0) {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(Constants.EXTRA_USER_DETAILS,user)
                startActivity(intent)
                finish()
            }, 200)
        }
        else
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent =Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }, 200)
        }


    }
    override fun onBackPressed() {
        doubleBackToExit()
    }
}