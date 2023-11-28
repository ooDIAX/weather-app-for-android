package com.harbourspace.unsplash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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


class DetailsActivity : ComponentActivity() {

  private val unsplashViewModel: UnsplashViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val id = intent.getStringExtra("id")
    Log.d("DetailsActivity", "Received value: $id")


    setContent {
      UnsplashTheme {
        unsplashViewModel.fetchImages()
        val unsplashImages = unsplashViewModel.items.observeAsState(emptyList())

        unsplashViewModel.fetchjson("oBp-kWAPFnA")
        val json = unsplashViewModel.item.observeAsState()

        LazyColumn (
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
        ){
          item{
            Text(
              color = Color.White,
              text = id.toString(),
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold
            )
          }

//                    Block 1 (image)
          item {
            val painter = rememberAsyncImagePainter(
              model = ImageRequest.Builder(LocalContext.current)
                .data(json.value?.urls?.regular ?: "")
                .build()
            )

            Surface {

              Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.description_image_preview),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .fillMaxWidth()
                  .height(250.dp),
              )
            }
          }
//                  Block 1 end

//                  Block 2
          item{
            Row(
              modifier = Modifier
                .height(82.dp)
                .fillMaxWidth()
                .padding(16.dp)
            ) {
              Row(
                modifier = Modifier.weight(3.0f)
              ) {


                Image(
                  painter = painterResource(id = R.drawable.img3),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .weight(1.0f)
                    .clip(CircleShape)
                    .fillMaxSize()
                )



                // Text and other content
                Row(
                  modifier = Modifier
                    .weight(2.0f)
                    .fillMaxHeight()
                    .padding(8.dp)
                ) {
                  Text(
                    color = Color.White,
                    text = json.value?.user?.instagram_username ?: "" ,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }

              Column(
                modifier = Modifier.weight(3.0f)
              ) {
                Row(
                  modifier = Modifier.weight(3.0f)
                ) {
                  Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                      .weight(1.0f)
                      .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.download),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.description_image_preview)
                  )

                  Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                      .weight(1.0f)
                      .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.favorite),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.description_image_preview)
                  )

                  Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                      .weight(1.0f)
                      .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.bookmark),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.description_image_preview)
                  )
                }
              }
            }
          }
//                  Block 2 end


//                  Block 3
          item {
            Divider(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp),
              thickness = 1.dp,
              color = Color.LightGray
            )
          }
//                  row 1
          item {
            Row(
              modifier = Modifier.padding(16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Camera",
                  subtitle = json.value?.exif?.model ?: ""
                )
              }


              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Aperture",
                  subtitle = json.value?.exif?.aperture ?: ""
                )
              }
            }
          }

//                  row2
          item {
            Row(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Focal Length",
                  subtitle = json.value?.exif?.focal_length ?: ""
                )
              }

              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Shutter Speed",
                  subtitle = json.value?.exif?.exposure_time ?: ""
                )
              }
            }
          }

//                  row3
          item {
            Row(
              modifier = Modifier.padding(16.dp)
            ) {
              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "ISO",
                  subtitle = json.value?.exif?.iso?.toString() ?: ""
                )
              }

              Column(
                modifier = Modifier.weight(1.0f)
              ) {
                AddImageInformation(
                  title = "Dimensions",
                  subtitle = (json.value?.width.toString() ?: "") + " x " + (json.value?.height.toString() ?: "")
                )
              }
            }
          }
//                  Block 3 end

//                  Block 4
//                  line
          item {
            Divider(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp),
              thickness = 1.dp,
              color = Color.LightGray
            )
          }

//                  Info
          item {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              horizontalArrangement = Arrangement.SpaceEvenly
            ) {

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = "Views",
                  subtitle = stringResource(id = R.string.details_views_default)
//                          there is no info for views
                )
              }

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = "Downloads",
                  subtitle = json.value?.downloads?.toString() ?: ""
                )
              }

              Column(
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                AddImageInformation(
                  title = "Likes",
                  subtitle = json.value?.likes?.toString() ?: ""
                )
              }
            }
          }
//                  Block 4 end

//                  Block 5
          item {
            Divider(
              modifier = Modifier.padding(start = 16.dp, end = 16.dp),
              thickness = 1.dp,
              color = Color.LightGray
            )
          }
          item{
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            ){
              Row {
                Box(
                  modifier = Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                    .padding(1.dp)
                ) {
                  Text(color = Color.White, text = json.value?.location?.city ?: "Earth")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                  modifier = Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                    .padding(1.dp)
                ) {
                  Text(color = Color.White, text = json.value?.location?.country ?: "Earth")
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun AddImageInformation(
  title: String,
  subtitle: String
) {

  Text(
    color = Color.White,
    text = title,
    fontSize = 17.sp,
    fontWeight = FontWeight.Bold
  )

  Text(
    color = Color.White,
    text = subtitle,
    fontSize = 15.sp
  )
}