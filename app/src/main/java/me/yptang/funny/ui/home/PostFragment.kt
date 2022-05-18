package me.yptang.funny.ui.home

import android.os.Bundle
import android.util.Log
import me.yptang.funny.R
import me.yptang.funny.databinding.PostFragmentBinding
import me.yptang.funny.ui.EngineLazyFragment

class PostFragment : EngineLazyFragment<PostFragmentBinding>(R.layout.post_fragment) {

    companion object {
        fun newInstance(type: String): PostFragment {
            return PostFragment().also {
                val bundle = Bundle()
                bundle.putString("type", type)
                it.arguments = bundle
            }
        }
    }

    private lateinit var type: String

    override fun initView() {

    }

    override fun initData() {
        type = arguments?.getString("type") ?: return
        Log.e("Lazy-PostFragment", "initData: 类型 $type")
    }


}