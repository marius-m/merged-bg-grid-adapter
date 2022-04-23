package lt.markmerkk.gridmergedbackground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        binding.button1.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections
                    .actionHomeFragmentToItemsFragment(
                        gridSize = 2,
                        itemBundle = ItemBundle(
                            items = items.take(3)
                        )
                    )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
