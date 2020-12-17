package com.example.progressivelearning_android.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.Resource
import com.example.progressivelearning_android.model.URLType
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import kotlinx.android.synthetic.main.fragment_resources_dialog.*
import kotlinx.android.synthetic.main.resource_list_item.view.*


class ResourcesDialogFragment(unit: Unit) : DialogFragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private val items = URLType.values()
    private var _unit: Unit = unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        _unit.resources.add(Resource(
//                "https://en.wikipedia.org/wiki/Representational_state_transfer",
//        URLType.WIKIPEDIA))
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, items)
        (tip_resource_type.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        rv_resources.adapter = ResourceAdapter(_unit.resources)
        rv_resources.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_resources.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        initViews()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onCancel(dialog: DialogInterface) {
        learningGoalViewModel.setNewUnit(_unit)
        super.onCancel(dialog)
    }

    private fun initViews() {
        add_resoure_btn.setOnClickListener {
            addResource()
        }

        cancel_button.setOnClickListener {
            requireDialog().cancel()
        }

        times_icon.setOnClickListener {
            requireDialog().cancel()
        }
    }


    inner class ResourceAdapter(var resources: ArrayList<Resource>):
            RecyclerView.Adapter<ResourceAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            fun databind(resource: Resource) {
                itemView.tv_resource_url.text = resource.url
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.resource_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.databind(resources[position])
        }

        override fun getItemCount(): Int {
            return resources.size
        }
    }

    private fun addResource() {
        val urlType: String = url_type_resource.text.toString()
        val url: String =  et_resource_url.text.toString()
        val resource = Resource(url, URLType.valueOf(urlType))
        _unit.resources.add(resource)
        url_type_resource.clearListSelection()
        et_resource_url.clearComposingText()
        rv_resources.adapter!!.notifyDataSetChanged()
    }

}