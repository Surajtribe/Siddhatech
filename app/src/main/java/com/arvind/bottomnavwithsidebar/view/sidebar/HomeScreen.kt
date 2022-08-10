package com.arvind.bottomnavwithsidebar.view.sidebar

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.viewinterop.AndroidView
import com.arvind.bottomnavwithsidebar.navigation.Screens
import com.arvind.bottomnavwithsidebar.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.time.Month
import java.time.OffsetDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.Home)
    Greeting1()
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Greeting1() {
    val context = LocalContext.current
    val thisMonth: Month = Month.from(YearMonth.now(ZoneId.systemDefault()))
    val months: List<Month> = Arrays.asList(thisMonth.minus(2), thisMonth.minus(1), thisMonth)
    for (m in months) {
        Log.e("TAG", "Greeting: "+ m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH))
        System.out.println(m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH))
    }
    val fmt = SimpleDateFormat("dd/MM/yyyy")
    val cal = Calendar.getInstance()
    cal.clear()
    cal[2022, 8 - 1] = 1
    val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    for (i in 0 until daysInMonth) {
       // System.out.println(fmt.format(cal.time))
        Log.e("TAG", "Greeting: "+fmt.format(cal.time))
        cal.add(Calendar.DAY_OF_MONTH, 1)

    }

    val test = "come"

    Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().monthValue
    Log.e("TAG", "Greeting: "+Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().monthValue)

    val locale = Locale("en_US")
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    context.getApplicationContext().getResources().updateConfiguration(config, null)

    val strDateTime = "2020-09-10T20:00:00.000Z"
    val odt: OffsetDateTime = OffsetDateTime.parse(strDateTime)
    Log.e("aaa", "Greeting1: "+odt )
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("MMMM", Locale("es", "ES"))


    val formatted: String = formatter.format(odt)
    println(formatted)
    Log.e("TAG", "Greeting1: "+formatted )
    //Toast.makeText(context, formatted, Toast.LENGTH_LONG).show()


    val c = Calendar.getInstance()
    c.add(Calendar.MONTH, -2)
    val dd = DatePickerDialog(
        context,
        { view, year, monthOfYear, dayOfMonth ->
            try {
                Log.e("TAG", "Greeting1: "+c[Calendar.DAY_OF_MONTH] )
            } catch (ex: Exception) {
            }
        }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DAY_OF_MONTH]
    )
    dd.show()

    val date = remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        AndroidView(
            factory = { CalendarView(context) },

            /*update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    c[Calendar.YEAR]; c[Calendar.MONTH]; c[Calendar.DAY_OF_MONTH]
                }
            },*/
        )

        /*cal.set(
            2025, // year
            10, // month nov, 0 based index
            3 // day of month
        )

        calendarView.setDate(
            cal.timeInMillis,

            false,
            false
        )*/

    }

}
