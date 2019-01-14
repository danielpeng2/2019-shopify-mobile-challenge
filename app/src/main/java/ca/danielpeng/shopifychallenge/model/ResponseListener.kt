package ca.danielpeng.shopifychallenge.model

import java.lang.Exception

interface ResponseListener<T> {
    fun onResponse(results: T)
    fun onFailure(e: Exception)
}