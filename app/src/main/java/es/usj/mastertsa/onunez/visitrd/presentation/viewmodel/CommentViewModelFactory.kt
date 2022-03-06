package es.usj.mastertsa.onunez.visitrd.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.onunez.visitrd.data.repository.CommentRepositoryImpl
import es.usj.mastertsa.onunez.visitrd.data.repository.site.PlaceSqLiteHelper
import es.usj.mastertsa.onunez.visitrd.domain.usecases.AddCommentUseCase
import es.usj.mastertsa.onunez.visitrd.domain.usecases.GetCommentUseCase

class CommentViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val sqLiteHelper = PlaceSqLiteHelper(context)
        val repository = CommentRepositoryImpl(sqLiteHelper)
        val addComment = AddCommentUseCase(repository)
        val getComment = GetCommentUseCase(repository)
        return CommentViewModel(addComment, getComment) as T
    }
}