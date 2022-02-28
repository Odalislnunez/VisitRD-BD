package es.usj.mastertsa.onunez.visitrd.presentation.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import es.usj.mastertsa.onunez.visitrd.R

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}