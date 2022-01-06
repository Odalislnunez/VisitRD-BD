package es.usj.mastertsa.onunez.visitrd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.item_place.view.*

class PlacesAdapter(private val mContext: Context, private val placeList: List<Place>) : ArrayAdapter<Place>(mContext, 0, placeList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_place, parent, false)

        val place = placeList[position]

        layout.tvNamePlace.text = place.name
        layout.tvLocationPlace.text = place.location
        var image: String = ""
        place.images?.get(0)?.let {
            image = it
        }
        image = image.split(",")[0]
//        image = "https://lh5.googleusercontent.com/p/AF1QipM6m4VuHejk_PRf2N41zLcyJG_4FxAx6cl2DZ54=w1080-k-no"
        Glide.with(mContext).load(image).into(layout.ivPlace)
//        place.images?.get(0)?.let { layout.ivPlace.setImageResource(it) }
        layout.rBPlace.rating = place.rating!!.toFloat()

        return layout
    }
}
