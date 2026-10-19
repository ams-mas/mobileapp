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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import android.widget.Toast
import com.example.mobileapp.data.local.entity.PropertyEntity
import kotlinx.coroutines.launch

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
    val icon: ImageVector,
    val tint: Color,
    val pale: Color
)

@Composable
private fun RealEstateDashboard() {
    var selectedItem by rememberSaveable { mutableStateOf<String?>(null) }

    if (selectedItem != null) {
        val title = selectedItem ?: ""
        if (title == "ثبت ملک" || title == "فروش ملک") {
            PropertyRegistrationPage(onBack = { selectedItem = null })
        } else {
            SimpleSectionPage(title = title, onBack = { selectedItem = null })
        }
    } else {
    val items = listOf(
        DashboardItem("ثبت ملک", Icons.Default.HomeWork, Color(0xFF1768AC), Color(0xFFE8F3FF)),
        DashboardItem("فروش ملک", Icons.Default.Sell, Color(0xFF21845A), Color(0xFFE7F7EF)),
        DashboardItem("درخواست کرایه", Icons.Default.Search, Color(0xFF8056B3), Color(0xFFF1EAFE)),
        DashboardItem("ملک برای کرایه", Icons.Default.Key, Color(0xFFD17B22), Color(0xFFFFF1DF))
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
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items.take(3).forEach { item ->
                    DashboardMenuCard(
                        item = item,
                        modifier = Modifier.weight(1f)
                    ) { selectedItem = item.title }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items.drop(3).forEach { item ->
                    DashboardMenuCard(
                        item = item,
                        modifier = Modifier.weight(1f)
                    ) { selectedItem = item.title }
                }
                repeat(2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
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


    }
}

@Composable
private fun SimpleSectionPage(title: String, onBack: () -> Unit) {
    Scaffold(containerColor = Background) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TextButton(onClick = onBack) { Text("← بازگشت به صفحه اصلی") }
            Text(title, color = DeepBlue, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("این صفحه در مرحله بعدی تکمیل می‌شود.", color = TextMuted)
        }
    }
}

@Composable
private fun ChoiceChips(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        options.forEach { option ->
            androidx.compose.material3.FilterChip(
                selected = option == selected,
                onClick = { onSelect(option) },
                label = { Text(option) }
            )
        }
    }
}

@Composable
private fun PropertyRegistrationPage(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var ownerName by rememberSaveable { mutableStateOf("") }
    var ownerPhone by rememberSaveable { mutableStateOf("") }
    var saleType by rememberSaveable { mutableStateOf("فروش قطعی") }
    var propertyType by rememberSaveable { mutableStateOf("خانه") }
    var address by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var documentType by rememberSaveable { mutableStateOf("") }
    var ownerCount by rememberSaveable { mutableStateOf("۱") }
    var hasProblem by rememberSaveable { mutableStateOf(false) }
    var problemDetails by rememberSaveable { mutableStateOf("") }
    var saving by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    fun digits(value: String) = value
        .replace('۰', '0').replace('۱', '1').replace('۲', '2').replace('۳', '3').replace('۴', '4')
        .replace('۵', '5').replace('۶', '6').replace('۷', '7').replace('۸', '8').replace('۹', '9')
        .replace('٠', '0').replace('١', '1').replace('٢', '2').replace('٣', '3').replace('٤', '4')
        .replace('٥', '5').replace('٦', '6').replace('٧', '7').replace('٨', '8').replace('٩', '9')

    Scaffold(containerColor = Background) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(onClick = onBack, enabled = !saving) { Text("← بازگشت به صفحه اصلی") }
            Text("ثبت ملک برای فروش", color = DeepBlue, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("مشخصات مالک", color = AppBlue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(ownerName, { ownerName = it }, label = { Text("نام مالک *") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(ownerPhone, { ownerPhone = it }, label = { Text("شماره تماس مالک *") }, placeholder = { Text("+93 یا شماره محلی") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Text("نوع فروش", color = AppBlue, fontWeight = FontWeight.Bold)
            ChoiceChips(listOf("فروش قطعی", "گروی", "معاوضه"), saleType) { saleType = it }
            Text("نوع ملک", color = AppBlue, fontWeight = FontWeight.Bold)
            ChoiceChips(listOf("خانه", "آپارتمان", "مارکیت", "زمین"), propertyType) { propertyType = it }
            OutlinedTextField(address, { address = it }, label = { Text("آدرس مکمل ملک *") }, placeholder = { Text("ولایت، ناحیه، کوچه و نشانی دقیق") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(price, { price = it.filter { ch -> ch.isDigit() || ch == ',' || ch == '٬' } }, label = { Text("قیمت پیشنهادی ملک *") }, placeholder = { Text("مبلغ به افغانی یا واحد توافق‌شده") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
            OutlinedTextField(documentType, { documentType = it }, label = { Text("نوع اسناد ملک *") }, placeholder = { Text("مثلاً قباله شرعی، سند رسمی، عرفی") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(ownerCount, { ownerCount = it.filter { ch -> ch.isDigit() } }, label = { Text("تعداد مالکین *") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
            Text("آیا ملک مشکل یا محدودیت حقوقی دارد؟", color = AppBlue, fontWeight = FontWeight.Bold)
            ChoiceChips(listOf("نخیر", "بلی"), if (hasProblem) "بلی" else "نخیر") {
                hasProblem = it == "بلی"
                if (!hasProblem) problemDetails = ""
            }
            if (hasProblem) {
                OutlinedTextField(problemDetails, { problemDetails = it }, label = { Text("شرح مشکل *") }, placeholder = { Text("مشکل یا محدودیت را توضیح دهید") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            }
            error?.let { Text(it, color = Color(0xFFB3261E)) }
            Button(
                onClick = {
                    val count = digits(ownerCount).toIntOrNull()
                    val parsedPrice = digits(price.replace(",", "").replace("٬", "")).toLongOrNull()
                    when {
                        ownerName.isBlank() -> error = "لطفاً نام مالک را وارد کنید."
                        ownerPhone.isBlank() -> error = "لطفاً شماره تماس مالک را وارد کنید."
                        address.isBlank() -> error = "لطفاً آدرس ملک را وارد کنید."
                        parsedPrice == null || parsedPrice <= 0L -> error = "لطفاً قیمت معتبر ملک را وارد کنید."
                        documentType.isBlank() -> error = "لطفاً نوع اسناد را مشخص کنید."
                        count == null || count < 1 -> error = "تعداد مالکین باید حداقل ۱ باشد."
                        hasProblem && problemDetails.isBlank() -> error = "لطفاً مشکل ملک را توضیح دهید."
                        else -> {
                            error = null
                            saving = true
                            scope.launch {
                                try {
                                    val app = context.applicationContext as RealEstateApplication
                                    app.localDatabase.propertyDao().upsert(
                                        PropertyEntity(
                                            title = propertyType + " - " + ownerName.trim(),
                                            propertyType = propertyType,
                                            listingType = saleType,
                                            province = "",
                                            address = address.trim(),
                                            price = parsedPrice,
                                            currency = "AFN",
                                            description = "نام مالک: " + ownerName.trim() +
                                                "\nشماره تماس مالک: " + ownerPhone.trim() +
                                                "\nنوع اسناد: " + documentType.trim() +
                                                "\nتعداد مالکین: " + count +
                                                "\nمشکل حقوقی: " + (if (hasProblem) "بلی" else "نخیر") +
                                                (if (hasProblem) "\nشرح مشکل: " + problemDetails.trim() else ""),
                                            status = "available",
                                            syncState = "pending"
                                        )
                                    )
                                    Toast.makeText(context, "اطلاعات ملک در همین دستگاه ذخیره شد.", Toast.LENGTH_LONG).show()
                                    onBack()
                                } catch (e: Exception) {
                                    saving = false
                                    error = "ذخیره اطلاعات انجام نشد. لطفاً دوباره تلاش کنید."
                                }
                            }
                        }
                    }
                },
                enabled = !saving,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (saving) CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                Text(if (saving) "در حال ذخیره..." else "ذخیره ملک")
            }
            Text("کد ملک بعداً توسط سرور ساخته می‌شود.", color = TextMuted, fontSize = 12.sp)
        }
    }
}


@Composable
private fun PropertyRegistrationDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var title by rememberSaveable { mutableStateOf("") }
    var propertyType by rememberSaveable { mutableStateOf("خانه") }
    var listingType by rememberSaveable { mutableStateOf("فروش") }
    var province by rememberSaveable { mutableStateOf("") }
    var district by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var currency by rememberSaveable { mutableStateOf("AFN") }
    var bedrooms by rememberSaveable { mutableStateOf("") }
    var bathrooms by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var saving by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    fun normalizeDigits(value: String): String = value
        .replace('۰', '0').replace('۱', '1').replace('۲', '2')
        .replace('۳', '3').replace('۴', '4').replace('۵', '5')
        .replace('۶', '6').replace('۷', '7').replace('۸', '8')
        .replace('۹', '9')
        .replace('٠', '0').replace('١', '1').replace('٢', '2')
        .replace('٣', '3').replace('٤', '4').replace('٥', '5')
        .replace('٦', '6').replace('٧', '7').replace('٨', '8')
        .replace('٩', '9')

    AlertDialog(
        onDismissRequest = { if (!saving) onDismiss() },
        title = {
            Text("ثبت ملک جدید", color = DeepBlue, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(520.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("مشخصات اصلی", color = AppBlue, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = title, onValueChange = { title = it },
                    label = { Text("عنوان ملک *") },
                    placeholder = { Text("مثلاً خانه دو منزله") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = propertyType, onValueChange = { propertyType = it },
                    label = { Text("نوع ملک *") },
                    placeholder = { Text("خانه، آپارتمان، زمین، دوکان") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = listingType, onValueChange = { listingType = it },
                    label = { Text("نوع معامله *") },
                    placeholder = { Text("فروش، کرایه، گروی") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                Text("موقعیت ملک", color = AppBlue, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = province, onValueChange = { province = it },
                    label = { Text("ولایت *") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = district, onValueChange = { district = it },
                    label = { Text("ناحیه / ولسوالی") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                OutlinedTextField(
                    value = address, onValueChange = { address = it },
                    label = { Text("آدرس مکمل") },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("قیمت و اندازه", color = AppBlue, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = price, onValueChange = { price = it.filter { ch -> ch.isDigit() } },
                    label = { Text("قیمت *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
                OutlinedTextField(
                    value = currency, onValueChange = { currency = it.uppercase().take(3) },
                    label = { Text("واحد پول (AFN / USD / PKR)") },
                    modifier = Modifier.fillMaxWidth(), singleLine = true
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = bedrooms, onValueChange = { bedrooms = it.filter { ch -> ch.isDigit() } },
                        label = { Text("اتاق خواب") }, modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = bathrooms, onValueChange = { bathrooms = it.filter { ch -> ch.isDigit() } },
                        label = { Text("حمام") }, modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
                OutlinedTextField(
                    value = area,
                    onValueChange = { area = it.filter { ch -> ch.isDigit() || ch == '.' || ch == '٫' || ch == ',' } },
                    label = { Text("مساحت (متر مربع)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
                OutlinedTextField(
                    value = description, onValueChange = { description = it },
                    label = { Text("توضیحات و امکانات") },
                    modifier = Modifier.fillMaxWidth(), minLines = 3
                )
                error?.let { Text(it, color = Color(0xFFB3261E), fontSize = 13.sp) }
                Text(
                    "کد ملک پس از اتصال به سیستم اصلی از طرف سرور ساخته می‌شود.",
                    color = TextMuted, fontSize = 12.sp, lineHeight = 18.sp
                )
            }
        },
        confirmButton = {
            Button(
                enabled = !saving,
                onClick = {
                    val parsedPrice = normalizeDigits(price).toLongOrNull()
                    when {
                        title.isBlank() -> error = "لطفاً عنوان ملک را وارد کنید."
                        propertyType.isBlank() -> error = "لطفاً نوع ملک را وارد کنید."
                        listingType.isBlank() -> error = "لطفاً نوع معامله را وارد کنید."
                        province.isBlank() -> error = "لطفاً ولایت را وارد کنید."
                        parsedPrice == null || parsedPrice < 0 -> error = "لطفاً قیمت معتبر وارد کنید."
                        currency !in listOf("AFN", "USD", "PKR") -> error = "واحد پول باید AFN، USD یا PKR باشد."
                        else -> {
                            error = null
                            saving = true
                            scope.launch {
                                try {
                                    val app = context.applicationContext as RealEstateApplication
                                    app.localDatabase.propertyDao().upsert(
                                        PropertyEntity(
                                            title = title.trim(),
                                            propertyType = propertyType.trim(),
                                            listingType = listingType.trim(),
                                            province = province.trim(),
                                            district = district.trim(),
                                            address = address.trim(),
                                            price = parsedPrice,
                                            currency = currency,
                                            bedrooms = normalizeDigits(bedrooms).toIntOrNull(),
                                            bathrooms = normalizeDigits(bathrooms).toIntOrNull(),
                                            areaSquareMeters = normalizeDigits(area.replace('٫', '.').replace(',', '.')).toDoubleOrNull(),
                                            description = description.trim(),
                                            status = "available",
                                            syncState = "pending"
                                        )
                                    )
                                    Toast.makeText(context, "ملک در همین دستگاه ذخیره شد.", Toast.LENGTH_LONG).show()
                                    onDismiss()
                                } catch (e: Exception) {
                                    saving = false
                                    error = "ذخیره ملک انجام نشد. لطفاً دوباره تلاش کنید."
                                }
                            }
                        }
                    }
                }
            ) {
                if (saving) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(if (saving) "در حال ذخیره..." else "ذخیره ملک")
            }
        },
        dismissButton = {
            TextButton(enabled = !saving, onClick = onDismiss) { Text("انصراف") }
        },
        containerColor = Color.White
    )
}

@Composable
private fun DashboardMenuCard(
    item: DashboardItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(112.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(15.dp),
                color = item.pale,
                modifier = Modifier.size(48.dp)
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.title,
                color = TextDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
