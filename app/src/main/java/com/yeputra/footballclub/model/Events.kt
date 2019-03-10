package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:16
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
@Parcelize
data class Event(
    val idEvent: String?,
    @SerializedName("strEvent")
    val event: String?,
    val dateEvent: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String
): Parcelable

@Parcelize
data class Events(
    val events: MutableList<Event>?
): Parcelable