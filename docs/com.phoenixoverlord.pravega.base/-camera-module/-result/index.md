[./](../../../index.md) / [com.phoenixoverlord.pravega.base](../../index.md) / [CameraModule](../index.md) / [Result](./index.md)

# Result

`class Result`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Result(onSuccess: (`[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}, onFailure: (`[`Error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {})` |

### Properties

| Name | Summary |
|---|---|
| [onFailure](on-failure.md) | `var onFailure: (`[`Error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSuccess](on-success.md) | `var onSuccess: (`[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addonFailureListener](addon-failure-listener.md) | `fun addonFailureListener(onFailure: (`[`Error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [addOnSuccessListener](add-on-success-listener.md) | `fun addOnSuccessListener(onSuccess: (`[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
