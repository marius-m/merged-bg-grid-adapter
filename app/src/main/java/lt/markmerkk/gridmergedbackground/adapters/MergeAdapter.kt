package lt.markmerkk.gridmergedbackground.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.autoNotify
import kotlin.properties.Delegates

class MergeAdapter<T : BasicAdapterItem>(
    private val gridSpanSize: Int,
    private val itemClickListener: ((BasicAdapterItem) -> Unit)? = null,
) : RecyclerView.Adapter<MergeAdapterViewHolder<T>>(), ItemBoundableAdapter<T> {

    override var items: List<T> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MergeAdapterViewHolder<T> {
        return MergeAdapterViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(
        holder: MergeAdapterViewHolder<T>,
        position: Int,
    ) {
        val isItemInFirstRow = isItemInFirstRow(position)
        val isItemInFirstColumn = isItemInFirstColumn(position)
        val item = items[position]
        holder.bind(
            isItemInFirstRow,
            isItemInFirstColumn,
            item,
            itemClickListener
        )
    }

    /**
     * @return item position is in the first row
     */
    private fun isItemInFirstRow(pos: Int): Boolean {
        return pos <= gridSpanSize - 1
    }

    /**
     * @return item position is in the first column, when on different rows
     */
    private fun isItemInFirstColumn(pos: Int): Boolean {
        return pos % gridSpanSize == 0
    }

    override fun getItemCount(): Int = items.size
}
