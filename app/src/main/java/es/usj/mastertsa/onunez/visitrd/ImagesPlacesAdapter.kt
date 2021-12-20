package es.usj.mastertsa.onunez.visitrd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_images_place.view.*

class ImagesPlacesAdapter (private val mContext: Context, private val imagesList: List<Int>) : ArrayAdapter<Int>(mContext, 0, imagesList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_images_place, parent, false)

        layout.ivF.setImageResource(imagesList[0])
        layout.ivS.setImageResource(imagesList[1])

        return layout
    }
}