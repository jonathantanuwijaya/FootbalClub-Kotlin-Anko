package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val idPlayer: String?,
    @SerializedName("strNationality")
    val nationality: String?,
    @SerializedName("strPlayer")
    val name: String?,
    @SerializedName("strTeam")
    val team: String?,
    val dateBorn: String?,
    val dateSigned: String?,
    @SerializedName("strWage")
    val wage: String?,
    @SerializedName("strBirthLocation")
    val birthLocation: String?,
    @SerializedName("strDescriptionEN")
    val description: String?,
    @SerializedName("strGender")
    val gender: String?,
    @SerializedName("strPosition")
    val position: String?,
    @SerializedName("strHeight")
    val height: String?,
    @SerializedName("strWeight")
    val weight: String?,
    @SerializedName("strCutout")
    val photo: String?
): Parcelable

data class PlayersResponse(
    val player: MutableList<Player>?
)

data class PlayerResponse(
    val players: MutableList<Player>?
)