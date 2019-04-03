package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standing(
    val name: String?,
    @SerializedName("teamid")
    val teamId: String?,
    val played: Int?,
    @SerializedName("goalsfor")
    val goalsFor: Int?,
    @SerializedName("goalsagainst")
    val goalsAgainst: Int?,
    @SerializedName("goalsdifference")
    val goalsDifference: Int?,
    val win: Int?,
    val draw: Int?,
    val loss: Int?,
    val total: Int?
): Parcelable

data class StandingsResponse(
    val table: MutableList<Standing>?
)