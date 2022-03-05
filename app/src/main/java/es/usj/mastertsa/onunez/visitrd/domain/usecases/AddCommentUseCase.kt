package es.usj.mastertsa.onunez.visitrd.domain.usecases

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository

class AddCommentUseCase(val repository: CommentRepository) {
    fun addComment(comment: Comment) {
        repository.addComments(comment)
    }
}