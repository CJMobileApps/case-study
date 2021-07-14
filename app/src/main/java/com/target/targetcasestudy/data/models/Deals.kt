package com.target.targetcasestudy.data.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
class Deals(
    @JsonProperty("products") val products: List<Product>
): Parcelable

@Parcelize
data class Product(
    @JsonProperty("aisle") val aisle: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("id") val id: Int,
    @JsonProperty("image_url") val imageUrl: String?,
    @JsonProperty("regular_price") val regularPrice: Price?,
    @JsonProperty("sale_price") val salesPrice: Price?,
    @JsonProperty("title") val title: String
): Parcelable

@Parcelize
data class Price(
    @JsonProperty("amount_in_cents") val amountInCents: Int,
    @JsonProperty("currency_symbol") val currencySymbol: String?,
    @JsonProperty("display_string") val displayString: String?
): Parcelable
