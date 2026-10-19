# مدیریت املاک

اپلیکیشن اندروید برای سیستم مدیریت املاک و رهنمای معاملات افغانستان.

## فناوری
- Kotlin و Jetpack Compose
- طراحی راست‌به‌چپ (RTL)
- متن‌ها و منوهای فارسی
- ارتباط آینده با API امن PHP از طریق HTTPS؛ اتصال مستقیم اپلیکیشن به MySQL ممنوع است.

## اجرای پروژه
1. پروژه را در Android Studio باز کنید.
2. منتظر همگام‌سازی Gradle بمانید.
3. یک شبیه‌ساز اندروید بسازید یا موبایل را وصل کنید.
4. پیکربندی `app` را انتخاب کرده و روی دکمه Run کلیک کنید.

## منوی فعلی
- ثبت ملک
- فروش ملک
- درخواست کرایه
- ملک برای کرایه

کارت‌های داشبورد در حال حاضر نمای اولیه رابط کاربری هستند. صفحه‌های ثبت و مدیریت اطلاعات و اتصال به API هنوز باید ساخته شوند.

## ساختار و طرح معماری
برای توضیح بخش‌های پیشنهادی، فایل `REAL_ESTATE_APP_STRUCTURE.md` را ببینید.

## Local offline database
Room/SQLite is configured for local storage on the Android device. The current local tables are properties, customers, inquiries, and appointments. Local edits are marked as pending for future synchronization. The PHP REST API connection is not yet implemented, so local records are not currently shared with the web system.

See `REAL_ESTATE_APP_STRUCTURE.md` for the architecture and security rules.
