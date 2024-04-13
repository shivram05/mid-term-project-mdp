package com.miu.edu.sports.news.information.screen.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.SportsAdapter
import com.miu.edu.sports.news.information.utils.StaticData
import com.miu.edu.sports.news.information.utils.StaticData.sportsData
import com.miu.edu.sports.news.information.databinding.FragmentSportsBinding
import com.miu.edu.sports.news.information.dialog.OnAddNewSportsItemClicked
import com.miu.edu.sports.news.information.dialog.SportsDialog
import com.miu.edu.sports.news.information.model.SportsModel
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog

class SportsFragment : Fragment(), OnAddNewSportsItemClicked {
    private lateinit var binding: FragmentSportsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sportsRecyclerView.adapter =
            SportsAdapter().apply {
                submitList(sportsData())
            }

        binding.addSports.setOnClickListener {
            val sportsDialog = SportsDialog(this)
            sportsDialog.show(activity?.supportFragmentManager!!,sportsDialog.tag)
        }
    }

    override fun onNewSportsAdded(sportsModel: SportsModel, dialog: Dialog?) {
        activity?.showProgressDialog()
        StaticData.sportsList.add(SportsModel(sportsType = sportsModel.sportsType,
           sportsName = sportsModel.sportsName, instruction = sportsModel.instruction))
        hideProgressDialog()
        activity?.showAlert(getString(R.string.sports_added_successfully), positiveString = getString(R.string.ok), negativeString = "", onPositiveBtnClicked = {
            dialog?.dismiss()
        }, onNegativeBtnClicked = {})
    }
}