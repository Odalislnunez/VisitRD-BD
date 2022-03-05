package es.usj.mastertsa.onunez.visitrd.domain.repository

import es.usj.mastertsa.onunez.visitrd.domain.model.Comment

interface CommentRepository {
    fun getComments(): List<Comment>

    fun addComments(comment: Comment)
}