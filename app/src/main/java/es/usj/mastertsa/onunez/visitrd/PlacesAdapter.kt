package es.usj.mastertsa.onunez.visitrd

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.item_place.view.*

class PlacesAdapter(private val mContext: Context, private var placeList: List<Place>) : ArrayAdapter<Place>(mContext, 0, placeList), Filterable {
    private var mPlace: List<Place> = placeList

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

    override fun getCount(): Int {
        return placeList.size
    }

    override fun getItem(position: Int): Place? {
        return placeList[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                placeList = filterResults.values as List<Place>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values =
                    if (queryString.isNullOrEmpty()) mPlace
                    else {
                        placeList = mPlace
                        placeList.filter {
                            it.name.toLowerCase().contains(queryString) ||
                                    it.location.toLowerCase().contains(queryString)
                        }
                    }
                return filterResults
            }
        }
    }
}
