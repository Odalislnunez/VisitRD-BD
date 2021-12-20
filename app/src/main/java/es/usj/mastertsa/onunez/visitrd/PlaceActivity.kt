package es.usj.mastertsa.onunez.visitrd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_place.*
import kotlinx.android.synthetic.main.activity_place.tvNameP
import kotlinx.android.synthetic.main.item_place.*

class PlaceActivity : AppCompatActivity() {

//    private lateinit var bindings : ActivityPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
//        bindings = ActivityPlaceBinding.inflate(layoutInflater)
//        setContentView(bindings.root)
//        bindings.vpImages.adapter = AdminPageAdapter(this)

        val place = intent.getSerializableExtra("place") as Place
//        val adapterImages = ImagesPlacesAdapter(this, place.images)
        val adapterComment = CommentsPlacesAdapter(this, place.comments)

        tvNameP.text = place.name
        tvLocationP.text = place.location
//        vpImages.adapter = adapterImages
        tvDescription.text = place.description
        lvComments.adapter = adapterComment
    }
}