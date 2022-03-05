package es.usj.mastertsa.onunez.visitrd.presentation.view.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import es.usj.mastertsa.onunez.visitrd.R
import es.usj.mastertsa.onunez.visitrd.databinding.FragmentPlaceBinding
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.presentation.view.adapters.CommentAdapter
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.CommentState
import es.usj.mastertsa.onunez.visitrd.presentation.viewmodel.CommentViewModel
import es.usj.mastertsa.onunez.visitrd.presentation.viewmodel.CommentViewModelFactory
import kotlinx.coroutines.flow.collect

class PlaceFragment : Fragment() {
    private var lat: String = "40.4167754"
    private var lon: String = "-3.7037901999999576"
    private var locManager: LocationManager? = null
    private var locListener: LocationListener? = null
    private var favorite: Boolean = true
    lateinit var commentsAdapter: CommentAdapter
    val commentViewModel: CommentViewModel by viewModels {
        CommentViewModelFactory()
    }
    var _binding: FragmentPlaceBinding? = null
    val binding: FragmentPlaceBinding get() = _binding!!
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place = activity?.intent?.getSerializableExtra("place") as Place

        for (i in place.images!![0].split(",")) {
            val containerIv = CardView(mContext)
            val iv = ImageView(mContext)

            Glide.with(this).load(i.replace("[","").replace("\\","").replace("\"",""))
                .into(iv)

            iv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(550,550))
            containerIv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(600,600))
            containerIv.addView(iv)
            binding.llImages.addView(containerIv)
        }

        binding.tvNameP.text = place.name
        binding.tvLocationP.text = place.location
        binding.rBar.rating = place.rating.toFloat()
        binding.tvDescription.text = place.description
        lat = place.latitude
        lon = place.longitude
        favorite = place.favorite

        commentsAdapter = CommentAdapter(place.code)

        binding.rvComments.apply {
            adapter = commentsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            commentViewModel.commentStateFlow.collect { commentState: CommentState ->
                setState(commentState)
            }
        }

        commentViewModel.getData()

        gpsRecord()
    }

    private fun setState(commentState: CommentState) {
        when(commentState) {
            CommentState.Loading -> binding.progressBar.visibility = View.VISIBLE
            is CommentState.Success -> {
                binding.progressBar.visibility = View.GONE
                val commentsList = commentState.data
                commentsAdapter.submitList(commentsList)
            }
            is CommentState.Failure -> {
                Toast.makeText(mContext, "Failure!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun gpsRecord() {
        locManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext, R.string.no_permission, Toast.LENGTH_LONG).show()
            return
        }
        val loc = locManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        showPosition(loc)
        locListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                showPosition(location)
            }
            override fun onStatusChanged(provider: String, status:
            Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        locManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, locListener!!)
    }

    private fun showPosition(loc: Location?): Array<String> {
        val data: Array<String>
        if (loc != null) {
//            lat = loc.latitude.toString()
//            lon = loc.longitude.toString()
            data = arrayOf(loc.longitude.toString(), loc.latitude.toString())
        } else {
            data = arrayOf(40.4167754.toString(), (-3.7037901999999576).toString(), "Default location")
            lat = 40.4167754.toString()
            lon = (-3.7037901999999576).toString()
        }
        return data
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaceFragment()
    }
}