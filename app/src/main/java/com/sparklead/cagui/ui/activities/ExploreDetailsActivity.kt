package com.sparklead.cagui.ui.activities

import android.net.Uri
import android.os.Bundle
import android.telecom.Call
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.cagui.R
import com.sparklead.cagui.models.BranchItem
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.ui.adapters.ExploreDetailsAdapter
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_explore_details.*

class ExploreDetailsActivity : BaseActivity() {

    lateinit var mTitle : String
    lateinit var mDetails :String
    private lateinit var mImage : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_details)

        supportActionBar?.hide()

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)!!
        mImage = intent.getStringExtra(Constants.EXTRA_IMAGE)!!

        val uri = Uri.parse(mImage)
        iv_branch_image.setImageURI(uri)

        println(uri)

        tv_title_explore_details.text = mTitle
        tv_title_heading.text = mTitle

    }

    override fun onResume() {
        super.onResume()
        getBranchList()
    }

    private fun getBranchList(){
        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().getBranchList(this,mTitle)
    }

    fun populateListUI(branchItem: ArrayList<String>,total : Int,details: String){
        hideProgressDialog()

        mDetails = details

        rv_branch_list.layoutManager = LinearLayoutManager(this)
        rv_branch_list.setHasFixedSize(true)

        val exploreDetailsAdapter = ExploreDetailsAdapter(this,branchItem,mTitle, total)
        rv_branch_list.adapter = exploreDetailsAdapter
        tv_title_explore_details_details.text = mDetails

    }
}