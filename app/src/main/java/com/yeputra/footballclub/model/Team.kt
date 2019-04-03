package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    val idTeam: String?,
    @SerializedName("strTeam")
    val name: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strTeamBadge")
    val logo: String?,
    @SerializedName("strStadiumLocation")
    val stadium: String?,
    @SerializedName("strWebsite")
    val website: String?
) : Parcelable

data class TeamsResponse (
    val teams: MutableList<Team>?
)