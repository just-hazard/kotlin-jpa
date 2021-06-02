package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Favorite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

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

    @Test
    fun 저장() {
        데이터_저장()
        아이디_날짜_체크(favorite)
    }

    // read
    @Test
    fun 가져오기() {
        데이터_저장()
        val actual = 데이터_조회()
        아이디_날짜_체크(actual.get())

    }

    private fun 데이터_조회() = favoriteRepository.findById(1L)

    @Test
    fun 수정() {
        데이터_저장()
        val present = 데이터_조회()
        val beforeDate = present.get().modifiedDate
        val after = favoriteRepository.save(present.get())
        assertThat(beforeDate).isNotEqualTo(after.modifiedDate)

    }

    private fun 아이디_날짜_체크(inputFavorite: Favorite) {
        assertAll(
            { assertThat(inputFavorite.id).isNotNull() },
            { assertThat(inputFavorite.createdDate).isNotNull() },
            { assertThat(inputFavorite.modifiedDate).isNotNull() },
        )
    }

    private fun 데이터_저장() {
        favorite = favoriteRepository.save(Favorite())
    }

}
