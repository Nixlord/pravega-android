[./](../../index.md) / [com.phoenixoverlord.pravega.mechanisms](../index.md) / [ActivityResultHandler](./index.md)

# ActivityResultHandler

`class ActivityResultHandler`

### Types

| Name | Summary |
|---|---|
| [ActionBuilder](-action-builder/index.md) | `inner class ActionBuilder` |
| [ActivityResultAction](-activity-result-action/index.md) | `class ActivityResultAction` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ActivityResultHandler()` |

### Properties

| Name | Summary |
|---|---|
| [actionRequests](action-requests.md) | `val actionRequests: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, ActivityResultAction>` |

### Functions

| Name | Summary |
|---|---|
| [createAction](create-action.md) | `fun createAction(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ActionBuilder` |
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
