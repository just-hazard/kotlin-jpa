package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MemberFavorite(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val favorite: Favorite,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var member: Member
): Base() {
    fun updateMember(member: Member) {
        this.member = member
        member.memberFavorites.add(this)
    }
}
