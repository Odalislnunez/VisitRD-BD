package es.usj.mastertsa.onunez.visitrd.data.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment
import es.usj.mastertsa.onunez.visitrd.domain.repository.CommentRepository

class CommentRepositoryImpl(): CommentRepository {
    private val comments = getFakeData()

    override fun getComments(): List<Comment> {
        val newComments = mutableListOf<Comment>()
        newComments.addAll(comments)

        return newComments
    }

    override fun addComments(comment: Comment) {
        comments.add(comment)
    }

    fun getFakeData(): MutableList<Comment> {
        val comments = mutableListOf<Comment>()
        comments.add( Comment(0,0,"Comentario de prueba."))
        comments.add( Comment(1,0,"Comentario de prueba2."))

        return comments
    }
}