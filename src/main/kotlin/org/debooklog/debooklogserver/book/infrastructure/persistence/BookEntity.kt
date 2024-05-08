package org.debooklog.debooklogserver.book.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.common.domain.StringListConverter

@Entity
@Table(name = "books")
class BookEntity(
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "bookshelf_id")
    val bookshelfId: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "author")
    val author: String,
    @Column(name = "isbn")
    @Convert(converter = StringListConverter::class)
    val isbn: List<String>,
    @Column(name = "thumbnail")
    val thumbnail: String,
) : BaseEntity<BookEntity>() {
    companion object {
        fun from(book: Book): BookEntity {
            return BookEntity(
                memberId = book.memberId,
                bookshelfId = book.bookshelfId,
                title = book.title,
                author = book.author,
                isbn = book.isbn,
                thumbnail = book.thumbnail,
            )
        }
    }

    fun toModel(): Book {
        return Book(
            id = id,
            memberId = memberId,
            bookshelfId = bookshelfId,
            title = title,
            author = author,
            isbn = isbn,
            thumbnail = thumbnail,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
