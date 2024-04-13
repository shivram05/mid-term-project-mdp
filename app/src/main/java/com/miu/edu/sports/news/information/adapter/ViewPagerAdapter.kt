package com.miu.edu.sports.news.information.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.edu.sports.news.information.screen.fragment.AboutMeFragment
import com.miu.edu.sports.news.information.screen.fragment.AthletesFragment
import com.miu.edu.sports.news.information.screen.fragment.EventsFragment
import com.miu.edu.sports.news.information.screen.fragment.HistoricalArchivesFragment
import com.miu.edu.sports.news.information.screen.fragment.NewsFragment
import com.miu.edu.sports.news.information.screen.fragment.SportsFragment

private const val NUM_TABS = 6

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SportsFragment()
            1 -> return NewsFragment()
            2-> return AthletesFragment()
            3-> return EventsFragment()
            4-> return HistoricalArchivesFragment()
            5-> return AboutMeFragment()
        }
        return SportsFragment()
    }
}