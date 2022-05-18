package me.yptang.funny.ui.home

import androidx.viewpager.widget.ViewPager
import com.drake.engine.adapter.FragmentPagerAdapter
import com.drake.statusbar.statusPadding
import com.google.android.material.tabs.TabLayout
import me.yptang.funny.R
import me.yptang.funny.databinding.HomeFragmentBinding
import me.yptang.funny.simple.SimpleOnTabSelectedListener
import me.yptang.funny.ui.EngineLazyFragment

class HomeFragment : EngineLazyFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    companion object {
        const val POST_FAVORITE = "post_favorite"
        const val POST_RECOMMEND = "post_recommend"
        const val POST_NEW = "post_new"
        const val POST_TEXT = "post_text"
        const val POST_IMAGE = "post_image"
    }


    override fun initData() {

    }

    override fun initView() {
        binding.tabLayout.statusPadding()

        val titles = listOf("关注", "推荐", "新鲜", "纯文", "趣图")
        val pages = listOf(
            PostFragment.newInstance(POST_FAVORITE),
            PostFragment.newInstance(POST_RECOMMEND),
            PostFragment.newInstance(POST_NEW),
            PostFragment.newInstance(POST_TEXT),
            PostFragment.newInstance(POST_IMAGE),
        )

        binding.viewPager.offscreenPageLimit = 3
        val adapter = FragmentPagerAdapter(pages, titles)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                //binding.tabLayout.selectTab()
            }
        })

        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.addOnTabSelectedListener(object : SimpleOnTabSelectedListener() {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }
        })
    }

}