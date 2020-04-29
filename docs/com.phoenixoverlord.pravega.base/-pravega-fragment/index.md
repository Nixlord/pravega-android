[./](../../index.md) / [com.phoenixoverlord.pravega.base](../index.md) / [PravegaFragment](./index.md)

# PravegaFragment

`abstract class PravegaFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PravegaFragment()` |

### Properties

| Name | Summary |
|---|---|
| [activityResultComponents](activity-result-components.md) | `val activityResultComponents: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`UsesActivityResult`](../-uses-activity-result/index.md)`>` |
| [host](host.md) | `val host: `[`PravegaActivity`](../-pravega-activity/index.md) |
| [permissionResultComponents](permission-result-components.md) | `val permissionResultComponents: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`UsesPermission`](../-uses-permission/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [inject](inject.md) | `fun inject(vararg components: `[`Component`](../-component/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun inject(component: `[`Component`](../-component/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onActivityResult](on-activity-result.md) | `open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `open fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
