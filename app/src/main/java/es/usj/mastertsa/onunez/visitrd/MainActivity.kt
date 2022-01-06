package es.usj.mastertsa.onunez.visitrd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_place.*
import java.io.IOException
import org.json.JSONArray
import java.io.InputStream
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private val placeList = ArrayList<Place>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
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
                        jsonObject.optString("name") + "\n",
                        jsonObject.optString("location") + "\n",
                        jsonObject.optString("description") + "\n",
                        listOf<String>(jsonObject.optString("images")),
                        listOf<Comment>(Comment("", "")),
                        jsonObject.optString("latitude") + "\n",
                        jsonObject.optString("longitude") + "\n",
                        jsonObject.optDouble("rating")
                    )
                )
            }
        }
        catch (e: IOException){
            e.printStackTrace()
        }

        val adapter = PlacesAdapter(this, placeList)

        lvMain.adapter = adapter

        lvMain.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PlaceActivity::class.java)
            intent.putExtra("place", placeList[position])
            startActivity(intent)
        }

//        val imagesBahia = listOf<Int>(R.drawable.bahia, R.drawable.bahia2, R.drawable.bahia3, R.drawable.bahia4)
//        val imagesMonumento = listOf<Int>(R.drawable.monumento, R.drawable.monumento2, R.drawable.monumento3, R.drawable.monumento4, R.drawable.monumento5)
//
//        val commentsBahia = listOf<Comment>(Comment("Rodolfo06","Hola soy Rodolfo y quiero ser blanco."),
//            Comment("Ariel06","Hola soy Ariel y quiero ser negro."),
//            Comment("Aleyda1101","La mejor playa que he visitado de mi país, soy de la costa norte y viajé kilómetros para visitarle y no me arrepiento."),
//            Comment("Odalislnunez","Hermosas aguas cristalinas del sur de mi bello país."),
//            Comment("Aleyda2204","Soy extranjera, y no me gustó mucho, la temporada en la que fuí habían muchas algas."))
//        val commentsMonumento = listOf<Comment>(Comment("Bianel01","Hola soy Bianel y me gusta el pollo frito."),
//            Comment("Javier11","Hola soy Javier y no quiero rebajar."),
//            Comment("Andres0826","Hermoso monumento histórico en mi bella ciudad corazón."),
//            Comment("Yamile0903","Ya estoy jarta del monumento, todas mis citas me llevan allí."))
//
//        val firstPlace = Place("Bahía de las Aguilas", "Parque Nacional de Jaragua, Prov. Pedernales",
//            "Es una bahía del mar Caribe, localizada en la parte central de la costa meridional de la isla de Santo Domingo, y considerada como la playa más cristalina del mundo.",
//            imagesBahia, commentsBahia, "17.8405556", "-71.6541667")
//        val secondPlace = Place("Monumento a los Héroes de la Restauración", "Centro de la Ciudad, Prov. Santiago",
//            "Mejor conocido como el Monumento de Santiago es el monumento erigido en la ciudad de Santiago de los Caballeros para conmemorar el 100 aniversario de la independencia del país.",
//            imagesMonumento, commentsMonumento,"19.455698","-70.696685")
    }
}