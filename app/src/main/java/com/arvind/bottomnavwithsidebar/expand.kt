package com.arvind.bottomnavwithsidebar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Message1(
    val author: String,
    val body: String,
    val amount: Double
)

@Composable
fun expand(
    messages: List<Message>,
    modifier: Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn1(modifier,onDestinationClicked, messages )
    }

}

@Composable
fun LazyColumn1(
    modifier: Modifier,
    onDestinationClicked: (route: String) -> Unit,
    messages: List<Message>
) {


    LazyColumn {
        itemsIndexed(messages) { index, _ ->

            Row(modifier = Modifier.padding(all = 10.dp)) {
                Image(
                    painter = painterResource(R.drawable.ic_login),
                    contentDescription = "Android background image",
                    modifier = Modifier
                        .size(60.dp)
                        //.background(Color(R.color.black))
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

                )

                Spacer(modifier = Modifier.width(15.dp))
                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor: Color by animateColorAsState(
                    if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                )

                Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

                    onDestinationClicked("home")
                    /*screensFromDrawer.forEach { screen ->
                        Spacer(Modifier.height(14.dp))
                        onDestinationClicked(screen.route)
                    }*/

                    /* if (index != 4) Text(
                         text = messages.get(index).author,
                         color = colorResource(id = R.color.black),
                         style = MaterialTheme.typography.subtitle2,

                         ) else Text(
                         text = messages.get(index).author,
                         color = colorResource(id = R.color.purple_200),
                         style = MaterialTheme.typography.subtitle2,

                         )
                     Spacer(modifier = Modifier.height(5.dp))

                     val format: NumberFormat = NumberFormat.getCurrencyInstance()
                     format.setMaximumFractionDigits(0);
                     format.setCurrency(Currency.getInstance("IND"));

                     Surface(
                         shape = MaterialTheme.shapes.medium,
                         elevation = 1.dp,
                         color = surfaceColor,
                         modifier = Modifier
                             .animateContentSize()
                             .padding(1.dp)) {
                         Text(
                             text = messages[index].body,
                             modifier = Modifier.padding(all = 4.dp),
                             maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                             //text =format.format(messages[index].amount),
                             // text = messages[index].amount.addAutomaticThousandSeparator(),

                         )
                     }*/
                }
            }
        }
    }

}
