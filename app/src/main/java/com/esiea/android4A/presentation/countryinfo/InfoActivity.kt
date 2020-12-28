package com.esiea.android4A.presentation.countryinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.esiea.android4A.Constants
import com.esiea.android4A.R
import com.esiea.android4A.Singletons
import com.esiea.android4A.domain.entity.Rapper
//import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import org.koin.android.ext.android.inject

@SuppressLint("Registered")
class InfoActivity : AppCompatActivity() {

    private val infoViewModel : InfoViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val intent = intent
        val countryJson = intent.getStringExtra(Constants.KEY_Country_List)
        val rapper = Singletons.gson!!.fromJson(countryJson, Rapper::class.java)

        infoViewModel.info(rapper)

        infoViewModel.rapperName.observe(this, Observer {
                value -> rapperName.text = value
        })

        infoViewModel.realName.observe(this, Observer {
                value -> realName.text = value
        })

        infoViewModel.bio.observe(this, Observer {
                value -> bio.text = value.toString()
        })



        backButton.setOnClickListener {
            finish()
        }
    }
}
