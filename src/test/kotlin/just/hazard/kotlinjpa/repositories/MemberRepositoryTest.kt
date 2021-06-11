package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Line
import just.hazard.kotlinjpa.domain.Member
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var member: Member

    @BeforeEach
    fun setUp() {
        member = memberRepository.save(Member(20, "email", "1234"))
    }

    @AfterEach
    fun tearDown() {
        memberRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table member alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 생성() {
        사용자_데이터_체크(member, 20, "email", "1234")
    }

    @Test
    fun 가져오기() {
        val actual = 사용자_조회()
        사용자_데이터_체크(actual.get(), 20, "email", "1234")
    }

    @Test
    fun 수정() {
        val before = member
        before.apply {
            this.age = 21
            this.email = "email1"
            this.password = "justhis"
        }

        val actual = memberRepository.save(before)
        사용자_데이터_체크(actual, 21, "email1", "justhis")
    }

    @Test
    fun 삭제() {
        memberRepository.delete(member)
        val actual = 사용자_조회()
        assertThat(actual).isEmpty
    }

    private fun 사용자_조회() = memberRepository.findById(1L)

    private fun 사용자_데이터_체크(actual: Member, age: Int, email: String, password: String) {
        assertAll(
            { Assertions.assertThat(member.age).isEqualTo(age) },
            { Assertions.assertThat(member.email).isEqualTo(email) },
            { Assertions.assertThat(member.password).isEqualTo(password) },
            { Assertions.assertThat(actual.id).isNotNull() },
            { Assertions.assertThat(actual.createdDate).isNotNull() },
            { Assertions.assertThat(actual.modifiedDate).isNotNull() },
        )
    }
}
