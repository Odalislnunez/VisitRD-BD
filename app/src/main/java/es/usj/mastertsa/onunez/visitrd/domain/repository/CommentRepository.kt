package es.usj.mastertsa.onunez.visitrd.domain.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getComments(placeCode: Int): Flow<List<Comment>>

    suspend fun addComments(comment: Comment)
}