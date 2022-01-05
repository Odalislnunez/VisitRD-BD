package es.usj.mastertsa.onunez.visitrd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_comments_place.view.*

class CommentsPlacesAdapter(private val mContext: Context, private val commentList: List<Comment>, private val view_more : String) : ArrayAdapter<Comment>(mContext, 0, commentList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_comments_place, parent, false)

        val comment = commentList[position]

        layout.tvUser.text = comment.user
        if(comment.comment.length>40){
            layout.tvComment.text = comment.comment.substring(0,40)+ view_more
        }
        else{
            layout.tvComment.text = comment.comment
        }

        return layout
    }
}