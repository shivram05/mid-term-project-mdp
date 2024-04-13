package com.miu.edu.sports.news.information.screen.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.AthletesAdapter
import com.miu.edu.sports.news.information.utils.StaticData.athletesData
import com.miu.edu.sports.news.information.databinding.FragmentAthletesBinding
import com.miu.edu.sports.news.information.dialog.AthletesDialogFragment
import com.miu.edu.sports.news.information.dialog.OnAddAthletesItemClicked
import com.miu.edu.sports.news.information.model.AthletesModel
import com.miu.edu.sports.news.information.utils.StaticData
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog


class AthletesFragment : Fragment(), OnAddAthletesItemClicked {

    lateinit var binding: FragmentAthletesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAthletesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNews.setOnClickListener {
            val athletesDialog = AthletesDialogFragment(this)
            athletesDialog.show(activity?.supportFragmentManager!!, athletesDialog.tag)
        }
        binding.athletesRecyclerView.adapter = AthletesAdapter().apply {
            submitList(athletesData())
        }
    }

    override fun onAthletesItemClick(athletesModel: AthletesModel, dialog: Dialog?) {
        activity?.showProgressDialog()
        StaticData.athletesList.add(
            AthletesModel(name =
                athletesModel.name, competingSport = athletesModel.competingSport,
                origin = athletesModel.origin, personalBest = athletesModel.personalBest,
                numberOfMedals = athletesModel.numberOfMedals, interestingFacts = athletesModel.interestingFacts
            )
        )
        hideProgressDialog()
        activity?.showAlert(getString(R.string.athletes_added_successfully), positiveString = getString(
            R.string.ok), negativeString = "", onPositiveBtnClicked = {
            dialog?.dismiss()
        }, onNegativeBtnClicked = {})
    }
}