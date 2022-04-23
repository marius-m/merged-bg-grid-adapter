package lt.markmerkk.gridmergedbackground.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.autoNotify
import kotlin.properties.Delegates

class ThumbnailAdapter<T : ThumbItem>(
    private val gridSpanSize: Int,
    private val itemClickListener: ((ThumbItem) -> Unit)? = null,
) : RecyclerView.Adapter<ThumbnailViewHolder<T>>() {

    var items: List<T> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ThumbnailViewHolder<T> {
        return ThumbnailViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(
        holder: ThumbnailViewHolder<T>,
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
