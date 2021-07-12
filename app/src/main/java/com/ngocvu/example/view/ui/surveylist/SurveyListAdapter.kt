package com.ngocvu.example.view.ui.surveylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngocvu.example.R
import com.ngocvu.example.data.entity.SurveyListEntity
import kotlinx.android.synthetic.main.item_survey.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SurveyListAdapter(
    private val context: Context,
    private val surveyList: ArrayList<SurveyListEntity>
) : RecyclerView.Adapter<SurveyListAdapter.SurveyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        return SurveyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_survey, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        val calendar = Calendar.getInstance()
        val currentDate: String = SimpleDateFormat("EEE, MMMM d ").format(calendar.time)
        holder.itemHeaderTitle.text = currentDate
        holder.surveyTitle.text = surveyList[position].title
        holder.surveyDesc.text = surveyList[position].description
        var highResImgUrl = surveyList[position].coverImageUrl + "l"
        Glide.with(context)
            .load(highResImgUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    class SurveyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val surveyTitle = view.tv_title
        val surveyDesc = view.tv_description
        val image = view.imv_background
        val itemHeaderTitle = view.tv_header_title
    }

}
