package com.ladevelopers.wayv.drivers.qa.features

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class Feature(titleRes: Int, val imageRes: Int) {
    private lateinit var _activity: MainActivity
    protected val activity: MainActivity get() = _activity

    val title: String by lazy { activity.getString(titleRes).orEmpty() }

    fun setActivity(activity: MainActivity?) {
        _activity = activity ?: return
    }

    open fun start() = activity.drawer_layout.closeDrawers()
}

abstract class FragmentFeature
(titleRes: Int, imageRes: Int, private val name: String, private val factory: () -> Fragment)
    : Feature(titleRes, imageRes) {

    constructor(titleRes: Int, imageRes: Int, fragmentClass: Class<*>, factory: () -> Fragment)
            : this(titleRes, imageRes, fragmentClass.name, factory)

    private val fragmentManager: FragmentManager get() = activity.supportFragmentManager

    protected fun open(fragment: Fragment, name: String? = null) {
        val fragmentManager = activity.supportFragmentManager
        if (!fragmentManager.popBackStackImmediate(name, 0))
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fragment_enter, R.anim.fragment_exit,
                            R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(name)
                    .commit()
        activity.drawer_layout.closeDrawers()
    }

    override fun start() {
        if (!fragmentManager.popBackStackImmediate(name, 0))
            open(factory(), name)
        super.start()
    }
}

