package lt.markmerkk.gridmergedbackground

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import lt.markmerkk.gridmergedbackground.databinding.FragmentHomeBinding
import lt.markmerkk.gridmergedbackground.entities.Item
import lt.markmerkk.gridmergedbackground.entities.ItemBundle

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding
        get() = _binding!!
    private val items = (0..100)
        .map { Item(title = "Item $it") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridSizes = listOf(1, 2, 3, 4)
        val itemCounts = listOf(1, 2, 3, 4, 5, 15)
        val gridConfigs: List<GridConfig> = gridSizes.map { gridSize ->
            itemCounts.map { itemCount ->
                GridConfig(gridSize, itemCount)
            }
        }.flatten()
        setupButtons(gridConfigs)
    }

    private fun setupButtons(
        gridConfigs: List<GridConfig>,
    ) {
        gridConfigs.forEach {
            binding.buttonContainer.addView(
                spawnButton(
                    context = requireContext(),
                    items = items,
                    gridConfig = it,
                )
            )
        }
    }

    private fun spawnButton(
        context: Context,
        items: List<Item>,
        gridConfig: GridConfig,
    ): Button {
        val themeWrapper = ContextThemeWrapper(
            context,
            R.style.Theme_GridMergedBackground_Button,
        )
        val button = MaterialButton(themeWrapper)
        button.text = "Grid: %d, ItemCount: %d"
            .format(gridConfig.gridSize, gridConfig.itemCount)
        button.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections
                    .actionHomeFragmentToItemsFragment(
                        gridSize = gridConfig.gridSize,
                        itemBundle = ItemBundle(
                            items = items.take(gridConfig.itemCount)
                        )
                    )
            )
        }
        return button
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private data class GridConfig(
        val gridSize: Int,
        val itemCount: Int,
    )
}
