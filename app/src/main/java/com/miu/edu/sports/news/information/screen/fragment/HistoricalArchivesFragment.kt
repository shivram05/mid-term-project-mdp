package com.miu.edu.sports.news.information.screen.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.HistoricalArchivesAdapter
import com.miu.edu.sports.news.information.databinding.FragmentHistoricalArchivesBinding
import com.miu.edu.sports.news.information.dialog.HistoricalArchivesDialogFragment
import com.miu.edu.sports.news.information.dialog.OnAddHistoricalItemClicked
import com.miu.edu.sports.news.information.model.HistoricalArchivesModel
import com.miu.edu.sports.news.information.utils.StaticData
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog

class HistoricalArchivesFragment : Fragment(), OnAddHistoricalItemClicked {

    private lateinit var binding: FragmentHistoricalArchivesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoricalArchivesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addHistorical.setOnClickListener {
            val historicalDialog = HistoricalArchivesDialogFragment(this)
            historicalDialog.show(activity?.supportFragmentManager!!, historicalDialog.tag)

        }
        binding.historicalRecyclerView.adapter = HistoricalArchivesAdapter().apply {
            submitList(StaticData.historicalArchivesData())
        }
    }

    override fun onHistoricalAddedClicked(
        historicalArchivesModel: HistoricalArchivesModel,
        dialog: Dialog?
    ) {
        activity?.showProgressDialog()
        StaticData.historicalArchivesList.add(
            HistoricalArchivesModel(historicalArchivesModel.historyName,historicalArchivesModel.description,historicalArchivesModel.date)
        )
        hideProgressDialog()
        activity?.showAlert(getString(R.string.historical_added_successfully), positiveString = getString(
            R.string.ok), negativeString = "", onPositiveBtnClicked = {
            dialog?.dismiss()
        }, onNegativeBtnClicked = {})
    }
}