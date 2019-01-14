package ca.danielpeng.shopifychallenge.model

import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object ShopifyInteractor : ShopifyInteractable {

    // CONSTANTS
    private const val TAG_ID = "id"
    private const val TAG_TITLE = "title"
    private const val TAG_IMAGE = "image"
    private const val TAG_SOURCE = "src"
    private const val TAG_CUSTOM_COLLECTIONS = "custom_collections"
    private const val TAG_COLLECTS = "collects"
    private const val TAG_PRODUCT_ID = "product_id"
    private const val TAG_PRODUCTS = "products"
    private const val TAG_VARIANTS = "variants"
    private const val TAG_INVENTORY_QUANTITY = "inventory_quantity"
    private const val TAG_BODY_HTML = "body_html"

    private val client = OkHttpClient()

    override fun getCollections(responseListener: ResponseListener<ArrayList<Collection>>) {
        val request = Request.Builder()
            .url("https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseListener.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val collections = arrayListOf<Collection>()

                // convert the response to a list of Collection objects
                val jsonResponse = JSONObject(response.body()?.string())
                val jsonArray = jsonResponse.getJSONArray(TAG_CUSTOM_COLLECTIONS)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    collections.add(
                        Collection(
                            item.getString(TAG_ID),
                            item.getString(TAG_TITLE),
                            item.getJSONObject(TAG_IMAGE).getString(TAG_SOURCE),
                            item.getString(TAG_BODY_HTML)
                        )
                    )
                }

                responseListener.onResponse(collections)
            }
        })
    }

    override fun getProducts(collectionId: String, responseListener: ResponseListener<ArrayList<Product>>) {
        val collectRequest = Request.Builder()
            .url("https://shopicruit.myshopify.com/admin/collects.json?collection_id=" +
                    collectionId + "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
            .build()

        // fetch the list of collects for the collectionId
        client.newCall(collectRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseListener.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val productIds = arrayListOf<String>()

                // convert the response to a list of productIds
                val collectResponse = JSONObject(response.body()?.string())
                val collectArray = collectResponse.getJSONArray(TAG_COLLECTS)
                for (i in 0 until collectArray.length()) {
                    val collectItem = collectArray.getJSONObject(i)
                    productIds.add(collectItem.getString(TAG_PRODUCT_ID))
                }

                // join productIds in string delimited by ","
                val productIdString = productIds.joinToString(separator = ",")


                val productRequest = Request.Builder()
                    .url("https://shopicruit.myshopify.com/admin/products.json?ids=" +
                            productIdString + "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
                    .build()

                // use the productIds to fetch a list of products
                client.newCall(productRequest).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        responseListener.onFailure(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val products = arrayListOf<Product>()

                        // convert the response into a list of Product objects
                        val productResponse = JSONObject(response.body()?.string())
                        val productArray = productResponse.getJSONArray(TAG_PRODUCTS)
                        for (i in 0 until productArray.length()) {
                            val productItem = productArray.getJSONObject(i)
                            var totalInventory = 0

                            // get the available inventory across all variants
                            val variantArray = productItem.getJSONArray(TAG_VARIANTS)
                            for (j in 0 until variantArray.length()) {
                                val variantItem = variantArray.getJSONObject(j)
                                totalInventory += variantItem.getInt(TAG_INVENTORY_QUANTITY)
                            }

                            products.add(
                                Product(
                                    productItem.getString(TAG_TITLE),
                                    totalInventory
                                )
                            )
                        }

                        responseListener.onResponse(products)
                    }
                })
            }
        })
    }
}