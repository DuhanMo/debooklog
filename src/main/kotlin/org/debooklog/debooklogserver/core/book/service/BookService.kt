package org.debooklog.debooklogserver.core.book.service

import org.debooklog.debooklogserver.core.book.model.Book
import org.debooklog.debooklogserver.core.book.model.BookRegisterCommand
import org.debooklog.debooklogserver.core.book.port.BookRepository
import org.debooklog.debooklogserver.core.bookshelf.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository,
    private val bookshelfRepository: BookshelfRepository,
) {
    fun register(command: BookRegisterCommand) {
        val books = bookRepository.findAllByMemberId(command.memberId)
        val bookshelf =
            bookshelfRepository.findByMemberId(command.memberId)
                ?: throw NoSuchElementException("책장을 찾지 못했습니다")
        val book = Book(command, bookshelf.id!!)
        book.validateForDuplicate(books)
        bookRepository.save(book)
    }

    fun delete(
        bookId: Long,
        memberId: Long,
    ) {
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.delete(memberId))
    }

    fun reading(
        bookId: Long,
        memberId: Long,
    ) {
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.reading(memberId))
    }

    fun done(
        bookId: Long,
        memberId: Long,
    ) {
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.done(memberId))
    }
}
