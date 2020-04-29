[pravega-android](../index.md) / [com.phoenixoverlord.pravega.extensions](./index.md)

## Package com.phoenixoverlord.pravega.extensions

### Types

| Name | Summary |
|---|---|
| [Firebase](-firebase/index.md) | `object Firebase` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [android.view.ViewGroup](android.view.-view-group/index.md) |  |
| [android.widget.EditText](android.widget.-edit-text/index.md) |  |
| [androidx.appcompat.app.AppCompatActivity](androidx.appcompat.app.-app-compat-activity/index.md) |  |
| [androidx.fragment.app.Fragment](androidx.fragment.app.-fragment/index.md) |  |
| [com.google.firebase.firestore.DocumentReference](com.google.firebase.firestore.-document-reference/index.md) |  |
| [com.google.firebase.storage.StorageReference](com.google.firebase.storage.-storage-reference/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [createImageFile](create-image-file.md) | `fun createImageFile(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = uniqueName(), parentFolder: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)` = getDefaultFolder()): `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html) |
| [downloadImage](download-image.md) | `fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.downloadImage(imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onSuccess: (file: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getDefaultFolder](get-default-folder.md) | `fun getDefaultFolder(): `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html) |
| [getExternallyAccessibleURI](get-externally-accessible-u-r-i.md) | `fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.getExternallyAccessibleURI(file: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): Uri` |
| [loadCircularImage](load-circular-image.md) | `fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.loadCircularImage(imageView: ImageView, imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ImageView` |
| [loadImage](load-image.md) | `fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.loadImage(imageView: ImageView, imageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ImageView`<br>`fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.loadImage(imageView: ImageView, imageFile: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): ImageView`<br>`fun `[`BaseActivity`](../com.phoenixoverlord.pravega.base/-base-activity/index.md)`.loadImage(imageView: ImageView, drawableID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ImageView` |
| [logDebug](log-debug.md) | `fun logDebug(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "GlobalLog", message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [logError](log-error.md) | `fun logError(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun logError(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "GlobalLog", exception: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
