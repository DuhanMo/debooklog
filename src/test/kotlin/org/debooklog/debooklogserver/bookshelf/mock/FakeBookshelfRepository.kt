package org.debooklog.debooklogserver.bookshelf.mock

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import java.time.LocalDateTime.now

class FakeBookshelfRepository : BookshelfRepository {
    private var autoGeneratedId: Long = 0
    private val data: MutableList<Bookshelf> = mutableListOf()

    override fun save(bookshelf: Bookshelf): Bookshelf {
        if (bookshelf.id == null || bookshelf.id == 0L) {
            val newBookshelf =
                Bookshelf(
                    id = ++autoGeneratedId,
                    memberId = bookshelf.memberId,
                    name = bookshelf.name,
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = bookshelf.deletedAt,
                )
            data.add(newBookshelf)
            return bookshelf
        } else {
            data.removeIf { it.id == bookshelf.id }
            data.add(bookshelf)
            return bookshelf
        }
    }

    override fun getById(id: Long): Bookshelf {
        return data.first { it.id == id }
    }

    override fun findAll(): List<Bookshelf> {
        return data.filter { it.deletedAt == null }
    }
}
