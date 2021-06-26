package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Favorite
import just.hazard.kotlinjpa.domain.Station
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import javax.persistence.EntityManager

@DataJpaTest
class FavoriteRepositoryTest {

    @Autowired
    private lateinit var favoriteRepository: FavoriteRepository

    private lateinit var favorite: Favorite

    @Autowired
    private lateinit var entityManager: EntityManager

    @BeforeEach
    fun setUp() {
       favorite = 데이터_저장()
    }

    @AfterEach
    fun tearDown() {
        favoriteRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table favorite alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 저장() {
        아이디_날짜_체크(favorite)
    }

    // read
    @Test
    fun 가져오기() {
        favorite = 데이터_조회()
        아이디_날짜_체크(favorite)
    }

    @Test
    fun 수정() {
        val present = 데이터_조회()
        present.arrivalStation = Station("저스디스역")
        val beforeDate = present.modifiedDate
        present.modifiedDate = LocalDateTime.now()
        val after = favoriteRepository.save(present)
        assertThat(after.arrivalStation.name).isEqualTo("저스디스역")
        assertThat(beforeDate).isNotEqualTo(after.modifiedDate)
    }

    @Test
    fun 삭제() {
        favoriteRepository.delete(데이터_조회())
        val actual = favoriteRepository.findById(1L)
        assertThat(actual).isEmpty
    }


    private fun 데이터_조회(): Favorite = favoriteRepository.findById(1L).get()

    private fun 아이디_날짜_체크(inputFavorite: Favorite) {
        assertAll(
            { assertThat(inputFavorite.id).isNotNull() },
            { assertThat(inputFavorite.createdDate).isNotNull() },
            { assertThat(inputFavorite.modifiedDate).isNotNull() },
            { assertThat(inputFavorite.departureStation.name).isEqualTo("서울역") },
            { assertThat(inputFavorite.arrivalStation.name).isEqualTo("의정부역") },
        )
    }

    private fun 데이터_저장(): Favorite {
        return favoriteRepository.save(즐겨찾기_데이터())
    }

    private fun 즐겨찾기_데이터(): Favorite {
        return Favorite(Station("서울역"),Station("의정부역"))
    }
}
