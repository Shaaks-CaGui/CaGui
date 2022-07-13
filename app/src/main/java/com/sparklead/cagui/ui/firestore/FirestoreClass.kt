package com.sparklead.cagui.ui.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.activities.SignInActivity
import com.sparklead.cagui.ui.activities.SignUpActivity

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User){

        //the "users" is a collection name.
        mFirestore.collection(Constants.USERS)
            //Document Id for users fields.
            .document(userInfo.id)
            //here the userInfo are field and the setOption is set to merge.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user_id.",
                    e
                )
            }
    }

    fun getCurrentUserId() :String {

        //An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserId = ""
        if(currentUser!=null)
        {
            currentUserId = currentUser.uid
        }

        return currentUserId

    }

    fun getUserDetails(activity: SignInActivity){

        //Here we pass the collection name from which we wants the data.
        mFirestore.collection(Constants.USERS)

            // the document id to get the field of User.
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document->
                Log.i(activity.javaClass.simpleName,document.toString())

                //Here we have received the document snapshot which is converted into the User data model object.
                val user = document.toObject(User::class.java)!!

//                val sharedPreferences = activity.getSharedPreferences(
//                    Constants.CAGUI_PREFERENCES,
//                    Context.MODE_PRIVATE
//                )
//
//                val editor : SharedPreferences.Editor = sharedPreferences.edit()
//                //key : logged_in_success :Aditya Gupta
//                editor.putString(Constants.LOGGED_IN_USERNAME," ${user.name}")
//                editor.apply()
//                //end

                activity.userLoggedInSuccess(user)

            }
        //end
    }

}