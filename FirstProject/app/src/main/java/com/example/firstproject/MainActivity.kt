package com.example.firstproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstproject.ui.theme.FirstProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstProjectTheme {
                LazyColumn (
                    modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                ){
//                    Block 1 (image)
                    item {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            painter = painterResource(id = R.drawable.palace),
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(id = R.string.image_description)
                        )
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
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = null,
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
                                        text = "John Doe",
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
                                        painter = painterResource(id = R.drawable.download_icon),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.download_icon_description)
                                    )

                                    Image(
                                        colorFilter = ColorFilter.tint(Color.White),
                                        modifier = Modifier
                                            .weight(1.0f)
                                            .fillMaxHeight(),
                                        painter = painterResource(id = R.drawable.like_icon),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.like_icon_description)
                                    )

                                    Image(
                                        colorFilter = ColorFilter.tint(Color.White),
                                        modifier = Modifier
                                            .weight(1.0f)
                                            .fillMaxHeight(),
                                        painter = painterResource(id = R.drawable.bookmark_icon),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.bookmark_icon_description)
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
                                    subtitle = stringResource(id = R.string.camera_title)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1.0f)
                            ) {
                                AddImageInformation(
                                    title = "Aperture",
                                    subtitle = stringResource(id = R.string.aperture_title)
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
                                    subtitle = stringResource(id = R.string.focal_length_title)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1.0f)
                            ) {
                                AddImageInformation(
                                    title = "Shutter Speed",
                                    subtitle = stringResource(id = R.string.shutter_speed_title)
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
                                    subtitle = stringResource(id = R.string.ISO_title)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1.0f)
                            ) {
                                AddImageInformation(
                                    title = "Dimensions",
                                    subtitle = stringResource(id = R.string.dimensions_title)
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
                                    subtitle = stringResource(id = R.string.views)
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AddImageInformation(
                                    title = "Downloads",
                                    subtitle = stringResource(id = R.string.downloads)
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AddImageInformation(
                                    title = "Likes",
                                    subtitle = stringResource(id = R.string.likes)
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
                                    Text(color = Color.White, text = "Bangkok")
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                                        .padding(1.dp)
                                ) {
                                    Text(color = Color.White, text = "Thailand")
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


