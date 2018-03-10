package com.ladevelopers.wayv.drivers.qa.features.main

import android.app.FragmentManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.account.AccountFragment
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        version_text.text = packageManager.getPackageInfo(packageName, 0).versionName

        val toolbar = this.findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.content_frame, DashboardFragment.newInstance())
                .commit()

        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard ->
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                R.id.nav_account ->
                    navigateOrCreateFragment({ AccountFragment.newInstance() })
            }

            drawer_layout.closeDrawers()
            //Toast.makeText(this, menuItem.title, Toast.LENGTH_SHORT).show()
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                toolbar.setNavigationOnClickListener { supportFragmentManager.popBackStack() }
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toggle.syncState()
                toolbar.setNavigationOnClickListener { drawer_layout.openDrawer(GravityCompat.START) }
            }
        }
    }

    private fun navigateOrCreateFragment(factory: () -> Fragment, name: String) {
        if (!supportFragmentManager.popBackStackImmediate(name, 0))
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, factory())
                    .addToBackStack(name)
                    .commit()
    }

    private inline fun <reified T : Fragment> navigateOrCreateFragment(noinline factory: () -> T) =
            navigateOrCreateFragment(factory, T::class.simpleName!!)

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
