package com.miu.edu.sports.news.information.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.miu.edu.sports.news.information.R
import com.mobsandgeeks.saripaar.ValidationError
import java.util.Calendar


fun Activity.onErrorTextFieldValidator(errors: MutableList<ValidationError>?) {
    errors?.let {
        for (error in errors) {
            val view = error.view
            val message = error.getCollatedErrorMessage(this)

            if (view is AppCompatEditText) {
                view.error = message
            } else {
                showToast(message)
            }
        }
    }
}


var alertDialog: AlertDialog? = null

fun Activity.showAlert(
    message: String, positiveString: String?,
    negativeString: String?, onPositiveBtnClicked: (() -> Unit)?,
    onNegativeBtnClicked: (() -> Unit)?
) {
    alertDialog?.dismiss()
    val alertDialogBuilder = MaterialAlertDialogBuilder(this, R.style.MyAlertDialogTheme)
    alertDialogBuilder.setCancelable(false)
    alertDialogBuilder.setIcon(R.mipmap.ic_launcher_round)
    alertDialogBuilder.setTitle(R.string.app_name)
    alertDialogBuilder.setMessage(message)

    if (positiveString != null)
        alertDialogBuilder.setPositiveButton(positiveString) { dialog, _ ->
            onPositiveBtnClicked?.invoke()
            dialog.dismiss()
        }
    if (negativeString != null)
        alertDialogBuilder.setNegativeButton(negativeString) { dialog, _ ->
            onNegativeBtnClicked?.invoke()
            dialog.dismiss()
        }
    alertDialog = alertDialogBuilder.show()
}

fun Activity.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
private var progressDialog: AlertDialog? = null
private var dialogDisplayedTimeInMillis: Long = 0
private const val preferredDelayInMillis: Long = 750
fun Activity.showProgressDialog() {
    // Check if the activity is finishing or has already finished
    if (isFinishing || isDestroyed) {
        return
    }
    progressDialog?.let {
        if (it.isShowing) {
            it.dismiss()
        }
    }

    val view = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null)
    progressDialog = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(false)
        .create()

    progressDialog?.show()?.also {
        dialogDisplayedTimeInMillis = Calendar.getInstance().timeInMillis
    }

    val displayRectangle = Rect()
    progressDialog?.window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)
    progressDialog?.window?.apply {
        setLayout(
            (displayRectangle.width() * 0.50).toInt(), LinearLayout.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawableResource(android.R.color.transparent)
    }
}

fun Activity.makeStatusColor(){
    window.statusBarColor = ContextCompat.getColor(this,R.color.purple_500)
}
fun hideProgressDialog() {
    val timeElapsed = Calendar.getInstance().timeInMillis - dialogDisplayedTimeInMillis
    if (timeElapsed > preferredDelayInMillis) {
        progressDialog?.dismiss()
    } else {
        Handler(Looper.getMainLooper()).postDelayed({
            progressDialog?.dismiss()
        }, preferredDelayInMillis - timeElapsed)
    }
}

fun FragmentActivity.datePickerDialogSetListener(
    calendar: Calendar,
    dateSetListener: DatePickerDialog.OnDateSetListener
): Calendar {
    DatePickerDialog(
        this,
        dateSetListener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
    return calendar
}
