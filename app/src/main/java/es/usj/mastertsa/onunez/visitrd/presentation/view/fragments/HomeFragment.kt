package es.usj.mastertsa.onunez.visitrd.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.onunez.visitrd.databinding.FragmentHomeBinding
import es.usj.mastertsa.onunez.visitrd.presentation.view.activities.PlaceActivity
import es.usj.mastertsa.onunez.visitrd.presentation.view.adapters.HomeAdapter
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.PlaceState
import es.usj.mastertsa.onunez.visitrd.presentation.viewmodel.HomeViewModel
import es.usj.mastertsa.onunez.visitrd.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {
    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

    val placesAdapter = HomeAdapter(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMain.apply {
            adapter = placesAdapter
            placesAdapter.setOnItemClickListener(object: HomeAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(activity,position.toString(),Toast.LENGTH_LONG)
                    val intent = Intent(activity, PlaceActivity::class.java)
                    intent.putExtra("place", placesAdapter.getItem(position))
                    startActivity(intent)
                }
            })
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.homeStateFlow.collect { placeState: PlaceState ->
                setState(placeState)
            }
        }

        homeViewModel.getData()
    }

    private fun setState(placeState: PlaceState) {
        when(placeState) {
            PlaceState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is PlaceState.Success -> {
                binding.progressBar.visibility = View.GONE
                val placesList = placeState.data
                placesAdapter.submitList(placesList)
            }
            is PlaceState.Failure -> {
                Toast.makeText(context, "Failure!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
    @JvmStatic
        fun newInstance() = HomeFragment()
    }
}