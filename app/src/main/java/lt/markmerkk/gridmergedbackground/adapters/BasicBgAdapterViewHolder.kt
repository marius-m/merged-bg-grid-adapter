package lt.markmerkk.gridmergedbackground.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.databinding.ItemBasicBinding
import lt.markmerkk.gridmergedbackground.toViewClickListenerOrNull

class BasicBgAdapterViewHolder<T : BasicAdapterItem>(
    private val binding: ItemBasicBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: T,
        itemClickListener: ((T) -> Unit)?
    ) {
        val viewClickListener = toViewClickListenerOrNull(item, itemClickListener)
        binding.root.setOnClickListener(viewClickListener)
        binding.title.text = item.title
    }

    companion object {
        fun <T : BasicAdapterItem> create(viewGroup: ViewGroup): BasicBgAdapterViewHolder<T> {
            return BasicBgAdapterViewHolder(
                binding = ItemBasicBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }
}
