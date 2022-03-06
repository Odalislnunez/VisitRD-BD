package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetCommentUseCase
import es.usj.mastertsa.onunez.visitrd.presentation.view.states.CommentState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentViewModel(
    val addCommentUseCase: AddCommentUseCase,
    val getCommentUseCase: GetCommentUseCase
): ViewModel() {
    private val commentsMutableStateFlow: MutableStateFlow<CommentState> =
        MutableStateFlow(CommentState.Loading)
    val commentStateFlow: StateFlow<CommentState> = commentsMutableStateFlow

    fun getData(placeCode: Int) {
        viewModelScope.launch {
            delay(1000)
            val newComments = getCommentUseCase.getComments(placeCode).collect { commentList ->
                commentsMutableStateFlow.emit(CommentState.Success(commentList))
            }
        }
    }

    fun addComment(comment: Comment){
        viewModelScope.launch {
            addCommentUseCase.addComment(comment)
        }
    }
}