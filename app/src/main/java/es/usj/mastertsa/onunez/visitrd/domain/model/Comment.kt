package es.usj.mastertsa.onunez.visitrd.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment (val id: Int, val place_code: Int, val comment: String) : Parcelable