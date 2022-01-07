package es.usj.mastertsa.onunez.visitrd

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.item_place.view.*

class PlacesAdapter(private val mContext: Context, private val placeList: List<Place>) : ArrayAdapter<Place>(mContext, 0, placeList) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_place, parent, false)

        val place = placeList[position]

        layout.tvNamePlace.text = place.name
        layout.tvLocationPlace.text = place.location
        var image: String? = place.images?.get(0)?.let {
            it.split(",")[0].replace("[","").replace("\\","").replace("\"","")
        }
        Glide.with(mContext).load(image).into(layout.ivPlace)
        layout.rBPlace.rating = place.rating!!.toFloat()

        return layout
    }
}
