package com.ladevelopers.wayv.drivers.qa.features.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.databinding.FragmentDashboardBinding
import com.ladevelopers.wayv.drivers.qa.infrastructure.App
import com.ladevelopers.wayv.drivers.qa.infrastructure.ViewModelFactory
import javax.inject.Inject

class DashboardFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity!!.application as App).component.inject(this)
        val binding = DataBindingUtil.inflate<FragmentDashboardBinding>(
                inflater, R.layout.fragment_dashboard, container, false)
        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(DashboardViewModel::class.java)
        binding.vm = viewModel
        return binding.root
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

}
