package ca.danielpeng.shopifychallenge.presenter

interface Presenter {

    fun onCreate()

    fun onPause()

    fun onResume()

    fun onDestroy()
}