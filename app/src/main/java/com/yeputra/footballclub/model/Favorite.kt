package com.yeputra.footballclub.model

data class Favorite(
    val eventId: Long?,
    val eventDate: String?,
    val homeName: String?,
    val awayName: String?,
    val homeScore: String?,
    val awayScore: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val EVENT_ID: String = "EVENT_ID_"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}