package com.example.dscatalogmobile.gui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dscatalogmobile.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("DS Catalog")


    }
}