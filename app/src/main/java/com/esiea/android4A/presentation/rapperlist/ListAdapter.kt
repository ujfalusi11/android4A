package com.esiea.android4A.presentation.rapperlist

import com.squareup.picasso.Picasso
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.esiea.android4A.R
import com.esiea.android4A.domain.entity.Rapper

class ListAdapter(
    rappers:List<Rapper>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    private val values = rappers

    inner class ViewHolder(
        layout: View
    ): RecyclerView.ViewHolder(layout), View.OnClickListener {
        var txtHeader: TextView = layout.findViewById<View>(R.id.firstLine) as TextView
        var txtFooter: TextView = layout.findViewById<View>(R.id.secondLine) as TextView
        var image = itemView.findViewById(R.id.img) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    @NonNull
    @Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.activity_row, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRapper: Rapper = values.get(position)
        holder.txtHeader.text = currentRapper.rapperName
        holder.txtFooter.text = "Top album: " + currentRapper.topAlbum
        Picasso.get().load(currentRapper.url).fit().into(holder.image)
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
