package org.debooklog.core.book.model

data class BookRank(
    val rank: Int,
    val isbn: String,
    val bookTitle: String,
    val count: Int,
)
