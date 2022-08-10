@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class
)

package com.arvind.bottomnavwithsidebar.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.bottomnavwithsidebar.Message
import com.arvind.bottomnavwithsidebar.R
import com.arvind.bottomnavwithsidebar.navigation.screensFromDrawer
import com.arvind.bottomnavwithsidebar.ui.theme.BottomNavWithSideBarTheme
import com.arvind.bottomnavwithsidebar.ui.theme.Purple500
import com.arvind.bottomnavwithsidebar.ui.theme.Purple700
import com.arvind.bottomnavwithsidebar.utils.SampleData

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WalletTheme(modifier,onDestinationClicked )
    }
}

@Composable
fun WalletTheme(modifier: Modifier, onDestinationClicked: (route: String) -> Unit) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Box() {
            // var emojis = Util.getEmojis()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                header(onDestinationClicked)

                //itemMenu(modifier, onDestinationClicked)
                //LazyColumnDemo(modifier, onDestinationClicked)
                LazyColumn1(onDestinationClicked, SampleData.conversationSample)
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(1f))

                footer()

            }
        }
    }
}

@Composable
fun header(onDestinationClicked: (route: String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 5.dp)
            .height(80.dp)
            .background(
                (Color.White)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back",
            modifier = Modifier
                .size(25.dp)
                .clickable(onClick = {
                    onDestinationClicked("home")
                })
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "Suraj Patil",
            style = MaterialTheme.typography.h5,
            color = Purple500
        )
    }
}

@Composable
fun itemMenu(modifier: Modifier, onDestinationClicked: (route: String) -> Unit) {

    val context = LocalContext.current

    var flag = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Column(
            modifier
                .fillMaxSize()
        ) {
            screensFromDrawer.forEach { screen ->
                Spacer(Modifier.height(14.dp))
                Column(
                    modifier = modifier.padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 0.dp, bottom = 15.dp)
                            .fillMaxWidth()
                            .clickable {
                                if (screen.expan == "false") {
                                    onDestinationClicked(screen.route)
                                } else {
                                    flag.value = true
                                }
                            }
                            .height(40.dp)
                            .background(color = Color.Transparent)

                    ) {
                        Image(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(35.dp)
                                .width(35.dp)
                                .padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = screen.title,
                            fontSize = 16.sp,
                            color = Purple700,
                            modifier = Modifier.padding(start = 10.dp)
                        )

                        var expanded by remember {mutableStateOf(false)}

                        if (screen.expan == "true") {
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expanded = !expanded }
                            ) {
                                Image(
                                    painter = painterResource(id = screen.icon1),
                                    contentDescription = screen.title,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(35.dp)
                                        .padding(start = 0.dp, end = 20.dp)
                                        .fillMaxSize()
                                        .clickable {
                                            flag.value = true
                                        }
                                )

                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(start = 10.dp, end = 20.dp),
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }

    if (flag.value){

        //Greeting(SampleData.conversationSample)
        Toast.makeText(context, "yyy", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun footer(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 0.dp, bottom = 0.dp)
                .height(80.dp)
                .background(
                    (Color.White)
                ),
            verticalAlignment = Alignment.CenterVertically,
        )  {
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(25.dp)
                    .clip(CircleShape)
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Logout",
                style = MaterialTheme.typography.h5,
                color = Purple500
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    BottomNavWithSideBarTheme {
        Drawer {}
    }
}

data class Message(
    val author: String,
    val body: String,
    val amount: Double
)

@Composable
private fun Greeting() {

    // Toast.makeText(context, "yyy", Toast.LENGTH_LONG).show()
    var expanded by remember {mutableStateOf(false)}
    Column(Modifier.clickable { expanded = !expanded }) {

        Image(painter = painterResource(R.drawable.ic_login),
            contentDescription = "Android background image")
        AnimatedVisibility(expanded) {
            Text(text = "jetpack compose",
                style = MaterialTheme.typography.h2,)
        }
    }
}

@Composable
fun LazyColumn1(
    onDestinationClicked: (route: String) -> Unit, messages: List<Message>,
) {
    val context = LocalContext.current
    var flag = remember {
        mutableStateOf(false)
    }

    LazyColumn {
        itemsIndexed(messages) { index, _ ->

            Row(modifier = Modifier.padding(end = 10.dp, bottom = 10.dp, top = 10.dp)) {

                Spacer(modifier = Modifier.width(15.dp))

                var isExpanded by remember { mutableStateOf(false) }

                Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

                    Row(modifier = Modifier.padding(all = 10.dp).fillMaxWidth()
                        .clickable {  if (index == 0) onDestinationClicked("Home")
                        //else if (index == 1) onDestinationClicked("my_profile")
                        else if (index == 2) onDestinationClicked("my_reviews")
                        else if (index == 3) onDestinationClicked("visit_history")
                        else if (index == 4) onDestinationClicked("notifications")
                        else if (index == 5) onDestinationClicked("settings")
                        else if (index == 6) onDestinationClicked("qr-code")
                        else if (index == 7) onDestinationClicked("help")
                        else if (index == 8) onDestinationClicked("about_us") }) {
                        Text(
                            modifier = Modifier.padding(start = 0.dp)
                                .clickable {
                                    if (index == 0) onDestinationClicked("Home")
                                    //else if (index == 1) onDestinationClicked("my_profile")
                                    else if (index == 2) onDestinationClicked("my_reviews")
                                    else if (index == 3) onDestinationClicked("visit_history")
                                    else if (index == 4) onDestinationClicked("notifications")
                                    else if (index == 5) onDestinationClicked("settings")
                                    else if (index == 6) onDestinationClicked("qr-code")
                                    else if (index == 7) onDestinationClicked("help")
                                    else if (index == 8) onDestinationClicked("about_us")
                                    //else if (index == 9) onDestinationClicked("logout")
                                },
                            text = messages[index].body,
                            fontSize = 16.sp,
                            color = Purple500
                        )
                        if (index == 1){
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                    }
                            ) {
                                Image(
                                    painter = painterResource(
                                        if (isExpanded) {
                                            R.drawable.ic_up_arrow
                                        } else {
                                            R.drawable.ic_down_arrow
                                        }
                                    ),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(24.dp)
                                        .fillMaxSize()
                                        .clickable {

                                        }
                                )
                            }
                        }
                    }
                    if (messages[index].amount == 1) {
                        AnimatedVisibility(isExpanded) {
                            // flag.value = true
                            expandView(onDestinationClicked)
                        }
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(start = 10.dp, end = 20.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
    /* if (flag.value){
         expandView(onDestinationClicked)
     }*/
}

@Composable
fun expandView(onDestinationClicked: (route: String) -> Unit) {

    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "Hello!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Purple500,

            modifier = Modifier
                .padding(10.dp)
                .clickable { onDestinationClicked("Home") }
        )
        Divider(
            modifier = Modifier.padding(start = 10.dp, end = 20.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )

        Text(
            text = "Abc!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Purple500,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .clickable { onDestinationClicked("Home") }
        )

        Divider(
            modifier = Modifier.padding(start = 10.dp, end = 20.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )

        Text(
            text = "Pqr!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Purple500,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .clickable { onDestinationClicked("Home") }
        )
    }

}