package com.radhsyn83.themoviedb.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.github.ybq.android.spinkit.SpinKitView
import com.radhsyn83.themoviedb.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

//
// Created by Fathur Radhy
// on May 2019-05-26.
//
object Tools {

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun dateTimeFormatNormal(time: String): String {
        var dateTime: Date? = null
        try {
            dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return SimpleDateFormat("dd/MM/yyyy").format(dateTime) + ", " + SimpleDateFormat("HH:mm").format(
            dateTime
        ) + " WIB"
    }

    fun dateFormat(time: String): String {
        var dateTime: Date? = null
        var dateTime2: Date? = null
        try {
            dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar = Calendar.getInstance()
        calendar.time = dateTime

        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)

        try {
            dateTime2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar2 = Calendar.getInstance()
        calendar2.time = dateTime2

        return if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(
                Calendar.DAY_OF_YEAR
            )
        ) {
            SimpleDateFormat("HH:mm").format(dateTime)
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(
                Calendar.DAY_OF_YEAR
            ) == yesterday.get(
                Calendar.DAY_OF_YEAR
            )
        ) {
            "Kemarin, " + SimpleDateFormat("HH:mm").format(dateTime)
        } else {
            SimpleDateFormat("dd/MM/yyyy").format(dateTime) + ", " + SimpleDateFormat("HH:mm").format(
                dateTime
            )
        }
    }

    fun getTimeStringFromDate(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.US)
        return dateFormat.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(time: String): String {
        var dateTime: Date? = null
        try {
            dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        var date = "-"

        try {
           date = SimpleDateFormat("dd/MM/yyyy").format(dateTime)
        } catch (e: NullPointerException) {
        }

        return date
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(time: String): String {
        var dateTime: Date? = null
        try {
            dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return SimpleDateFormat("HH:mm").format(dateTime)
    }

    fun datePicker(mContext: Context, onGetDate: (String) -> Unit) {

        var cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yyyy-MM-dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                onGetDate(sdf.format(cal.time))
            }

        DatePickerDialog(
            mContext, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        return if (cursor == null) {
            contentURI.path.toString()
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }

    fun customStatusBar(
        activity: Activity,
        isTransparent: Boolean,
        color: Int = Color.WHITE,
        lightStatusBar: Boolean = false
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isTransparent) {
                activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                activity.window.statusBarColor = Color.TRANSPARENT
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                activity.window.statusBarColor = color
            }
        } else {
            if (isTransparent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimary)
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimary)
                }
            }
        }

        if (lightStatusBar) {
            var flags = activity.window.decorView.systemUiVisibility // get current flag
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                flags =
                    flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } // use XOR here for remove LIGHT_STATUS_BAR from flags
            activity.window.decorView.systemUiVisibility = flags
        }
    }

    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun displayImageFromUrl(img: ImageView, spinKit: SpinKitView?, url: String, errorImage: Int = R.drawable.image_error) {
        if (url == "") {
            img.setImageResource(errorImage)
            spinKit?.visibility = View.GONE
        } else {
            try {
                spinKit?.visibility = View.VISIBLE
                Picasso.get()
                    .load(url)
                    .resize(1000, 500)
                    .onlyScaleDown()
                    .centerInside()
                    .into(img, object : Callback {
                        override fun onSuccess() {
                            if (spinKit != null) spinKit.visibility = View.GONE
                        }

                        override fun onError(e: java.lang.Exception?) {
                            spinKit?.visibility = View.GONE
                            img.setImageResource(errorImage)
                        }
                    })
            } catch (e: Exception) {
            }

        }
    }
}