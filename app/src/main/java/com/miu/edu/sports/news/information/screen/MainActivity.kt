package com.miu.edu.sports.news.information.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.edu.sports.news.information.R
import com.miu.edu.sports.news.information.adapter.ViewPagerAdapter
import com.miu.edu.sports.news.information.databinding.ActivityMainBinding
import com.miu.edu.sports.news.information.utils.makeStatusColor
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sportsArray = arrayOf(
        "Sports",
        "News",
        "Athletes",
        "Events",
        "Historical Archives",
        "About"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeStatusColor()
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = sportsArray[position]
        }.attach()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> viewPager.currentItem = 1
                R.id.events -> viewPager.currentItem = 3
                R.id.historical_archives -> viewPager.currentItem = 4
            }
            false
        }
    }
}