package com.harbourspace.unsplash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

class MainActivity : ComponentActivity() {

  private val unsplashViewModel: UnsplashViewModel by viewModels()

  @SuppressLint("SuspiciousIndentation")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    unsplashViewModel.fetchImages()
    unsplashViewModel.fetchCollections()


    setContent {
      UnsplashTheme {

        val unsplashImages = unsplashViewModel.items.observeAsState(emptyList())

        LazyColumn {
          items(unsplashImages.value) { image ->
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                  val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                  intent.putExtra("ID", image.id)
                  startActivity(intent)
                }
            ) {
              Text(
                color = Color.White,
                text = image.id,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
              )

              val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                  .data(image.urls.regular)
                  .build()
              )

              Surface {

                Image(
                  painter = painter,
                  contentDescription = stringResource(id = R.string.description_image_preview),
                  contentScale = ContentScale.Crop,
                  modifier = Modifier.fillMaxSize()
                )
              }

              Column(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
              ) {
                Text(text = image.description ?: "")

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = image.user.name)
              }
            }

          }
        }
      }
    }

  }
}