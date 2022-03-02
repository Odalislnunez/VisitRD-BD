package es.usj.mastertsa.onunez.visitrd.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.usj.mastertsa.onunez.visitrd.R
import es.usj.mastertsa.onunez.visitrd.databinding.ItemPlaceBinding
import es.usj.mastertsa.onunez.visitrd.domain.model.Place
import kotlinx.android.synthetic.main.item_place.view.*

class HomeAdapter(private val mContext: Context?): ListAdapter<Place, HomeAdapter.HomeViewHolder>(PlacesDiffUtilCallback) {

    inner class HomeViewHolder(val binding: ItemPlaceBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val place = getItem(position)
        holder.binding.tvNamePlace.text = place.name
        holder.binding.tvLocationPlace.text = place.location
        var image: String? = place.images?.get(0)?.let {
            it.split(",")[0].replace("[","").replace("\\","").replace("\"","")
        }
        if (mContext != null) {
            Glide.with(mContext).load(image).into(holder.binding.ivPlace)
        }
        holder.binding.rBPlace.rating = place.rating!!.toFloat()
        holder.binding.btnFavorite.setImageResource(setFavoriteIcon(place.favorite))
        holder.binding.btnFavorite.setOnClickListener {
            holder.binding.btnFavorite.setImageResource(setFavoriteIcon(!place.favorite))
            place.favorite = !place.favorite
        }
    }

    private fun setFavoriteIcon(favorite: Boolean): Int {
        return if(favorite)
            R.drawable.ic_favorite_red_24dp
        else
            R.drawable.ic_favorite_border_red_24dp
    }
}

object PlacesDiffUtilCallback: DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.code == newItem.code
    }
}