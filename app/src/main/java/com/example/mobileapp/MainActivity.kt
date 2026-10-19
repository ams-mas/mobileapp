package com.example.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalLayoutDirection

private val AppBlue = Color(0xFF1756A9)
private val DeepBlue = Color(0xFF103B70)
private val Background = Color(0xFFF4F7FC)
private val TextDark = Color(0xFF1B2A41)
private val TextMuted = Color(0xFF68778D)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                MaterialTheme(
                    colorScheme = lightColorScheme(
                        primary = AppBlue,
                        secondary = Color(0xFF4E91E8),
                        background = Background,
                        surface = Color.White
                    ),
                    typography = MaterialTheme.typography.copy(
                        headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.SansSerif),
                        titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.SansSerif),
                        titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.SansSerif),
                        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.SansSerif),
                        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.SansSerif),
                        labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.SansSerif)
                    )
                ) {
                    RealEstateDashboard()
                }
            }
        }
    }
}

private data class DashboardItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val tint: Color,
    val pale: Color
)

@Composable
private fun RealEstateDashboard() {
    var selectedItem by rememberSaveable { mutableStateOf<String?>(null) }

    val items = listOf(
        DashboardItem("ثبت ملک", "ثبت مشخصات و قیمت ملک جدید", Icons.Default.HomeWork, Color(0xFF1768AC), Color(0xFFE8F3FF)),
        DashboardItem("فروش ملک", "مشاهده و پیگیری املاک فروشی", Icons.Default.Sell, Color(0xFF21845A), Color(0xFFE7F7EF)),
        DashboardItem("درخواست کرایه", "ثبت درخواست متقاضیان کرایه", Icons.Default.Search, Color(0xFF8056B3), Color(0xFFF1EAFE)),
        DashboardItem("ملک برای کرایه", "ثبت و مشاهده املاک کرایی", Icons.Default.Key, Color(0xFFD17B22), Color(0xFFFFF1DF))
    )

    Scaffold(containerColor = Background) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "رهنمای معاملات",
                        color = DeepBlue,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "سیستم مدیریت املاک",
                        color = TextMuted,
                        fontSize = 15.sp
                    )
                }
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = AppBlue,
                    modifier = Modifier.size(54.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.HomeWork,
                            contentDescription = "نشان املاک",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = DeepBlue),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "خوش آمدید",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "برای شروع، یکی از بخش‌های زیر را انتخاب کنید.",
                        color = Color(0xFFDCE9FA),
                        fontSize = 15.sp,
                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "بخش‌های اصلی",
                color = TextDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(13.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DashboardMenuCard(items[0], Modifier.weight(1f)) { selectedItem = items[0].title }
                DashboardMenuCard(items[1], Modifier.weight(1f)) { selectedItem = items[1].title }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DashboardMenuCard(items[2], Modifier.weight(1f)) { selectedItem = items[2].title }
                DashboardMenuCard(items[3], Modifier.weight(1f)) { selectedItem = items[3].title }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "اطلاعیه‌ها",
                        tint = AppBlue,
                        modifier = Modifier.size(28.dp)
                    )
                    Column {
                        Text(
                            text = "اطلاعیه‌ها",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "اطلاعیه‌های تازه در این بخش نمایش داده می‌شود.",
                            color = TextMuted,
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "نسخه ابتدایی سیستم مدیریت املاک",
                modifier = Modifier.fillMaxWidth(),
                color = TextMuted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    selectedItem?.let { title ->
        AlertDialog(
            onDismissRequest = { selectedItem = null },
            title = { Text(text = title, textAlign = TextAlign.Start) },
            text = {
                Text(
                    text = "صفحه این بخش در مرحله بعدی ساخته می‌شود.",
                    textAlign = TextAlign.Start,
                    lineHeight = 25.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { selectedItem = null }) {
                    Text("متوجه شدم")
                }
            },
            containerColor = Color.White
        )
    }
}

@Composable
private fun DashboardMenuCard(
    item: DashboardItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(174.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = RoundedCornerShape(15.dp),
                color = item.pale,
                modifier = Modifier.size(53.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = item.tint,
                        modifier = Modifier.size(29.dp)
                    )
                }
            }
            Column {
                Text(
                    text = item.title,
                    color = TextDark,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = item.description,
                    color = TextMuted,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
