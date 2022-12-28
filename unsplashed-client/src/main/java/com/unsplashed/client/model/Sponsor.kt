package com.unsplashed.client.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsor(
    @SerialName("accepted_tos")
    val acceptedTos: Boolean,
    val bio: String? = null,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("for_hire")
    val forHire: Boolean,
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    val links: Links,
    val location: String? = null,
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("profile_image")
    val profileImage: ProfileImage,
    val social: Social,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("updated_at")
    val updatedAt: String,
    val username: String
)