package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(uniqueConstraints = [UniqueConstraint(name = "member_favorite_uinque", columnNames = ["id","favorite_id","member_id"])])
class MemberFavorite(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var favorite: Favorite,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var member: Member
): Base() {
    fun updateMember(member: Member) {
        this.member = member
        member.memberFavorites.add(this)
    }

    fun updateFavorite(favorite: Favorite) {
        this.favorite = favorite
    }
}
