package com.ladevelopers.wayv.drivers.qa.features.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.creomobile.lists.BR
import com.creomobile.lists.RecycleListAdapter
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.databinding.ActivityMainBinding
import com.ladevelopers.wayv.drivers.qa.features.Feature
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardFragment
import com.ladevelopers.wayv.drivers.qa.infrastructure.App
import com.ladevelopers.wayv.drivers.qa.infrastructure.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_navigation_menu.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), android.support.v4.app.FragmentManager.OnBackStackChangedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main)
        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel::class.java)
        binding.vm = viewModel

        versionTextView.text = packageManager.getPackageInfo(packageName, 0).versionName

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecycleListAdapter.Builder()
                    .addView<Feature>(R.layout.view_navigation_menu_item, BR.vm)
                    .build()
        }

        val toolbar = this.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content_frame, DashboardFragment.newInstance())
                    .commit()
        } else {
            onBackStackChanged()
        }

        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onBackStackChanged() {
        val toolbar = this.findViewById<Toolbar>(R.id.toolbar)

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { supportFragmentManager.popBackStack() }
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            actionBarDrawerToggle.syncState()
            toolbar.setNavigationOnClickListener { drawer_layout.openDrawer(GravityCompat.START) }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
