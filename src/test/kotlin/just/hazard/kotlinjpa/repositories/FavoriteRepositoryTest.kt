package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Favorite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import javax.persistence.EntityManager

//@Test
//void save() {
//    Station expected = new Station("잠실역");
//    Station actual = stations.save(expected);
//    assertAll(
//        () -> assertThat(actual.getId()).isNotNull(),
//    () -> assertThat(actual.getName()).isEqualTo(expected.getName())
//    );
//}
//
//@Test
//void findByName() {
//    String expected = "잠실역";
//    stations.save(new Station(expected));
//    String actual = stations.findByName(expected).getName();
//    assertThat(actual).isEqualTo(expected);
//}
//}
@DataJpaTest
class FavoriteRepositoryTest {

    @Autowired
    private lateinit var favoriteRepository: FavoriteRepository

    private lateinit var favorite: Favorite

    @Autowired
    private lateinit var entityManager: EntityManager

    @AfterEach
    fun tearDown() {
        favoriteRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table favorite alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 저장() {
        아이디_날짜_체크(데이터_저장())
    }

    // read
    @Test
    fun 가져오기() {
        데이터_저장()
        favorite = 데이터_조회()
        아이디_날짜_체크(favorite)

    }

    @Test
    fun 수정() {
        데이터_저장()
        val present = 데이터_조회()
        val beforeDate = present.modifiedDate
        present.modifiedDate = LocalDateTime.now()
        val after = favoriteRepository.save(present)
        assertThat(beforeDate).isNotEqualTo(after.modifiedDate)
    }

    @Test
    fun 삭제() {
        데이터_저장()
        favoriteRepository.delete(데이터_조회())
//        entityManager.flush()
        val actual = favoriteRepository.findById(1L)
        assertThat(actual).isEmpty
    }


    private fun 데이터_조회(): Favorite = favoriteRepository.findById(1L).get()

    private fun 아이디_날짜_체크(inputFavorite: Favorite) {
        assertAll(
            { assertThat(inputFavorite.id).isNotNull() },
            { assertThat(inputFavorite.createdDate).isNotNull() },
            { assertThat(inputFavorite.modifiedDate).isNotNull() },
        )
    }

    private fun 데이터_저장(): Favorite {
        return favoriteRepository.save(Favorite())
    }

}
