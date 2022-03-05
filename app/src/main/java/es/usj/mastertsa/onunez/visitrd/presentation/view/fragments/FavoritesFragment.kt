package es.usj.mastertsa.onunez.visitrd.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.usj.mastertsa.onunez.visitrd.databinding.FragmentFavoritesBinding

class FavoritesFragment: Fragment() {

    var _binding: FragmentFavoritesBinding? = null
    val binding: FragmentFavoritesBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}