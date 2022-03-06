package es.usj.mastertsa.onunez.visitrd.data.repository

import es.usj.mastertsa.onunez.visitrd.data.repository.room.CommentDao
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceSqLiteHelper
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommentRepositoryImpl(
    private val placeSqLiteHelper: PlaceSqLiteHelper,
    private val commentDao: CommentDao
): CommentRepository {
    private val db = placeSqLiteHelper.writableDatabase

    override suspend fun getComments(placeCode: Int): Flow<List<Comment>> {

        return commentDao.getComments(placeCode).map { commentList ->
            commentList.map { comment -> PlaceMapper.mapCommentFromDbToDomain(comment) }
        }
    }

    override suspend fun addComments(comment: Comment) {

        val commentToAdd = PlaceMapper.mapDbToDomainFromComment(comment)
        commentDao.insertComment(commentToAdd)
    }
}