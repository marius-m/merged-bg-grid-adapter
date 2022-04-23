package lt.markmerkk.gridmergedbackground.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lt.markmerkk.gridmergedbackground.databinding.ItemBasicPaddingBinding
import lt.markmerkk.gridmergedbackground.toViewClickListenerOrNull

class BasicBgPaddingAdapterViewHolder<T : BasicAdapterItem>(
    private val binding: ItemBasicPaddingBinding,
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
        fun <T : BasicAdapterItem> create(viewGroup: ViewGroup): BasicBgPaddingAdapterViewHolder<T> {
            return BasicBgPaddingAdapterViewHolder(
                binding = ItemBasicPaddingBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }
}
