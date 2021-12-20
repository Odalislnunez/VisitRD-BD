package es.usj.mastertsa.onunez.visitrd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_place.*
import kotlinx.android.synthetic.main.activity_place.ivFirst
import kotlinx.android.synthetic.main.item_images_place.*

class PlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        val place = intent.getSerializableExtra("place") as Place
        val adapterComment = CommentsPlacesAdapter(this, place.comments)

        tvNameP.text = place.name
        tvLocationP.text = place.location
        ivFirst.setImageResource(place.images[0])
        ivSecond.setImageResource(place.images[1])
        ivThird.setImageResource(place.images[2])
        ivFourth.setImageResource(place.images[3])
        ivFifth.setImageResource(place.images[4])
        tvDescription.text = place.description
        lvComments.adapter = adapterComment
    }
}