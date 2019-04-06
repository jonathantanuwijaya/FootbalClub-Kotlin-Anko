package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String?,
    @SerializedName("strLeague")
    val name: String?,
    @SerializedName("strSport")
    val sport: String?,

    @SerializedName("intFormedYear")
    val formedYear: String?,
    @SerializedName("dateFirstEvent")
    val dateFirstEvent: String?,
    @SerializedName("strCountry")
    val country: String?,
    @SerializedName("strWebsite")
    val website: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strBadge")
    val logo: String?
): Parcelable

@Parcelize
data class LeaguesResponse(
    val leagues: MutableList<League>?
): Parcelable