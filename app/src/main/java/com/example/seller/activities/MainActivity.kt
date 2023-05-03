package com.example.seller.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seller.R
import com.example.seller.adapters.ListingsAdapter
import com.example.seller.models.ListingDataModel
import com.example.seller.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var listingsAdapter: ListingsAdapter
    private lateinit var sortOptions: ArrayList<String>
    private lateinit var spinner: Spinner
    private lateinit var loader: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sortOptions = ArrayList()
        sortOptions.add("Sort By: ")
        sortOptions.add("Sort By: Price(Low to High)")
        sortOptions.add("Sort By: Price(High to Low)")
        sortOptions.add("Sort By: Date(Latest to Oldest)")
        sortOptions.add("Sort By: Date(Oldest to Latest)")

        val optionsAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            sortOptions
        )
        spinner = findViewById(R.id.spinner)
        spinner.adapter = optionsAdapter


        val listingsRV = findViewById<RecyclerView>(R.id.listingsRV)
        loader = findViewById(R.id.loader)
        listingsRV.layoutManager = LinearLayoutManager(this)
        listingsAdapter = ListingsAdapter()
        listingsRV.adapter = listingsAdapter

        initViewModel()

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        viewModel.makeApiCall()
        viewModel.getRecyclerListObserver()
            .observe(this, Observer<ListingDataModel> {

                var orderBy = 0
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        orderBy = position
                        Log.i("tag", orderBy.toString())
                        listingsAdapter.setUpdatedItems(it.results, orderBy)

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        orderBy = 0
                        Log.i("tag", orderBy.toString())
                        listingsAdapter.setUpdatedItems(it.results, orderBy)

                    }

                }
                loader.visibility = View.GONE
                listingsAdapter.setUpdatedItems(it.results, orderBy)


            })

    }
}