package ca.danielpeng.shopifychallenge.view

import android.support.annotation.StringRes

interface CollectionsView {
    fun showError(@StringRes errorMessage: Int)
}