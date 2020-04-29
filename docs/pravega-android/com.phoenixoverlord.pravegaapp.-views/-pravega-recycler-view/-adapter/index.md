[pravega-android](../../../index.md) / [com.phoenixoverlord.pravegaapp.Views](../../index.md) / [PravegaRecyclerView](../index.md) / [Adapter](./index.md)

# Adapter

`inner class Adapter<Model> : Adapter<BindableViewHolder<Model>>`

```
    IMPLEMENTATION
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) |

```
    IMPLEMENTATION<br>```
<br>`Adapter(list: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<Model>, layout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, binder: (View, Model) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [binder](binder.md) | `val binder: (View, Model) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [layout](layout.md) | `val layout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [list](list.md) | `val list: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<Model>` |

### Functions

| Name | Summary |
|---|---|
| [addModel](add-model.md) | `fun addModel(model: Model): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: BindableViewHolder<Model>, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): BindableViewHolder<Model>` |
| [removeModel](remove-model.md) | `fun removeModel(model: Model): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
