package org.debooklog.debooklogserver.member.mock

import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import java.time.LocalDateTime.now

class FakeMemberRepository : MemberRepository {
    private var autoGeneratedId: Long = 0
    private val data: MutableList<Member> = mutableListOf()

    override fun save(member: Member): Member {
        if (member.id == null || member.id == 0L) {
            val newMember =
                Member(
                    id = ++autoGeneratedId,
                    name = member.name,
                    socialId = member.socialId,
                    provider = member.provider,
                    createdAt = now(),
                    updatedAt = now(),
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
}
