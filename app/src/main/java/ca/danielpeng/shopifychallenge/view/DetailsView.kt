package ca.danielpeng.shopifychallenge.view

import android.support.annotation.StringRes

interface DetailsView {

    fun showError(@StringRes errorMessage: Int)
}