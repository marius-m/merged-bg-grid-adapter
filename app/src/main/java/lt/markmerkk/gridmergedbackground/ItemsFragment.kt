package lt.markmerkk.gridmergedbackground

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import lt.markmerkk.gridmergedbackground.adapters.ThumbnailAdapter
import lt.markmerkk.gridmergedbackground.databinding.FragmentItemsBinding
import lt.markmerkk.gridmergedbackground.entities.Item
import lt.markmerkk.gridmergedbackground.entities.ItemAsThumb
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ItemsFragment : Fragment() {

    private var _binding: FragmentItemsBinding? = null
    private val binding
        get() = _binding!!
    private val args by navArgs<ItemsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter(
            context = requireContext(),
            gridSize = args.gridSize,
            items = args.itemBundle.items,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter(
        context: Context,
        gridSize: Int,
        items: List<Item>,
    ) {
        Timber.tag("TEST").d(
            "setupAdapter(gridSize: %d, items: %s)",
            gridSize,
            items,
        )
        val adapter = ThumbnailAdapter<ItemAsThumb>(
            gridSpanSize = gridSize,
        )
        binding.recycler.layoutManager = GridLayoutManager(
            context,
            gridSize,
        )
        binding.recycler.adapter = adapter
        adapter.items = items.mapIndexed { index, item ->
            ItemAsThumb(
                id = index,
                item = item,
            )
        }
    }
}
