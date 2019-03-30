package com.yeputra.footballclub.model

import com.google.gson.annotations.SerializedName

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
)

data class TeamsResponse (
    val teams: MutableList<Team>?
)