package com.yeputra.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.yeputra.footballclub.utils.DateUtils
import kotlinx.android.parcel.Parcelize


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:16
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
@Parcelize
data class Event(
    val idEvent: String?,
    @SerializedName("strHomeTeam")
    val homeTeam: String?,
    @SerializedName("strAwayTeam")
    val awayTeam: String?,
    val dateEvent: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strHomeGoalDetails")
    val homeGoalDetail: String?,
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetail: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineupGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    val homeLineupDefense: String?,
    @SerializedName("strAwayLineupDefense")
    val awayLineupDefense: String?,

    @SerializedName("strHomeLineupMidfield")
    val homeLineupMidfield: String?,
    @SerializedName("strAwayLineupMidfield")
    val awayLineupMidfield: String?,

    @SerializedName("strHomeLineupForward")
    val homeLineupForward: String?,
    @SerializedName("strAwayLineupForward")
    val awayLineupForward: String?,

    @SerializedName("strHomeLineupSubstitutes")
    val homeLineupSubstitutes: String?,
    @SerializedName("strAwayLineupSubstitutes")
    val awayLineupSubstitutes: String?,

    @SerializedName("strHomeYellowCards")
    val homeYellowCards: String?,
    @SerializedName("strAwayYellowCards")
    val awayYellowCards: String?,

    @SerializedName("strHomeRedCards")
    val homeRedCards: String?,
    @SerializedName("strAwayRedCards")
    val awayRedCards: String?,

    @SerializedName("strThumb")
    val tumb: String?
): Parcelable {
    fun getFormatDateEvent(): String =
        DateUtils.format(DateUtils.parser(dateEvent, "yyyy-MM-dd"),"EE, dd-MMM-yyyy")
}


data class EventsResponse(
    val events: MutableList<Event>?
)

data class SearchMatchResponse(
    val event: MutableList<Event>?
)
