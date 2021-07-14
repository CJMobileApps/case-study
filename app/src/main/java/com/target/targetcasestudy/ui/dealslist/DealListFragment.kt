package com.target.targetcasestudy.ui.dealslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.DealsApplication

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.service.DealsServiceI
import com.target.targetcasestudy.ui.dagger.DaggerDealsComponent
import com.target.targetcasestudy.util.DealsViewModelFactory
import kotlinx.android.synthetic.main.fragment_deal_list.*
import javax.inject.Inject


class DealListFragment : Fragment() {

    @Inject
    lateinit var dealsService: DealsServiceI

    private val dealListViewModel: DealListViewModel by activityViewModels {
        DealsViewModelFactory(
            dealsService
        )
    }

    override fun onAttach(context: Context) {

        val dealsApplication = context.applicationContext as DealsApplication

        DaggerDealsComponent.builder()
            .dealsApplicationComponent(dealsApplication.dealsApplicationComponent)
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        recycler_view.addItemDecoration(dividerItemDecoration)

        val adapter = DealItemAdapter()
        recycler_view.adapter = adapter

        dealListViewModel.dealsLiveData.observe(viewLifecycleOwner, Observer { deals ->
            Log.d("HERE_", "deals 2: " + deals)
            adapter.products = deals.products
            adapter.notifyDataSetChanged()

        })

    }
}
