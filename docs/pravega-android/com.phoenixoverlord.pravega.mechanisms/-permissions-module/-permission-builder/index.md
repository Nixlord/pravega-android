[pravega-android](../../../index.md) / [com.phoenixoverlord.pravega.mechanisms](../../index.md) / [PermissionsModule](../index.md) / [PermissionBuilder](./index.md)

# PermissionBuilder

`inner class PermissionBuilder`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PermissionBuilder(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [requestCode](request-code.md) | `val requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | `fun execute(onGranted: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { logDebug("DefaultPermitCallback", "Default") }): PermissionBuilder` |
| [onError](on-error.md) | `fun onError(onError: (`[`Error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = { error -> logError("DefaultPermitCallback", error) }): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [withPermissions](with-permissions.md) | `fun withPermissions(activity: `[`BaseActivity`](../../../com.phoenixoverlord.pravega.base/-base-activity/index.md)`, permissions: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): PermissionBuilder` |
