[pravega-android](../../../index.md) / [com.phoenixoverlord.pravega.mechanisms](../../index.md) / [ActivityResultHandler](../index.md) / [ActionBuilder](./index.md)

# ActionBuilder

`inner class ActionBuilder`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ActionBuilder(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [requestCode](request-code.md) | `val requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addOnFailureListener](add-on-failure-listener.md) | `fun addOnFailureListener(onError: (`[`Error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`, Intent?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ActionBuilder` |
| [addOnSuccessListener](add-on-success-listener.md) | `fun addOnSuccessListener(onSuccess: (Intent) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ActionBuilder` |
| [perform](perform.md) | `fun perform(runnable: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): ActionBuilder` |
