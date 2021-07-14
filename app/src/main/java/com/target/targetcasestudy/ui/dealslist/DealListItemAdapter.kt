package com.target.targetcasestudy.ui.dealslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.models.Product
import kotlinx.android.synthetic.main.deal_list_item.view.*

class DealItemAdapter(var products: List<Product> = emptyList()) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {

        viewHolder.bind(products[position])

    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product) {
        Picasso.get()
            .load(product.imageUrl)
            .into(itemView.deal_list_item_image_view)

        itemView.deal_list_item_title.text = product.title
        itemView.deal_list_item_price.text = product.regularPrice?.displayString
        itemView.deals_list_aisle.text = product.aisle

        itemView.setOnClickListener { view ->
            val action = DealListFragmentDirections.actionDealsListToDealItem(product)
            view.findNavController().navigate(action)
        }
    }
}