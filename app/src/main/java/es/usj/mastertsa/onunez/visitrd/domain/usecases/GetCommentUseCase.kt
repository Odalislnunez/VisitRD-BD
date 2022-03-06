package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow

class GetCommentUseCase(val repository: CommentRepository) {
    suspend fun getComments(placeCode: Int): Flow<List<Comment>> {
        return repository.getComments(placeCode)
    }
}