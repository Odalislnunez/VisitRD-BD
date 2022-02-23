package es.usj.mastertsa.onunez.visitrd.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import es.usj.mastertsa.onunez.visitrd.presentation.view.PlacesAdapter
import es.usj.mastertsa.onunez.visitrd.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import org.json.JSONArray
import java.io.InputStream
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val placeList = ArrayList<Place>()
    private lateinit var adapter: PlacesAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json: String?

        try {
            val inputStream: InputStream = assets.open("Places.json")
            inputStream.buffered(5)
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                placeList.add(
                    Place(
                        jsonObject.optString("name"),
                        jsonObject.optString("location"),
                        jsonObject.optString("description"),
                        listOf<String>(jsonObject.optString("images")),
                        jsonObject.optString("comments"),
                        jsonObject.optString("latitude"),
                        jsonObject.optString("longitude"),
                        jsonObject.optDouble("rating")
                    )
                )
            }
        }
        catch (e: IOException){
            e.printStackTrace()
        }

        adapter = PlacesAdapter(this, placeList)

        lvMain.adapter = adapter

        lvMain.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PlaceActivity::class.java)
            intent.putExtra("place", adapter.getItem(position))
            startActivity(intent)
        }
    }
}
