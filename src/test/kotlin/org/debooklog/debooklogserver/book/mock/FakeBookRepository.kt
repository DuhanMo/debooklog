package org.debooklog.debooklogserver.book.mock

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.service.port.BookRepository

class FakeBookRepository : BookRepository {
    private var autoGeneratedId: Long = 0
    private val data: MutableList<Book> = mutableListOf()

    override fun findAllByMemberId(memberId: Long): List<Book> {
        return data.filter { it.memberId == memberId }
    }

    override fun save(book: Book): Book {
        if (book.id == null || book.id == 0L) {
            val newBook =
                Book(
                    id = ++autoGeneratedId,
                    memberId = book.memberId,
                    title = book.title,
                    author = book.author,
                    isbn = book.isbn,
                    thumbnail = book.thumbnail,
                    createdAt = book.createdAt,
                    updatedAt = book.updatedAt,
                )
            data.add(newBook)
            return newBook
        } else {
            data.removeIf { it.id == book.id }
            data.add(book)
            return book
        }
    }
}
