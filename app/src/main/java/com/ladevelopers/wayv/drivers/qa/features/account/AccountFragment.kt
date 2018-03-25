package com.ladevelopers.wayv.drivers.qa.features.account

import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.databinding.FragmentAccountBinding
import com.ladevelopers.wayv.drivers.qa.infrastructure.App
import com.ladevelopers.wayv.drivers.qa.infrastructure.ViewModelFactory
import javax.inject.Inject

class AccountFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity!!.application as App).component.inject(this)
        val binding = DataBindingUtil.inflate<FragmentAccountBinding>(
                inflater, R.layout.fragment_account, container, false)
        val viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(AccountViewModel::class.java)
        binding.vm = viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.account)
    }

    companion object {
        fun newInstance() = AccountFragment()
    }
}
