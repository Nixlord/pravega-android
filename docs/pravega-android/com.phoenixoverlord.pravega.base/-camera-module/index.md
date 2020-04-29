[pravega-android](../../index.md) / [com.phoenixoverlord.pravega.base](../index.md) / [CameraModule](./index.md)

# CameraModule

`class CameraModule : `[`Component`](../-component/index.md)`, `[`UsesPermission`](../-uses-permission/index.md)`, `[`UsesActivityResult`](../-uses-activity-result/index.md)

### Types

| Name | Summary |
|---|---|
| [Result](-result/index.md) | `class Result` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CameraModule(activity: `[`PravegaActivity`](../-pravega-activity/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [activity](activity.md) | `val activity: `[`PravegaActivity`](../-pravega-activity/index.md) |
| [taskQueue](task-queue.md) | `val taskQueue: `[`ConcurrentLinkedQueue`](https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ConcurrentLinkedQueue.html)`<Result>` |

### Functions

| Name | Summary |
|---|---|
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(owner: LifecycleOwner, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onPermissionResult](on-permission-result.md) | `fun onPermissionResult(owner: LifecycleOwner, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [takePhoto](take-photo.md) | `fun takePhoto(params: () -> `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Result` |
