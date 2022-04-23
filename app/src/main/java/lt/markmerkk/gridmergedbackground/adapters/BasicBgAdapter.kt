package lt.markmerkk.gridmergedbackground.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.autoNotify
import kotlin.properties.Delegates

class BasicBgAdapter<T : BasicAdapterItem>(
    private val gridSpanSize: Int,
    private val itemClickListener: ((BasicAdapterItem) -> Unit)? = null,
) : RecyclerView.Adapter<BasicBgAdapterViewHolder<T>>() {

    var items: List<T> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BasicBgAdapterViewHolder<T> {
        return BasicBgAdapterViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(
        holder: BasicBgAdapterViewHolder<T>,
        position: Int,
    ) {
        val item = items[position]
        holder.bind(
            item,
            itemClickListener
        )
    }

    override fun getItemCount(): Int = items.size
}
