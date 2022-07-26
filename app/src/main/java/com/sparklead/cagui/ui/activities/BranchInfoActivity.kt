package com.sparklead.cagui.ui.activities

import android.net.Uri
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
    private lateinit var mBranch : String
    private lateinit var mTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branch_info)

        supportActionBar?.hide()

        if(intent.hasExtra(Constants.EXTRA_BRANCH_NAME)){
            mTitleInfo = intent.getStringExtra(Constants.EXTRA_BRANCH_NAME)!!
        }
        if(intent.hasExtra(Constants.EXTRA_TITLE)){
            mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)!!
        }
        if(intent.hasExtra(Constants.EXTRA_BRANCH_TITLE)){
            mBranch = intent.getStringExtra(Constants.EXTRA_BRANCH_TITLE)!!
        }

        tv_title_explore_details_info.text = mBranch

    }

    override fun onResume() {
        super.onResume()
        getBranchInfo()
    }

    private fun getBranchInfo(){
        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().getInfoList(this ,mTitle,mTitleInfo)

    }

    fun populateListUI(overview:String,path:String,responsibilities:String,skills:String,careerProspects:String,companies:String,prosAndCons:String,futureGrowth:String,alternateCareer:String,averageSalary:String){
        hideProgressDialog()

        rv_details_info.layoutManager = LinearLayoutManager(this)
        rv_details_info.setHasFixedSize(true)

        val branchInfoAdapter = BranchInfoAdapter(this,overview,path,responsibilities,skills,careerProspects,companies,prosAndCons,futureGrowth,alternateCareer,averageSalary)
        rv_details_info.adapter = branchInfoAdapter
    }
}