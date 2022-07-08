package com.sparklead.cagui.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.cagui.R
import com.sparklead.cagui.ui.activities.Questions
import com.sparklead.cagui.ui.adapters.QuestionsListAdapter
import kotlinx.android.synthetic.main.fragment_faqs.*


class FAQsFragment : Fragment() {

    private lateinit var adapter :QuestionsListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var questionsArrayList: ArrayList<Questions>

    private lateinit var questions: ArrayList<String>
    private lateinit var answers : ArrayList<String>
    private lateinit var news : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = activity!!.getColor(R.color.fifth_color)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_faqs,container,false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.rv_faq_questions)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = QuestionsListAdapter(questionsArrayList)
        recyclerView.adapter = adapter
    }
//
//    private fun getQuestionList():ArrayList<String>{
//        val list = ArrayList<String>()
//
//        for(i in 1..15){
//            list.add("item $i")
//        }
//        return list
//    }

    private fun dataInitialize(){

        questionsArrayList = arrayListOf()

        questions = arrayListOf(
            "question1",
            "question2",
            "question3",
            "question4",
            "question5",
            "question6",
            "question7",
            "question8",
            "question9",
            "question10",
            "question11",
            "question12",
            "question13",
            "question14",
            "question15"
        )
        answers = arrayListOf(
            "First",
            "Second",
            "Third",
            "Fourth",
            "Fifth",
            "Sixth",
            "Seventh",
            "Eight",
            "Ninth",
            "Tenth",
            "Eleventh",
            "Twelfth",
            "Thirteen",
            "Fourteen",
            "Fifteen"
        )

        for(i in questions.indices){

            val question = Questions(questions[i],answers[i])
            questionsArrayList.add(question)
        }
    }

}