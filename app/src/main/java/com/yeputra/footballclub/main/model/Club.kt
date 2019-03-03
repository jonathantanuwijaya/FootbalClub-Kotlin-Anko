package com.yeputra.footballclub.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize


/**
 * Created by yovi.putra
 *    on 03/Mar/2019 17:16
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
@Parcelize
data class Club(val icon: Int, val name: String?, val desc: String?): Parcelable