package com.esiea.android4A.presentation.rapperlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.esiea.android4A.Constants
import com.esiea.android4A.R
import com.esiea.android4A.Singletons
import com.esiea.android4A.domain.entity.Rapper
import com.esiea.android4A.presentation.rapperinfo.InfoActivity
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject


class ListActivity  : AppCompatActivity(), ListAdapter.OnItemClickListener {
    private val listViewModel: ListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewModel.apiCall()
        listViewModel.apiLiveData.observe(this, Observer { it ->
            when (it) {
                is ListSuccess -> {
                    showList(it.rappers)
                }
                ListError -> {
                    Toast.makeText(this, "API Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showList(rappers: List<Rapper>) {
        recycler_view.adapter = ListAdapter(rappers, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        listViewModel.onItemClick(position)
        listViewModel.rapperLiveData.observe(this, Observer { value ->
            run {
                val intent = Intent(this, InfoActivity::class.java)
                intent.putExtra(Constants.KEY_Country_List, Singletons.gson!!.toJson(value))
                startActivity(intent)
            }
        })
    }
}

