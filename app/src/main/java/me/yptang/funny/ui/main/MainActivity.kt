package me.yptang.funny.ui.main

import androidx.viewpager.widget.ViewPager
import com.drake.engine.adapter.FragmentPagerAdapter
import com.drake.engine.base.EngineActivity
import com.drake.statusbar.immersive
import me.yptang.funny.R
import me.yptang.funny.databinding.MainActivityBinding
import me.yptang.funny.ui.home.HomeFragment
import me.yptang.funny.ui.message.MessageFragment
import me.yptang.funny.ui.person.PersonFragment
import me.yptang.funny.ui.video.VideoFragment

class MainActivity : EngineActivity<MainActivityBinding>(R.layout.main_activity) {

    override fun initView() {
        immersive(darkMode = true)
        val pages = listOf(HomeFragment(), VideoFragment(), MessageFragment(), PersonFragment())
        binding.viewPager.adapter = FragmentPagerAdapter(pages)
        //首页tab不销毁
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                binding.navView.menu.getItem(position).isChecked = true
            }
        })
        binding.navView.setOnLongClickListener { true }
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tabHome -> binding.viewPager.currentItem = 0
                R.id.tabVideo -> binding.viewPager.currentItem = 1
                R.id.tabMessage -> binding.viewPager.currentItem = 2
                R.id.tabPerson -> binding.viewPager.currentItem = 3
            }
            true
        }
    }

    override fun initData() {

    }

}