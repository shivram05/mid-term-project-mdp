package com.miu.edu.sports.news.information.screen.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.auth.SignInActivity
import com.miu.edu.sports.news.information.databinding.FragmentAboutMeBinding
import com.miu.edu.sports.news.information.utils.Constant.Companion.PROFILE_URL
import com.miu.edu.sports.news.information.utils.SharedPreferenceStorage
import com.miu.edu.sports.news.information.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AboutMeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferenceStorage: SharedPreferenceStorage

    private lateinit var aboutMeBinding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        aboutMeBinding = FragmentAboutMeBinding.inflate(layoutInflater)
        return aboutMeBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutMeBinding.userName.text = "${sharedPreferenceStorage.firstName} ${sharedPreferenceStorage.lastName}"
        aboutMeBinding.logout.setOnClickListener {
            activity?.showAlert(
                getString(R.string.are_you_sure_want_to_logout), getString(R.string.ok),
                "", onPositiveBtnClicked = {
                    sharedPreferenceStorage.logOutUser()
                    startActivity(Intent(requireContext(), SignInActivity::class.java))
                    activity?.finish()
                }, onNegativeBtnClicked = {})
        }
        Glide.with(this).load(PROFILE_URL).apply {
            centerCrop().placeholder(R.drawable.profile_place_holder)
                .into(aboutMeBinding.profileImage)
        }
    }
}