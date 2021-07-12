package com.ngocvu.example.view.ui.surveylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ngocvu.example.R
import com.ngocvu.example.data.entity.SurveyListEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_survey_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.collections.ArrayList


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SurveyListFragment : Fragment() {

    private lateinit var viewModel: SurveyListViewModel
    private lateinit var navController: NavController
    private var surveyList = ArrayList<SurveyListEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SurveyListViewModel::class.java)
        navController = Navigation.findNavController(view)
        initView()
    }

    fun initView() {
        getSurveyList()
        btn_take_survey.setOnClickListener {
            navController.navigate(R.id.action_startUpFragment_to_surveyDetailsFragment)
        }
    }

    fun getSurveyList() {
        pager.visibility = View.GONE
        issues_fetch_progress.visibility = View.VISIBLE
        btn_take_survey.visibility = View.GONE
        viewModel.getAllSurvey()
        viewModel.dataList.observe(viewLifecycleOwner) { response ->
            pager.visibility = View.VISIBLE
            issues_fetch_progress.visibility = View.GONE
            btn_take_survey.visibility = View.VISIBLE
            response.forEach {
                surveyList.add(it)
            }
            pager.adapter = SurveyListAdapter(requireContext(), surveyList)
            pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(tab_survey, pager) { tab, position ->
            }.attach()

        }
    }
}

