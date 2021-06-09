package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Station
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import javax.persistence.EntityManager

@DataJpaTest
class StationRepositoryTest {

    @Autowired
    private lateinit var stationRepository: StationRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var station: Station

    @BeforeEach
    fun setUp() {
        station = stationRepository.save(Station("의정부역"))
    }

    @AfterEach
    fun tearDown() {
        stationRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table station alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 저장() {
        지하철역_데이터_체크(station, "의정부역")
    }

    // AfterEach 예외하고 돌릴수 있는지 찾아보기
    @Test
    @Disabled
    fun 지하철역_이름_중복_예외() {
        Assertions.assertThatThrownBy {
            stationRepository.save(Station("의정부역"))
        }.isInstanceOf(DataIntegrityViolationException::class.java)
    }

    @Test
    fun 가져오기() {
        val 의정부역 = 지하철역_조회()
        지하철역_데이터_체크(의정부역, "의정부역")
    }

    @Test
    fun 수정() {
        val 의정부역 = 지하철역_조회()
        의정부역.name = "도봉산역"
        station = stationRepository.save(의정부역)
        지하철역_데이터_체크(station, "도봉산역")
    }

    @Test
    fun 삭제() {
        stationRepository.delete(station)
        Assertions.assertThatThrownBy {
            지하철역_조회()
        }.isInstanceOf(NoSuchElementException::class.java)
    }

    private fun 지하철역_조회() = stationRepository.findById(1L).get()

    private fun 지하철역_데이터_체크(actual: Station, name: String) {
        assertAll(
            { assertThat(station.name).isEqualTo(name) },
            { assertThat(actual.id).isNotNull() },
            { assertThat(actual.createdDate).isNotNull() },
            { assertThat(actual.modifiedDate).isNotNull() },
        )
    }
}
