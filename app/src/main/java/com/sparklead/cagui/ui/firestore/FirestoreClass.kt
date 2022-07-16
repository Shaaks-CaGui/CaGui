package com.sparklead.cagui.ui.firestore

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sparklead.cagui.models.BranchItem
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.activities.*
import com.sparklead.cagui.ui.fragments.HomeFragment

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val accounting = "Accounting"

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

    private fun getCurrentUserId() :String {

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

    fun getUserDetails(activity: Activity){

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
////                key : logged_in_success :Aditya Gupta
//                editor.putString(Constants.LOGGED_IN_USERNAME," ${user.name}")
//                editor.apply()
////                //end
                when(activity){
                    is SignInActivity ->
                    {
                        //call a function of base activity for transferring the result to it
                        activity.userLoggedInSuccess(user)

                    }
                    is SettingActivity ->
                    {
//                        activity.userDetailsSuccess(user)
                    }
                }

            }
        //end
    }

    fun updateUserProfileData(activity: Activity,userHashMap: HashMap<String,Any>)
    {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnCompleteListener{

                println("Yes profile updated")
                when(activity)
                {
                    is ProfileActivity ->
                    {
                        activity.userProfileUpdateSuccess()
                        println("Yes")
                    }
                }
            }
            .addOnFailureListener{ e ->
                when(activity)
                {
                    is ProfileActivity ->
                    {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,"Error while updating the user_id details",e
                )
            }
    }


    fun uploadImageToCloudStorage(activity: Activity, imageFileURI: Uri?, imageType : String ) {

        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(
                activity,
                imageFileURI
            )
        )

        //adding the file to reference
        sRef.putFile(imageFileURI!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())

                        // TODO Step 8: Pass the success result to base class.
                        // START
                        // Here call a function of base activity for transferring the result to it.
                        when (activity) {
                            is ProfileActivity -> {
                                activity.imageUploadSuccess(uri.toString())
                            }
                        }
                        // END
                    }
            }
            .addOnFailureListener { exception ->

                // Hide the progress dialog if there is any error. And print the error in log.
                when (activity) {
                    is ProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e("ABCD",
                    exception.message,
                    exception
                )
            }
    }

    fun getUserDetailsForFragment(fragment: Fragment){

        //Here we pass the collection name from which we wants the data.
        mFirestore.collection(Constants.USERS)

            // the document id to get the field of User.
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document->

                //Here we have received the document snapshot which is converted into the User data model object.
                val user = document.toObject(User::class.java)!!

//                val sharedPreferences = activity.getSharedPreferences(
//                    Constants.CAGUI_PREFERENCES,
//                    Context.MODE_PRIVATE
//                )
//
//                val editor : SharedPreferences.Editor = sharedPreferences.edit()
////                key : logged_in_success :Aditya Gupta
//                editor.putString(Constants.LOGGED_IN_USERNAME," ${user.name}")
//                editor.apply()
////                //end
                when(fragment){
                    is HomeFragment ->
                    {
                        //call a function of base activity for transferring the result to it
                        fragment.userDetailsSuccess(user)

                    }

                }

            }
        //end
    }

//    fun getBranchList(activity: Activity,title:String){
//        mFirestore.collection(Constants.BRANCH)
////            .whereEqualTo(Constants.ACCOUNTING,title)
////            .document(Constants.ACCOUNTING)
//            .get()
//            .addOnSuccessListener { document ->
//
//                println(Constants.ACCOUNTING ==title)
////
//                println("Start")
//                Log.e(
//                    activity.javaClass.simpleName, document.documents.toString()
//                )
//                println("End")
//
//                val list: ArrayList<BranchItem> = ArrayList()
//                val titleList : ArrayList<String> = ArrayList()
//
////                for(i in document.documents){
////                    val branchItem = i.toObject(BranchItem::class.java)!!
////                    list.add(branchItem)
////                }
//
//                for(i in document.documents){
//                    val titleHead = i.toObject(BranchItem::class.java)!!
//                    titleList.add(titleHead.branch1.title)
//                    titleList.add(titleHead.branch2.title)
//                }
//
//
//
//
////                Log.e(activity.javaClass.simpleName,document.data.toString())
////
////                val list = document.toObject(BranchItem::class.java)!!
//
//
//                when(activity){
//                    is ExploreDetailsActivity ->{
//                        activity.populateListUI(titleList)
//                    }
//                }
//            }
//            .addOnFailureListener { e->
//
//            }
//    }
    fun getBranchList(activity: Activity,title:String){
        mFirestore.collection(Constants.BRANCH)
//            .whereEqualTo(Constants.ACCOUNTING,title)
            .document(title)
            .get()
            .addOnSuccessListener { document ->

                println(Constants.ACCOUNTING ==title)
//
                println("Start")
                Log.e(
                    activity.javaClass.simpleName, document.data.toString()
                )
                println("End")

                val list: ArrayList<BranchItem> = ArrayList()
                val titleList : ArrayList<String> = ArrayList()

//                for(i in document.documents){
//                    val branchItem = i.toObject(BranchItem::class.java)!!
//                    list.add(branchItem)
//                }

                val titleHead = document.toObject(BranchItem::class.java)!!

                val details = titleHead.details
                val no = titleHead.no.toInt()
                titleList.add(titleHead.branch1.title)

                titleList.add(titleHead.branch2.title)

                titleList.add(titleHead.branch3.title)





//                Log.e(activity.javaClass.simpleName,document.data.toString())
//
//                val list = document.toObject(BranchItem::class.java)!!


                when(activity){
                    is ExploreDetailsActivity ->{
                        activity.populateListUI(titleList,no,details)
                    }
                }
            }
            .addOnFailureListener { e->

            }
    }

    fun getInfoList(activity: Activity,title: String,branch_name: String){
        mFirestore.collection(Constants.BRANCH)
            .document(title)
            .get()
            .addOnSuccessListener { document->

                Log.e(
                    activity.javaClass.simpleName, document.data.toString()
                )
//                val titleList : ArrayList<String> = ArrayList()
//                val overview :ArrayList<String> = ArrayList()
//                val path :ArrayList<String> = ArrayList()
//                val responsibilities :ArrayList<String> = ArrayList()
//                val skills :ArrayList<String> = ArrayList()
//                val careerProspects :ArrayList<String> = ArrayList()
//                val companies :ArrayList<String> = ArrayList()
//                val prosAndCons :ArrayList<String> = ArrayList()
//                val futureGrowth :ArrayList<String> = ArrayList()
//                val alternateCareer :ArrayList<String> = ArrayList()
//                val averageSalary :ArrayList<String> = ArrayList()

                var overview :String = " "
                var path :String = " "

                val titleHead = document.toObject(BranchItem::class.java)!!
                if(titleHead.branch1.tag == branch_name){
                    overview = titleHead.branch1.overview
                    path = titleHead.branch1.path
                }
                if(titleHead.branch2.tag == branch_name){
                    overview = titleHead.branch2.overview
                    path = titleHead.branch2.path
                }

                when(activity){
                    is BranchInfoActivity ->{
                        activity.populateListUI(overview,path)
                    }
                }


            }

    }

}