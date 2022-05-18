package me.yptang.funny.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.drake.engine.base.EngineActivity

abstract class EngineLazyFragment<B : ViewDataBinding>(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId), View.OnClickListener {

    lateinit var binding: B
    private var isLoad = false

    protected abstract fun initView()
    protected abstract fun initData()
    override fun onClick(v: View) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)!!
        val engineActivity = (requireActivity() as? EngineActivity<*>)
        engineActivity?.onBackPressed(this::onBackPressed)
    }

    override fun onResume() {
        super.onResume()
        if (!isLoad) {
            try {
                initView()
                initData()
                isLoad = true
            } catch (e: Exception) {
                Log.e("Engine", "Initializing failure")
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoad = false
    }

    @Deprecated(
        "建议使用onBackPressedDispatcher",
        ReplaceWith("requireActivity().onBackPressedDispatcher")
    )
    open fun onBackPressed(): Boolean {
        return false
    }

}