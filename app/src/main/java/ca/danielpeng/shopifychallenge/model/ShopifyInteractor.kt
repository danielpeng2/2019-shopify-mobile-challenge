package ca.danielpeng.shopifychallenge.model

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object ShopifyInteractor: ShopifyInteractable {

    // CONSTANTS
    private const val URL_COLLECTIONS =
        "https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6"
    private const val TAG_ID = "id"
    private const val TAG_TITLE = "title"
    private const val TAG_CUSTOM_COLLECTIONS = "custom_collections"

    private val client = OkHttpClient()

    override fun getCollections(responseListener: ResponseListener<ArrayList<Collection>>) {
        val request = Request.Builder()
            .url(URL_COLLECTIONS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseListener.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val collections = arrayListOf<Collection>()

                val jsonResponse = JSONObject(response.body()?.string())
                val jsonArray = jsonResponse.getJSONArray(TAG_CUSTOM_COLLECTIONS)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    collections.add(
                        Collection(item.getString(TAG_ID), item.getString(TAG_TITLE))
                    )
                }

                responseListener.onResponse(collections)
            }
        })
    }
}