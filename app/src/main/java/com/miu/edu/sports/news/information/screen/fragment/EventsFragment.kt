package com.miu.edu.sports.news.information.screen.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.EventsAdapter
import com.miu.edu.sports.news.information.databinding.FragmentEventsBinding
import com.miu.edu.sports.news.information.dialog.EventsDialogFragment
import com.miu.edu.sports.news.information.dialog.OnAddEventsItemClicked
import com.miu.edu.sports.news.information.model.EventsModel
import com.miu.edu.sports.news.information.utils.StaticData
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog

class EventsFragment : Fragment(), OnAddEventsItemClicked {

    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEvents.setOnClickListener {
            val sportsDialog = EventsDialogFragment(this)
            sportsDialog.show(activity?.supportFragmentManager!!, sportsDialog.tag)

        }
        binding.eventsRecyclerView.adapter = EventsAdapter().apply {
            submitList(StaticData.eventsData())
        }
    }

    override fun onEventsAdded(eventsModel: EventsModel, dialog: Dialog?) {
        activity?.showProgressDialog()
        StaticData.eventsList.add(
            EventsModel(eventsModel.eventName,eventsModel.description,eventsModel.date)
        )
        hideProgressDialog()
        activity?.showAlert(getString(R.string.events_added_successfully), positiveString = getString(
            R.string.ok), negativeString = "", onPositiveBtnClicked = {
            dialog?.dismiss()
        }, onNegativeBtnClicked = {})
    }
}