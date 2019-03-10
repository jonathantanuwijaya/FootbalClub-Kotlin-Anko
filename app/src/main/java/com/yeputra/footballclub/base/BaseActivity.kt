package com.yeputra.footballclub.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.yeputra.footballclub.R


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:56
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity<presenter: IBasePresenter>
    : AppCompatActivity(), IBaseView {

    @SuppressLint("ResourceType")
    private lateinit var builder: AlertDialog.Builder

    protected lateinit var presenter: presenter

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = initPresenter()

        builder = AlertDialog.Builder(this)
        val v = LayoutInflater.from(this).inflate(R.layout.loading,null)
        builder.setView(v)
    }

    abstract fun initPresenter(): presenter

    @SuppressLint("ResourceType")
    override fun showProgressbar() {
        builder.show()
    }

    override fun hideProgressbar() {
        builder.create().hide()
    }

    override fun onPresenterSuccess(data: Any?) {}

    override fun onPresenterFailed(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}