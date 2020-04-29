[pravega-android](../../index.md) / [com.phoenixoverlord.pravegaapp.Views](../index.md) / [PravegaRecyclerView](./index.md)

# PravegaRecyclerView

`class PravegaRecyclerView : RecyclerView`

### Types

| Name | Summary |
|---|---|
| [Adapter](-adapter/index.md) |

```
    IMPLEMENTATION<br>```
<br>`inner class Adapter<Model> : Adapter<BindableViewHolder<Model>>` |
| [BindableViewHolder](-bindable-view-holder/index.md) | `inner class BindableViewHolder<Model> : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PravegaRecyclerView(context: Context)`<br>`PravegaRecyclerView(context: Context, attrs: AttributeSet?)`<br>`PravegaRecyclerView(context: Context, attrs: AttributeSet?, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [addModel](add-model.md) | `fun <Model> addModel(model: Model): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [attach](attach.md) | `fun <Model> attach(list: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<Model>, layout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, layoutManager: LayoutManager, binder: (View, Model) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeModel](remove-model.md) | `fun <Model> removeModel(model: Model): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
