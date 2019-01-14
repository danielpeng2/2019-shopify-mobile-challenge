package ca.danielpeng.shopifychallenge.model

import java.io.Serializable

data class Collection(val id: String,
                      val name: String,
                      val imageUrl: String,
                      val bodyText: String) : Serializable
