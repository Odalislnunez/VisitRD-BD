package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.onunez.visitrd.data.repository.CommentRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetCommentUseCase

class CommentViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CommentRepositoryImpl()
        val addComment = AddCommentUseCase(repository)
        val getComment = GetCommentUseCase(repository)
        return CommentViewModel(addComment, getComment) as T
    }
}