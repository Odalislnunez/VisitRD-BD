package es.usj.mastertsa.onunez.visitrd.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import es.usj.mastertsa.onunez.visitrd.R
import es.usj.mastertsa.onunez.visitrd.databinding.FragmentFavoritesBinding

class FavoritesFragment : DialogFragment() {

    var _binding: FragmentFavoritesBinding? = null
    val binding: FragmentFavoritesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }
}