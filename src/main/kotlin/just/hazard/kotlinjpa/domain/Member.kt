package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Member(
    var age: Int,
    @Column(unique = true)
    var email: String,
    var password: String,
    @OneToMany(mappedBy = "member", cascade = [CascadeType.PERSIST])
    var memberFavorites : MutableSet<MemberFavorite> = mutableSetOf()
) : Base() {
    fun addMemberFavorite(memberFavorite: MemberFavorite) {
        this.memberFavorites.add(memberFavorite)
        memberFavorite.updateMember(this)
    }
}
