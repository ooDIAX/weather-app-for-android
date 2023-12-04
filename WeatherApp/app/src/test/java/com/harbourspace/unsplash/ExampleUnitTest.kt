package com.harbourspace.unsplash

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Observer
import com.harbourspace.unsplash.data.model.VisualCrossingItem
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.activity.viewModels
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun date_is_correct() {
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    assertEquals("2023-12-01", formattedDate)
  }

  @Test
  fun testButtonClick() {
    val currentDate = LocalDate.now()
    val dayList = mutableListOf<String>()

    for (i in 0 until 7) {
      val date = currentDate.plusDays(i.toLong())
      val dayOfMonth = date.format(DateTimeFormatter.ofPattern("yyy-MM-dd"))
      dayList.add(dayOfMonth)
    }

    val real = mutableListOf<String>()

    real.add("2023-12-01")
    real.add("2023-12-02")
    real.add("2023-12-03")
    real.add("2023-12-04")
    real.add("2023-12-05")
    real.add("2023-12-06")
    real.add("2023-12-07")

    for (i in 0 until 7) {
      assertEquals(real[i], dayList[i])
    }

  }


}

