package com.arvind.bottomnavwithsidebar.view.sidebar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arvind.bottomnavwithsidebar.R
import com.arvind.bottomnavwithsidebar.navigation.Screens
import com.arvind.bottomnavwithsidebar.ui.theme.Purple500
import com.arvind.bottomnavwithsidebar.viewmodel.MainViewModel

@Composable
fun MyReviewsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.MyReviews)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text(text = "My Reviews.", style = MaterialTheme.typography.h4)

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
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "My Reviews",
                style = MaterialTheme.typography.h5,
                color = Purple500
            )

            Image(
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = "back",
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
            )
        }
    }
}