package com.example.progressivelearning_android.ui

import CustomTabHelper
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.text.HtmlCompat
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.Unit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_screen_slide_unit.*

private const val TAG = "ScreenSlideUnit"

class ScreenSlideUnit(var unit: Unit) : Fragment() {

    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen_slide_unit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        tv_unit_title.text = unit.title
        if (unit.summary != null) tv_summary.text = HtmlCompat.fromHtml(unit.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
        checkbox_completed.isChecked = unit.completed
        btn_resources.setOnClickListener {
            displayMaterialDialog()
        }
    }

    private fun displayMaterialDialog() {
        if (unit.resources.size > 0) {
            var array: Array<String?> = arrayOfNulls(unit.resources.size)
            for (i in array.indices) {
                array[i] = unit.resources[i].url
            }

            MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.resources))
                    .setItems(array)
                    { dialog, which ->
                        val url = array[which]

                        val builder = CustomTabsIntent.Builder()
                        val customTabsIntent = builder.build()
                        val packageName = customTabHelper.getPackageNameToUse(this.requireContext(), url.toString())

                        if (packageName == null) {
                            println("Empty package name")
                        } else {
                            customTabsIntent.intent.setPackage(packageName)
                            customTabsIntent.launchUrl(this.requireContext(), Uri.parse(url))
                        }
                    }.show()
        } else {
            Toast.makeText(requireContext(), R.string.no_resources, Toast.LENGTH_SHORT).show()
        }

    }


}



