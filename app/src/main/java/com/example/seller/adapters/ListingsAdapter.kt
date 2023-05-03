package com.example.seller.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seller.activities.ObjectActivity
import com.example.seller.R
import com.example.seller.models.SelectedObjectData
import com.example.seller.models.results
import com.squareup.picasso.Picasso

class ListingsAdapter : RecyclerView.Adapter<ListingsAdapter.MyViewHolder>() {

    private var items = ArrayList<results>()


    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedItems(items: ArrayList<results>, orderBy: Int) {

        this.items = items

        if (orderBy == 1) {
            items.sortWith(compareBy { it.price.split(" ")[1].toInt() })
            notifyDataSetChanged()

        } else if (orderBy == 2) {
            items.sortWith(compareByDescending { it.price.split(" ")[1].toInt() })
            notifyDataSetChanged()

        } else if (orderBy == 3) {
            items.sortWith(
                compareBy(
                    { it.created.split("-")[0].toInt() },
                    { it.created.split("-")[1].toInt() },
                    { it.created.split("-")[2].split(" ")[0].toInt() })
            )
            items.reverse()
            notifyDataSetChanged()

        } else if (orderBy == 4) {
            items.sortWith(
                compareBy(
                    { it.created.split("-")[0].toInt() },
                    { it.created.split("-")[1].toInt() },
                    { it.created.split("-")[2].split(" ")[0].toInt() })
            )
            notifyDataSetChanged()

        }

        notifyDataSetChanged()

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val objectName = view.findViewById<TextView>(R.id.tvName)
        val objectPrice = view.findViewById<TextView>(R.id.tvPrice)
        val objectImage = view.findViewById<ImageView>(R.id.ivObject)
        val objectDate = view.findViewById<TextView>(R.id.tvDate)

        fun bind(data: results) {
            objectName.text = data.name.replaceFirstChar { it.uppercase() }
            objectPrice.text = data.price
            objectDate.text = data.created.split(" ")[0]
            val imageUrl = data.imageUrlThumbs.get(0)

            Picasso.get()
                .load(imageUrl)
                .into(objectImage)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ObjectActivity::class.java)

            SelectedObjectData.objectData = items.get(holder.adapterPosition)



            it.context.startActivity(intent)

        }

    }
}