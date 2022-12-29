package com.example.workshop

import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.workshop.home.HomeViewModel
import com.example.workshop.home.ui.PhotoCard
import com.example.workshop.home.ui.UserInformation
import com.unsplashed.client.UnsplashedClient
import com.unsplashed.client.model.Links
import com.unsplashed.client.model.Photo
import com.unsplashed.client.model.ProfileImage
import com.unsplashed.client.model.Social
import com.unsplashed.client.model.Urls
import com.unsplashed.client.model.User
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val fakeUser = User(
        username = "Test",
        acceptedTos = true,
        firstName = "test",
        forHire = false,
        id = "asdf",
        links = Links(),
        name = "Test",
        profileImage = ProfileImage("", "", ""),
        social = Social(null, null, null, null),
        totalCollections = 0,
        updatedAt = "",
        totalLikes = 0,
        totalPhotos = 0,
        portfolioUrl = null
    )

    val fakePhoto = Photo(
        altDescription = null,
        blurHash = "",
        color = "",
        createdAt = "",
        description = null,
        height = 100,
        id = "",
        likedByUser = false,
        likes = 0,
        links = Links(),
        promotedAt = null,
        sponsorship = null,
        updatedAt = "",
        urls = Urls("", "", "", "", "", ""),
        user = fakeUser,
        width = 200
    )

    @Test
    fun cardDisplaysUserName() {
        composeTestRule.setContent {
            UserInformation(user = fakeUser)
        }

        composeTestRule
            .onNode(hasText("Test"))
            .assertIsDisplayed()
    }


    @Test
    fun cardIsClickable() {

        var clicked = false

        val photo: Photo = mockk(relaxed = true) {
            every { user } returns mockk(relaxed = true) {
                every { name } returns "Test"
            }
            every { height } returns 200
            every { width } returns 100
        }


        val clicker: () -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            PhotoCard(photo = photo, onClick = clicker)
        }


        composeTestRule
            .onNode(hasText("Test"))
            .performClick()

        verify {
            clicker.invoke()
        }

    }

    @Test
    fun cardContainsImage() {

        composeTestRule.setContent {
            PhotoCard(photo = fakePhoto, onClick = {})
        }

        composeTestRule
            .onNode(hasTestTag("userInfo:image"), useUnmergedTree = true)
            .assertIsDisplayed()
            .assertWidthIsEqualTo(48.dp)
            .assertHeightIsEqualTo(48.dp)


    }


}