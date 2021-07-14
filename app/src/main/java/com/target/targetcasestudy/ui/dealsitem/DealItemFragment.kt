package com.target.targetcasestudy.ui.dealsitem

import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.DealsActivity
import kotlinx.android.synthetic.main.fragment_deal_item.*


class DealItemFragment : Fragment() {

    private val args: DealItemFragmentArgs by navArgs()
    private val STRIKE_THROUGH_SPAN = StrikethroughSpan()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo add back button for toolbar oops

        Picasso.get()
            .load(args.product.imageUrl)
            .into(dealsItem_image)

        val regPrice = getString(R.string.price_reg, args.product.regularPrice?.displayString)
        dealsItem_regPrice.setText(regPrice, TextView.BufferType.SPANNABLE)
        val spannable = dealsItem_regPrice.text as Spannable
        spannable.setSpan(
            STRIKE_THROUGH_SPAN,
            5,
            regPrice.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val salesPrice = args.product.salesPrice?.displayString
        if(!salesPrice.isNullOrEmpty()) {
            dealsItem_salePrice.text = salesPrice
            dealsItem_salePrice.visibility = View.VISIBLE
        }

        dealsItem_itemTitle.text = args.product.title
        dealsItem_itemDescription.text = args.product.description
    }
}
