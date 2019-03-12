package com.yeputra.footballclub.repository.database

import android.content.Context


val Context.database: FavoriteDatabase
    get() = FavoriteDatabase.getInstance(this)
