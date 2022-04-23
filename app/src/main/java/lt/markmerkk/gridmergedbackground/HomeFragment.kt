package lt.markmerkk.gridmergedbackground

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import lt.markmerkk.gridmergedbackground.databinding.FragmentHomeBinding
import lt.markmerkk.gridmergedbackground.entities.AdapterType
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
        setupSection(requireContext(), AdapterType.BASIC, gridSizes, itemCounts)
        setupSection(requireContext(), AdapterType.MERGED, gridSizes, itemCounts)
    }

    private fun setupSection(
        c: Context,
        adapterType: AdapterType,
        gridSizes: List<Int>,
        itemCounts: List<Int>,
    ) {
        gridSizes.forEach { gridSize ->
            val gridConfigs = itemCounts.map { itemCount ->
                GridConfig(
                    gridSize = gridSize,
                    adapterType = adapterType,
                    itemCount = itemCount,
                )
            }
            setupButtons(
                c = c,
                title = "Adapter: ${adapterType.name}",
                subtitle = "Grid: $gridSize",
                gridConfigs,
            )
        }
    }

    private fun setupButtons(
        c: Context,
        title: String,
        subtitle: String,
        gridConfigs: List<GridConfig>,
    ) {
        with(binding.buttonContainer) {
            addView(spawnHeader1(c, title))
            addView(spawnHeader2(c, subtitle))
            gridConfigs.forEach {
                addView(
                    spawnButton(
                        context = c,
                        items = items,
                        gridConfig = it,
                    )
                )
            }
        }
    }

    private fun spawnHeader1(
        context: Context,
        textHeader: String,
    ): TextView {
        val themeWrapper = ContextThemeWrapper(
            context,
            R.style.Theme_GridMergedBackground_TextView,
        )
        val viewTextHeader = TextView(themeWrapper).apply {
            setTextAppearance(R.style.Theme_GridMergedBackground_Headline1)
            text = textHeader
        }
        return viewTextHeader
    }

    private fun spawnHeader2(
        context: Context,
        textHeader: String,
    ): TextView {
        val themeWrapper = ContextThemeWrapper(
            context,
            R.style.Theme_GridMergedBackground_TextView,
        )
        val viewTextHeader = TextView(themeWrapper).apply {
            setTextAppearance(R.style.Theme_GridMergedBackground_Headline2)
            text = textHeader
        }
        return viewTextHeader
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
        button.text = "Grid size: %d, Item count: %d"
            .format(
                gridConfig.gridSize,
                gridConfig.itemCount
            )
        button.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections
                    .actionHomeFragmentToItemsFragment(
                        itemBundle = ItemBundle(
                            gridSize = gridConfig.gridSize,
                            adapterType = gridConfig.adapterType,
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
        val adapterType: AdapterType,
        val itemCount: Int,
    )
}
