package com.codegalaxy.practicewithbhanu.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.codegalaxy.practicewithbhanu.R
import com.codegalaxy.practicewithbhanu.viewmodel.SharedViewModel

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        titleTextView = findViewById(R.id.tv_title)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewPager.adapter = ViewPagerAdapter(this)

        sharedViewModel.sharedText.observe(this) { text ->
            titleTextView.text = text
        }
    }

    private inner class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Fragment1()
                else -> Fragment2()
            }
        }
    }
}