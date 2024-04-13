package com.miu.edu.sports.news.information.screen.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.NewsAdapter
import com.miu.edu.sports.news.information.utils.StaticData
import com.miu.edu.sports.news.information.utils.StaticData.newsData
import com.miu.edu.sports.news.information.databinding.FragmentNewsBinding
import com.miu.edu.sports.news.information.dialog.NewsDialogFragment
import com.miu.edu.sports.news.information.dialog.OnAddNewsItemClicked
import com.miu.edu.sports.news.information.model.NewsModel
import com.miu.edu.sports.news.information.utils.hideProgressDialog
import com.miu.edu.sports.news.information.utils.showAlert
import com.miu.edu.sports.news.information.utils.showProgressDialog

class NewsFragment : Fragment(), OnAddNewsItemClicked {

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNews.setOnClickListener {
            val sportsDialog = NewsDialogFragment(this)
            sportsDialog.show(activity?.supportFragmentManager!!, sportsDialog.tag)

        }
        binding.newsRecyclerView.adapter = NewsAdapter().apply {
            submitList(newsData())
        }
    }

    override fun onNewsAdded(newsModel: NewsModel, dialog: Dialog?) {
        activity?.showProgressDialog()
        StaticData.newsList.add(
            NewsModel(title = newsModel.title,
            description = newsModel.description, imageUri = newsModel.imageUri)
        )
        hideProgressDialog()
        activity?.showAlert(getString(R.string.news_added_successfully), positiveString = getString(
            R.string.ok), negativeString = "", onPositiveBtnClicked = {
            dialog?.dismiss()
        }, onNegativeBtnClicked = {})
    }
}