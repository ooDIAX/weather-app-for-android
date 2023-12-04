package com.harbourspace.unsplash

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getResourceId
import com.harbourspace.unsplash.ui.theme.UnsplashTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DetailsActivity : ComponentActivity() {

  private val visualcrossingViewModel: VisualCrossingViewModel by viewModels()

  private val showIndex = mutableIntStateOf(0)
  private val icons: MutableList<String> = mutableListOf()

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val dateIn7Days = currentDate.plusDays(7)
    val formattedDateIn7Days = dateIn7Days.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val dayList = mutableListOf<String>()
    val iconList = mutableListOf<String>()

    fillicons()

    val city = intent.getStringExtra("city")
    Log.d("DetailsActivity", "Received value: city")

    visualcrossingViewModel.fetchVisualCrossingData(
      city.toString(),
      formattedDate,
      formattedDateIn7Days
    )

    for (i in 0 until 7) {
      val date = currentDate.plusDays(i.toLong())
      val dayOfMonth = date.format(DateTimeFormatter.ofPattern("yyy-MM-dd"))
      dayList.add(dayOfMonth)
//      icons[i] = visualcrossingItem.value?.days?.get(showIndex.value)?.icon ?: "fog"
    }


    setContent {
      UnsplashTheme {
//          val visualcrossingItem = visualcrossingViewModel.visualCrossingItem.observeAsState()


        val visualcrossingItem = visualcrossingViewModel.visualCrossingItem.observeAsState()

        for (i in 0 until 7) {
          icons[i] = visualcrossingItem.value?.days?.get(i)?.icon ?: "rain_snow_showers_day"
        }

        Column(
          modifier = Modifier
            .fillMaxSize()
            .background(Color(54, 50, 50))
        ) {

          RowOfItems()

          Column(
            modifier = Modifier
              .padding(16.dp)
              .fillMaxWidth()
              .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {

            Row(
              modifier = Modifier
                .padding(horizontal = 20.dp)
            ) {
              Text(
                color = Color.White,
                text = city ?: "City",
                fontSize = 36.sp,
                modifier = Modifier
                  .weight(1f)
              )

//              visualcrossingItem.value?.description ?:
              Text(
                color = Color.White,
                text = dayList[showIndex.value],
                fontSize = 29.sp,
                modifier = Modifier
                  .weight(1f)
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            val deg = visualcrossingItem.value?.days?.get(showIndex.value)?.temp.toString() ?: "90"
            Text(
              color = Color.White,
              text = "$deg °F",
              fontSize = 50.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))

            val icon = icons[showIndex.value]
            Image(
              painter = painterResource(id =
              when(icon) {
                "rain_snow" -> R.drawable.rain_snow
                "cloudy" -> R.drawable.cloudy
                "hail" -> R.drawable.hail
                "partly_cloudy_day" -> R.drawable.partly_cloudy_day
                "rain" -> R.drawable.rain
                "rain_snow_showers_day" -> R.drawable.rain_snow_showers_day
                "rain_snow_showers_night" -> R.drawable.rain_snow_showers_night
                "sleet" -> R.drawable.sleet
                "snow" -> R.drawable.snow
                "snow_showers_day" -> R.drawable.snow_showers_day
                "snow_showers_night" -> R.drawable.snow_showers_night
                "thunder" -> R.drawable.thunder
                "thunder_rain" -> R.drawable.thunder_rain
                "thunder_showers_day" -> R.drawable.thunder_showers_day
                "wind" -> R.drawable.wind
                else -> R.drawable.fog

              }
              ),
              contentDescription = null,
              modifier = Modifier.size(200.dp)
            )
          }


          Column(
            modifier = Modifier
              .padding(16.dp)
              .fillMaxWidth()
              .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 16.dp)
                .background(shape = RoundedCornerShape(16.dp), color = Color(87, 87, 87)),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {

              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                weatherDetails(
                  "Max temp",
                  visualcrossingItem.value?.days?.get(showIndex.value)?.tempmax.toString() ?: "90"
                )
              }

              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                weatherDetails(
                  "Min temp",
                  visualcrossingItem.value?.days?.get(showIndex.value)?.tempmin.toString() ?: "-12"
                )
              }
            }

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 16.dp)
                .background(shape = RoundedCornerShape(16.dp), color = Color(87, 87, 87)),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {

              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                weatherDetails(
                  "feelslikemax",
                  visualcrossingItem.value?.days?.get(showIndex.value)?.feelslikemax.toString()
                    ?: "90"
                )
              }

              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                weatherDetails(
                  "feelslikemax",
                  visualcrossingItem.value?.days?.get(showIndex.value)?.feelslikemin.toString()
                    ?: "-90"
                )
              }

            }

          }

        }
      }

    }
  }


  @Composable
  fun weatherDetails(header : String, value : String){
    Column(
    ){

      Text(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .padding(16.dp),
        color = Color.White,
        text = header,
        fontSize = 25.sp,
        textAlign = TextAlign.Center
      )

      Text(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .padding(16.dp),
        color = Color.White,
        text = value,
        fontSize = 22.sp,
        textAlign = TextAlign.Center
      )
    }
  }

  @Composable
  fun RowOfItems() {
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      items(7) { index ->
        ItemWithSVGAndText(index)
      }
    }
  }

  @SuppressLint("RestrictedApi")
  @Composable
  fun ItemWithSVGAndText(index: Int) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(8.dp)
        .border(
          BorderStroke(1.dp, Color.Gray),
          shape = MaterialTheme.shapes.medium
        )
        .padding(8.dp)
        .clickable {
          showIndex.value = index
        }

    ) {
      val icon : String = icons[index]
      Image(

        painter = painterResource(id =
          when(icon) {
            "rain_snow" -> R.drawable.rain_snow
            "cloudy" -> R.drawable.cloudy
            "hail" -> R.drawable.hail
            "partly_cloudy_day" -> R.drawable.partly_cloudy_day
            "rain" -> R.drawable.rain
            "rain_snow_showers_day" -> R.drawable.rain_snow_showers_day
            "rain_snow_showers_night" -> R.drawable.rain_snow_showers_night
            "sleet" -> R.drawable.sleet
            "snow" -> R.drawable.snow
            "snow_showers_day" -> R.drawable.snow_showers_day
            "snow_showers_night" -> R.drawable.snow_showers_night
            "thunder" -> R.drawable.thunder
            "thunder_rain" -> R.drawable.thunder_rain
            "thunder_showers_day" -> R.drawable.thunder_showers_day
            "wind" -> R.drawable.wind
            else -> R.drawable.fog

          }),
        contentDescription = null,
        modifier = Modifier.size(48.dp),
        colorFilter = if(index == showIndex.value) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black)
      )

      Text(
        text = "Day ${index + 1}",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp),
        color = if(index == showIndex.value) Color.White else Color.Black

      )
    }
  }

  fun fillicons(){
    repeat(7) {
      icons.add("fog")
    }
  }


}
