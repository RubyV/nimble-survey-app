package com.ngocvu.example.view.ui.surveydetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngocvu.example.R

class SurveyDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SurveyDetailsFragment()
    }

    private lateinit var viewModel: SurveyDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SurveyDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}