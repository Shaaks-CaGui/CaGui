package com.sparklead.cagui.ui.activities

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.Info
import com.sparklead.cagui.ui.adapters.BranchInfoAdapter
import com.sparklead.cagui.ui.adapters.ExploreDetailsAdapter
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_branch_info.*
import kotlinx.android.synthetic.main.activity_explore_details.*

class BranchInfoActivity : BaseActivity() {

    private lateinit var mTitleInfo : String
    private lateinit var mBranch :String
    private lateinit var mTitle :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branch_info)

        supportActionBar?.hide()

        mTitleInfo = intent.getStringExtra(Constants.EXTRA_BRANCH_TITLE)!!
        mBranch = intent.getStringExtra(Constants.EXTRA_BRANCH_NAME)!!
        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)!!

        tv_title_explore_details_info.text = mTitleInfo
    }

    override fun onResume() {
        super.onResume()
        getBranchInfo()
    }

    private fun getBranchInfo(){
        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().getInfoList(this ,mTitle,mTitleInfo)

    }

    fun populateListUI(overview:String,path:String){
        hideProgressDialog()

        rv_details_info.layoutManager = LinearLayoutManager(this)
        rv_details_info.setHasFixedSize(true)

        val branchInfoAdapter = BranchInfoAdapter(this,false,overview,path)
        rv_details_info.adapter = branchInfoAdapter
    }
}