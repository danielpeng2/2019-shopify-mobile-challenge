package ca.danielpeng.shopifychallenge.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.danielpeng.shopifychallenge.R
import ca.danielpeng.shopifychallenge.presenter.CollectionsPresenter

class CollectionsActivity : AppCompatActivity(), CollectionsView {

    private lateinit var presenter: CollectionsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        setTitle(R.string.title_collections)

        presenter = CollectionsPresenter(this)
        presenter.onCreate()
    }
}
