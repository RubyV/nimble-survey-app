package com.ngocvu.example.view.ui.surveylist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngocvu.example.R
import com.ngocvu.example.data.res.SurveyListReqData
import kotlinx.android.synthetic.main.item_survey.view.*

class SurveyListAdapter(private val context: Context, private val surveyList: ArrayList<SurveyListReqData.Data>) : RecyclerView.Adapter<SurveyListAdapter.CheeseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        return CheeseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_survey, parent, false))
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.surveyTitle.text = surveyList[position].attributes.title
        holder.surveyDesc.text = surveyList[position].attributes.description
        var highResImgUrl = surveyList[position].attributes.cover_image_url + "l"
        Glide.with(context)
            .load(highResImgUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }



    class CheeseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val surveyTitle = view.tv_title
        val surveyDesc = view.tv_description
        val image = view.imv_background
    }

}
