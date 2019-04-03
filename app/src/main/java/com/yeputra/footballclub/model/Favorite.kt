package com.yeputra.footballclub.model

const val DB_FAVORITE = "DATABASE_FAVORITE"

data class MatchFavorite(
    val eventId: String?,
    val eventDate: String?,
    val homeName: String?,
    val homeScore: String?,
    val awayName: String?,
    val awayScore: String?
) {

    companion object {
        const val MATCH_FAVORITE: String = "MATCH_FAVORITE"
        const val EVENT_ID: String = "EVENT_ID_"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}

data class TeamFavorite(
    val teamId: String?,
    val teamName: String?,
    val teamLogo: String?
){
    companion object {
        const val TEAM_FAVORITE: String = "TEAM_FAVORITE"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_LOGO: String = "TEAM_LOGO"
    }
}

