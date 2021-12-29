package es.usj.mastertsa.onunez.visitrd

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
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
import es.usj.mastertsa.onunez.visitrd.databinding.ActivityPlaceBinding

class PlaceActivity : AppCompatActivity() {
    private var lat: String = "40.4167754"
    private var lon: String = "-3.7037901999999576"
    private var locManager: LocationManager? = null
    private var locListener: LocationListener? = null
    private val bindings: ActivityPlaceBinding by lazy {
        ActivityPlaceBinding.inflate(layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(bindings.root)

        val place = intent.getSerializableExtra("place") as Place
        val adapterComment = CommentsPlacesAdapter(this, place.comments)

//        val ll = findViewById<View>(R.id.llImages) as LinearLayout

        for (i in place.images) {
            val containerIv = CardView(this)
            val iv = ImageView(this)
            iv.setImageResource(i)
            iv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(550,550))
            containerIv.layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams(600,600))
            containerIv.addView(iv)
            bindings.llImages.addView(containerIv)
        }

        bindings.tvNameP.text = place.name
        bindings.tvLocationP.text = place.location
        bindings.tvDescription.text = place.description
        bindings.lvComments.adapter = adapterComment
        lat = place.latitude
        lon = place.longitude

        gpsRecord()
    }

    private fun gpsRecord() {
        locManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
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
            lat = loc.latitude.toString()
            lon = loc.longitude.toString()
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
            R.id.btnVisit -> {
                try {
                    val intent = Intent(this@PlaceActivity, MapsActivity::class.java)
                    intent.putExtra("Longitud", lat)
                    intent.putExtra("Latitud", lon)
                    startActivity(intent)
                } catch (ex: Exception) {
                    Toast.makeText(this@PlaceActivity, ex.message, Toast.LENGTH_LONG).show()
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}