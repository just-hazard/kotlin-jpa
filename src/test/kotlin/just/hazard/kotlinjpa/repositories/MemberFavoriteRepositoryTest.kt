package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Favorite
import just.hazard.kotlinjpa.domain.Member
import just.hazard.kotlinjpa.domain.MemberFavorite
import just.hazard.kotlinjpa.domain.Station
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import javax.persistence.EntityManager

@DataJpaTest
class MemberFavoriteRepositoryTest {

    @Autowired
    private lateinit var memberFavoriteRepository: MemberFavoriteRepository

    @Autowired
    private lateinit var stationRepository: StationRepository

    @Autowired
    private lateinit var favoriteRepository: FavoriteRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var memberFavorite: MemberFavorite

    private lateinit var memberFavorite1: MemberFavorite

    @BeforeEach
    fun setUp() {
        memberFavorite = MemberFavorite(
            Favorite(Station("서울역"), Station("의정부역")),
            (Member(20, "email", "1234")))

        memberFavorite1 = MemberFavorite(
            Favorite(Station("중곡역"), Station("건대입구역")),
            (Member(20, "email1", "1234")))

        memberFavoriteRepository.saveAll(listOf(memberFavorite, memberFavorite1))
    }

    @AfterEach
    fun tearDown() {
        memberFavoriteRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table member_favorite alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 생성() {
        데이터_체크(memberFavorite, 20, "email", "1234", "서울역", "의정부역")
    }

    @Test
    fun 사용자별_중복된_즐겨찾기_지하철역_등록() {
        val actual  = MemberFavorite(
            favoriteRepository.findById(1L).get(),
            (memberRepository.findById(1L).get()))
        assertThrows<DataIntegrityViolationException> {
            memberFavoriteRepository.save(actual)
        }
        entityManager.clear()
    }

    @Test
    fun 조회() {
        val actual = memberFavoriteRepository.findById(1L)
        데이터_체크(actual.get(), 20, "email", "1234", "서울역", "의정부역")
        val actual_id2 = memberFavoriteRepository.findById(1L)
        데이터_체크(actual_id2.get(), 20, "email1", "1234", "중곡역", "건대입구역")
    }

    @Test
    fun 수정() {
        memberFavorite.updateFavorite(Favorite(stationRepository.findByName("중곡역"), stationRepository.findByName("건대입구역")))
        val actual = memberFavoriteRepository.save(memberFavorite)
        데이터_체크(actual, 20, "email", "1234", "중곡역", "건대입구역")
    }

    @Test
    fun 삭제() {
        memberFavoriteRepository.delete(memberFavorite)
        val actual = memberFavoriteRepository.findById(1L)
        assertThat(actual).isEmpty
    }

    private fun 데이터_체크(actual: MemberFavorite, age: Int, email: String, password: String, departureStation: String, arrivalStation: String) {
        assertAll(
            { assertThat(actual.member.age).isEqualTo(age) },
            { assertThat(actual.member.email).isEqualTo(email) },
            { assertThat(actual.member.password).isEqualTo(password) },
            { assertThat(actual.favorite.departureStation.name).isEqualTo(departureStation) },
            { assertThat(actual.favorite.arrivalStation.name).isEqualTo(arrivalStation) }
        )
    }
}
