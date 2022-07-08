package com.sparklead.cagui.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.sparklead.cagui.R


class ExploreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = activity!!.getColor(R.color.second_color)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root =inflater.inflate(R.layout.fragment_explore,container,false)


        return root
    }

}