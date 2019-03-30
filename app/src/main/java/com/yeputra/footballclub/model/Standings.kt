package com.yeputra.footballclub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standing(
    val name: String?,
    val teamid: String?,
    val played: Int?,
    val goalsfor: Int?,
    val goalsagainst: Int?,
    val goalsdifference: Int?,
    val win: Int?,
    val draw: Int?,
    val loss: Int?,
    val total: Int?
): Parcelable

data class StandingsResponse(
    val table: MutableList<Standing>?
)