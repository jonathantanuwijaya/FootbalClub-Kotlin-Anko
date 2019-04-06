package com.yeputra.footballclub.ui.team

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yeputra.footballclub.R
import com.yeputra.footballclub.model.Player
import com.yeputra.footballclub.utils.DateUtils
import com.yeputra.footballclub.utils.INTENT_DATA
import kotlinx.android.synthetic.main.bsheet_detail_players.*
class PlayerDetailFm : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.bsheet_detail_players, container, false)

    override fun onResume() {
        super.onResume()
        initContentView()
    }

    @SuppressLint("SetTextI18n")
    fun initContentView(){
        arguments?.let {
            val player = it.getParcelable<Player>(INTENT_DATA)

            context?.let { it1 ->
                Glide.with(it1)
                    .load(player?.photo)
                    .apply(RequestOptions().placeholder(R.drawable.ic_player))
                    .apply(RequestOptions.circleCropTransform())
                    .into(img_photo)
            }

            tv_player_name.text = player?.name
            tv_nationality.text = player?.nationality

            val location = player?.birthLocation?.split(",")?.get(0)
            val dateBorn = DateUtils.parser(player?.dateBorn, "yyyy-MM-dd", "dd-MMM-yyyy")
            tv_birthdate_location.text = "$location, $dateBorn"
            tv_gender.text = player?.gender

            tv_height.text = "H: " + player?.height
            tv_wage.text = formatWidthOrWeight(player?.wage, player?.weight)


            tv_team_name.text = player?.team
            tv_posisiton.text = player?.position
            tv_date_signed.text = DateUtils.parser(player?.dateSigned, "yyyy-MM-dd", "dd-MMM-yyyy")

            tv_desc.text = player?.description
        }
    }

    private fun formatWidthOrWeight(wage: String?, weight: String?): String =
        wage?.let {
            if(!it.isEmpty())
                "W: $it"
            else
                "W: $weight"
        }?: run {
            "W: $weight"
        }
}

