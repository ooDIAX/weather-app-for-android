package com.harbourspace.unsplash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

class MainActivity : ComponentActivity() {


  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    setContent {
      UnsplashTheme {

          val context = LocalContext.current
          val cities = arrayOf("Bangkok", "Lisbon", "Tbilisi", "Zurich", "London", "Tokyo", "New York")
          var expanded by remember { mutableStateOf(false) }

          var selectedText by remember { mutableStateOf(cities[0]) }

          Column (
              modifier = Modifier
                  .fillMaxSize()
                  .background(Color(54, 50, 50)),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
          ){
              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(10.dp),
                  contentAlignment = Alignment.Center
              ) {
                  ExposedDropdownMenuBox(
                      expanded = expanded,
                      onExpandedChange = {
                          expanded = !expanded
                      }
                  ) {
                      TextField(
                          value = selectedText,
                          onValueChange = {},
                          readOnly = true,
                          trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                          modifier = Modifier.menuAnchor()
                      )

                      ExposedDropdownMenu(
                          expanded = expanded,
                          onDismissRequest = { expanded = false }
                      ) {
                          cities.forEach { item ->
                              DropdownMenuItem(
                                  text = { Text(text = item) },
                                  onClick = {
                                      selectedText = item
                                      expanded = false
                                      Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                  }
                              )
                          }
                      }
                  }
              }

              val buttonColor = ButtonDefaults.buttonColors(
                  containerColor = Color(87,87,87)
              )

              Button(

                  onClick = {
                  val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                  intent.putExtra("city", selectedText)
                  startActivity(intent)
                  },
                  colors = buttonColor

              ) {
                  Text("Show Weather")
              }
          }



      }
    }

  }
}

