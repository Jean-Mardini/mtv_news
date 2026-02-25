package com.example.mtv_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtv_news.ui.theme.*

data class NewsItem(
    val title: String,
    val time: String,
    val isBreaking: Boolean,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Mtv_newsTheme { MTVScreen() } }
    }
}

@Composable
fun MTVScreen() {

    val tabs = listOf("All", "Important", "Local", "International")
    var selectedTab by remember { mutableStateOf("All") }

    val news = listOf(
        NewsItem(
            "Abbas Araghchi: Iran will not develop a nuclear weapon, but it will not give up",
            "19:55", true, Icons.Filled.Public
        ),
        NewsItem(
            "UN General Assembly adopts draft resolution on peace",
            "19:49", false, Icons.Filled.AccountBalance
        ),
        NewsItem(
            "EU memo raises security concerns over mass escape from IS-linked Syria",
            "19:30", false, Icons.Filled.Security
        ),
        NewsItem(
            "French presidency: Director of the Louvre Museum has resigned",
            "19:21", false, Icons.Filled.Flag
        ),
        NewsItem(
            "Two U.S. officials: President will announce new measures",
            "18:58", true, Icons.Filled.Gavel
        ),
        NewsItem(
            "Cabinet to convene on Thursday at Grand Serail",
            "18:41", false, Icons.Filled.LocationCity
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrayBg)
    ) {

        // ---------- HEADER ----------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("☰", fontSize = 22.sp)

            Spacer(Modifier.weight(1f))

            Text("MTV", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)

            Spacer(Modifier.weight(1f))

            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(18.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
            ) { Text("Live", fontSize = 13.sp) }
        }

        // ---------- TABS (with red underline like MTV) ----------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavyBlue)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEach { tab ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { selectedTab = tab }
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = tab,
                        color = if (tab == selectedTab) White else White.copy(alpha = 0.55f),
                        fontWeight = if (tab == selectedTab) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 15.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    if (tab == selectedTab) {
                        Box(
                            modifier = Modifier
                                .height(3.dp)
                                .width(42.dp)
                                .clip(RoundedCornerShape(50))
                                .background(MTVRed)
                        )
                    } else {
                        Spacer(Modifier.height(3.dp))
                    }
                }
            }
        }

        // ---------- LIST ----------
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(news) { item ->
                MTVNewsRow(item)
                Divider(color = DividerGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun MTVNewsRow(item: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left “thumbnail” box but with icon (close to MTV layout without images)
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFE7EDF6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "category icon",
                tint = NavyBlue,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.time,
                    fontSize = 13.sp,
                    color = TimeGray
                )

                Spacer(Modifier.weight(1f))

                if (item.isBreaking) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(MTVRed)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "Breaking",
                            color = White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}