package com.example.progressivelearning_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import kotlinx.android.synthetic.main.item_learning_goal.view.*

class LearningGoalAdapter(
        private val learningGoals: List<LearningGoal>,
        private val onClick: (LearningGoal) -> Unit
):
        RecyclerView.Adapter<LearningGoalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                (onClick(learningGoals[adapterPosition]))
            }

        }
        fun databind(learningGoal: LearningGoal) {
            itemView.tv_title.text = learningGoal.title
            itemView.tv_progress.text = "${learningGoal.progress}%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_learning_goal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(learningGoals[position])
    }

    override fun getItemCount(): Int {
        return learningGoals.size
    }

}