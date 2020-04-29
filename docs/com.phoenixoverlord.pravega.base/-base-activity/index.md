[./](../../index.md) / [com.phoenixoverlord.pravega.base](../index.md) / [BaseActivity](./index.md)

# BaseActivity

`abstract class BaseActivity : AppCompatActivity`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseActivity()` |

### Properties

| Name | Summary |
|---|---|
| [activityResultHandler](activity-result-handler.md) | `val activityResultHandler: `[`ActivityResultHandler`](../../com.phoenixoverlord.pravega.mechanisms/-activity-result-handler/index.md) |
| [compositeDisposable](composite-disposable.md) | `val compositeDisposable: CompositeDisposable` |
| [loopingAtomicInteger](looping-atomic-integer.md) | `val loopingAtomicInteger: `[`LoopingAtomicInteger`](../../com.phoenixoverlord.pravega.utils/-looping-atomic-integer/index.md) |
| [notificationModule](notification-module.md) | `val notificationModule: `[`NotificationModule`](../../com.phoenixoverlord.pravega.mechanisms/-notification-module/index.md) |
| [permissionsModule](permissions-module.md) | `val permissionsModule: `[`PermissionsModule`](../../com.phoenixoverlord.pravega.mechanisms/-permissions-module/index.md) |

### Functions

| Name | Summary |
|---|---|
| [onActivityResult](on-activity-result.md) | CompressionModule`open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDestroy](on-destroy.md) | `open fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `open fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [rx](rx.md) | `fun rx(disposableFn: () -> Disposable): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [startActivityGetResult](start-activity-get-result.md) | ActivityResultModule`fun startActivityGetResult(intent: Intent, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = loopingAtomicInteger.nextInt()): ActionBuilder` |
| [withPermissions](with-permissions.md) | PermissionsModule`fun withPermissions(vararg permissions: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): PermissionBuilder` |

### Extension Functions

| Name | Summary |
|---|---|
| [downloadImage](../../com.phoenixoverlord.pravega.extensions/download-image.md) | `fun `[`BaseActivity`](./index.md)`.downloadImage(imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onSuccess: (file: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getExternallyAccessibleURI](../../com.phoenixoverlord.pravega.extensions/get-externally-accessible-u-r-i.md) | `fun `[`BaseActivity`](./index.md)`.getExternallyAccessibleURI(file: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): Uri` |
| [loadCircularImage](../../com.phoenixoverlord.pravega.extensions/load-circular-image.md) | `fun `[`BaseActivity`](./index.md)`.loadCircularImage(imageView: ImageView, imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ImageView` |
| [loadImage](../../com.phoenixoverlord.pravega.extensions/load-image.md) | `fun `[`BaseActivity`](./index.md)`.loadImage(imageView: ImageView, imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ImageView`<br>`fun `[`BaseActivity`](./index.md)`.loadImage(imageView: ImageView, imageFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): ImageView`<br>`fun `[`BaseActivity`](./index.md)`.loadImage(imageView: ImageView, drawableID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ImageView` |
