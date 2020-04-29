[pravega-android](../../index.md) / [com.phoenixoverlord.pravega.mechanisms](../index.md) / [NotificationModule](./index.md)

# NotificationModule

`class NotificationModule : ContextWrapper`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NotificationModule(context: Context)` |

### Properties

| Name | Summary |
|---|---|
| [CHANNEL_ID_UPLOAD](-c-h-a-n-n-e-l_-i-d_-u-p-l-o-a-d.md) | `val CHANNEL_ID_UPLOAD: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [loopingAtomicInteger](looping-atomic-integer.md) | `val loopingAtomicInteger: `[`LoopingAtomicInteger`](../../com.phoenixoverlord.pravega.utils/-looping-atomic-integer/index.md) |

### Functions

| Name | Summary |
|---|---|
| [cancelNotification](cancel-notification.md) | `fun cancelNotification(notificationId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [createDownloadProgressNotification](create-download-progress-notification.md) | `fun createDownloadProgressNotification(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, fileDownloadTask: FileDownloadTask): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [createHandlerIntent](create-handler-intent.md) | `fun createHandlerIntent(): PendingIntent?` |
| [createNotification](create-notification.md) | `fun createNotification(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [createNotificationChannel](create-notification-channel.md) | `fun createNotificationChannel(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [createUploadProgressNotification](create-upload-progress-notification.md) | `fun createUploadProgressNotification(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = loopingAtomicInteger.nextInt(), title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, uploadTask: UploadTask): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
