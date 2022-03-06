package es.usj.mastertsa.onunez.visitrd.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import es.usj.mastertsa.onunez.visitrd.R
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.presentation.view.fragments.PlaceFragment

class PlaceActivity : AppCompatActivity() {
    private var lat: String = "40.4167754"
    private var lon: String = "-3.7037901999999576"
    private var favorite: Boolean = true

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.place_menu, menu)

        setFavoriteIcon(menu?.findItem(R.id.btnFavoritePlace)!!)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        val place = intent.getSerializableExtra("place") as Place

        favorite = place.favorite == "true"

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerPlaceView, PlaceFragment())
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
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