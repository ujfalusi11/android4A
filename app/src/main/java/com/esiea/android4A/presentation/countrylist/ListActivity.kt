package com.esiea.android4A.presentation.countrylist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.esiea.android4A.R
import com.esiea.android4A.domain.entity.Rapper
import com.esiea.android4A.presentation.countryinfo.InfoActivity
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject


class ListActivity  : AppCompatActivity(), ListAdapter.OnItemClickListener {

    private val listViewModel : ListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewModel.status.observe(this, Observer {
            when (it) {
                is ListSuccess -> {
                    showList(it.rappers)
                }
                ListError -> showError()
            }
        })

    }

    private fun showList(galList: List<Rapper>){

        recycler_view.adapter = ListAdapter(galList, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        listViewModel.onItemClick(position)
        listViewModel.rapperLiveData.observe(this, Observer { value ->
            run {
                val intent = Intent(this, InfoActivity::class.java)
                //intent.putExtra(Constants.EXTRA_GALAXIE, Singletons.gson!!.toJson(value))
                startActivity(intent)
            }
        })

    }

    private fun showError() {
        Toast.makeText(this, "Connection failure", Toast.LENGTH_SHORT).show()
    }
}

