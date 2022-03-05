package es.usj.mastertsa.onunez.visitrd.presentation.view.states

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment

sealed class CommentState {
    object Loading : CommentState()
    data class Success(val data: List<Comment>) : CommentState()
    data class Failure(val exception: Throwable) : CommentState()
}
