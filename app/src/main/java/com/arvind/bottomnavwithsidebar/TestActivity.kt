package com.arvind.bottomnavwithsidebar

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvind.bottomnavwithsidebar.ui.theme.BottomNavWithSideBarTheme
import com.arvind.bottomnavwithsidebar.utils.SampleData
import java.text.NumberFormat
import java.util.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavWithSideBarTheme{
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(
    val author: String,
    val body: String,
    val amount: Int
)

@Composable
fun Conversation(
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
                    if (index != 4) Text(
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
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode"
)

@Composable
fun PreviewConversation() {
    BottomNavWithSideBarTheme {
        Conversation(SampleData.conversationSample)
    }
}
