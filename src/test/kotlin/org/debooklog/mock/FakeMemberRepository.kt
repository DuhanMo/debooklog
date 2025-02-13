package org.debooklog.mock

import org.debooklog.core.member.model.Member
import org.debooklog.core.member.port.MemberRepository
import java.time.Instant.now

class FakeMemberRepository : MemberRepository {
    private var autoGeneratedId: Long = 0
    private val data: MutableList<Member> = mutableListOf()

    override fun save(member: Member): Member {
        if (member.id == 0L) {
            val newMember =
                Member(
                    id = ++autoGeneratedId,
                    name = member.name,
                    email = member.email,
                    socialId = member.socialId,
                    provider = member.provider,
                    profileImage = member.profileImage,
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = null,
                    isDeleted = false,
                )
            data.add(newMember)
            return newMember
        } else {
            data.removeIf { it.id == member.id }
            data.add(member)
            return member
        }
    }

    override fun getById(id: Long): Member {
        return data.first { it.id == id }
    }

    override fun findAll(): List<Member> {
        return data
    }

    override fun findByEmail(email: String): Member? {
        return data.firstOrNull { it.email == email }
    }

    override fun findById(id: Long): Member? {
        return data.firstOrNull { it.id == id }
    }
}
