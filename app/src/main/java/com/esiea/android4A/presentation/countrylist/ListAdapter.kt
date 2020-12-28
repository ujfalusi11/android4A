package com.esiea.android4A.presentation.countrylist

import com.squareup.picasso.Picasso
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esiea.android4A.R
import com.esiea.android4A.domain.entity.Rapper
import kotlinx.android.synthetic.main.activity_row.view.*

class ListAdapter(
    private val rappers:List<Rapper>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imageView: ImageView = itemView.img
        val textName : TextView = itemView.firstLine
        val textRealName: TextView = itemView.secondLine

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_row, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = rappers[position]
        holder.textName.text = currentItem.rapperName
        holder.textRealName.text = ("TOP ALBUM - "+ currentItem.topAlbum)
        Picasso.get().load(currentItem.url).fit().into(holder.imageView)
    }

    override fun getItemCount() = rappers.size
}
