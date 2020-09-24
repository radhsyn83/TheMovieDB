package com.radhsyn83.themoviedb.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.utils.Tools
import org.jetbrains.anko.sdk27.coroutines.onClick

//
// Created by Fathur Radhy 
// on June 2019-06-06.
//

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        Tools.customStatusBar(this, false)
        super.onCreate(savedInstanceState)
        setContentView(setLayoutResource())

        //Back Btn
        findViewById<ImageView>(R.id.iv_back)?.onClick {
            finish()
        }

        onViewReady()
    }

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }

    protected abstract fun setLayoutResource(): Int
    protected abstract fun onViewReady()

}