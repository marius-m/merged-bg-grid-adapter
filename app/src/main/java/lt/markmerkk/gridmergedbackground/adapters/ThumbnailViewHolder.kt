package lt.markmerkk.gridmergedbackground.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.R
import lt.markmerkk.gridmergedbackground.databinding.ItemThumbBinding
import lt.markmerkk.gridmergedbackground.toViewClickListenerOrNull

class ThumbnailViewHolder<T : ThumbItem>(
    private val binding: ItemThumbBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        isFirstRow: Boolean,
        isFirstColumn: Boolean,
        item: T,
        itemClickListener: ((T) -> Unit)?
    ) {
        val viewClickListener = toViewClickListenerOrNull(item, itemClickListener)
        binding.root.setOnClickListener(viewClickListener)
        binding.title.text = item.title
        binding.root.setBackgroundResource(bgResourceByPosition(isFirstRow, isFirstColumn))
    }

    /**
     * Provides diff background based on item position in the grid
     * @param isFirstRow item is in the first row of the grid
     * @param isFirstColumn item is in the first column of the row
     */
    @DrawableRes
    private fun bgResourceByPosition(
        isFirstRow: Boolean,
        isFirstColumn: Boolean,
    ): Int {
        return when {
            isFirstRow && isFirstColumn -> R.drawable.shape_ll_product_thumb_row_column_first
            isFirstRow && !isFirstColumn -> R.drawable.shape_ll_product_thumb_row_column_last
            isFirstColumn -> R.drawable.shape_ll_product_thumb_column_first
            else -> R.drawable.shape_ll_product_thumb_column_last
        }
    }

    companion object {
        fun <T : ThumbItem> create(viewGroup: ViewGroup): ThumbnailViewHolder<T> {
            return ThumbnailViewHolder(
                binding = ItemThumbBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }
}
