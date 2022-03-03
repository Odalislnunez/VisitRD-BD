package es.usj.mastertsa.onunez.visitrd.presentation.view.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_place.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.view.Window
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.R
import es.usj.mastertsa.onunez.visitrd.databinding.ActivityPlaceBinding
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceActivity : AppCompatActivity() {
    private var lat: String = "40.4167754"
    private var lon: String = "-3.7037901999999576"
    private var locManager: LocationManager? = null
    private var locListener: LocationListener? = null
    private var favorite: Boolean = true
    private val bindings: ActivityPlaceBinding by lazy {
        ActivityPlaceBinding.inflate(layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_menu, menu)

        setFavoriteIcon(menu?.findItem(R.id.btnFavoritePlace)!!)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(bindings.root)

        val place = intent.getSerializableExtra("place") as Place

        for (i in place.images!![0].split(",")) {
            val containerIv = CardView(this)
            val iv = ImageView(this)

            Glide.with(this).load(i.replace("[","").replace("\\","").replace("\"",""))
                .into(iv)

            iv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(550,550))
            containerIv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(600,600))
            containerIv.addView(iv)
            bindings.llImages.addView(containerIv)
        }

        bindings.tvNameP.text = place.name
        bindings.tvLocationP.text = place.location
        bindings.rBar.rating = place.rating.toFloat()
        bindings.tvDescription.text = place.description
        lat = place.latitude
        lon = place.longitude
        favorite = place.favorite

//        bindings.lvComments.webViewClient = WebViewClient()
//        bindings.lvComments.loadUrl(place.comments!!)

        gpsRecord()
    }

    private fun gpsRecord() {
        locManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.no_permission, Toast.LENGTH_LONG).show()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.btnFavoritePlace -> {
                favorite = !favorite
                setFavoriteIcon(item)
            }
            R.id.btnShare -> {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Prueba")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(intent, null)
                startActivity(shareIntent)
            }
            R.id.btnVisit -> {
                try {
                    val intent = Intent(this@PlaceActivity, MapsActivity::class.java)
                    intent.putExtra("Longitude", lon)
                    intent.putExtra("Latitude", lat)
                    startActivity(intent)
                } catch (ex: Exception) {
                    Toast.makeText(this@PlaceActivity, ex.message, Toast.LENGTH_LONG).show()
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteIcon(menuItem: MenuItem) {
        val id = if(favorite)
            R.drawable.ic_favorite_white_24dp
        else
            R.drawable.ic_favorite_border_white_24dp

        menuItem.icon = ContextCompat.getDrawable(this, id)
    }
}