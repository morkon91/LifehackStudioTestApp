package com.app.lifehackstudiotestapp.utils

import android.view.View

fun View.resolveVisibility(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}