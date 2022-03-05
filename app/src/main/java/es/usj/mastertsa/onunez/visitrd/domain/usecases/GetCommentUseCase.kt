package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository

class GetCommentUseCase(val repository: CommentRepository) {
    fun getComments(): List<Comment>{
        return repository.getComments()
    }
}