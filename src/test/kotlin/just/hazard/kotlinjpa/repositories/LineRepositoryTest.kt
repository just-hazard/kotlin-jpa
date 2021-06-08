package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Line
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    private lateinit var lineRepository : LineRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var line: Line

    @BeforeEach
    fun setUp() {
       line = lineRepository.save(Line("파란색","일호선"))
    }

    @AfterEach
    fun tearDown() {
        lineRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table line alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 저장() {
        라인_데이터_체크(line, "파란색", "일호선")
    }

    @Test
    fun 가져오기() {
        val actual = 데이터_조회()
        라인_데이터_체크(actual, "파란색", "일호선")
    }

    @Test
    fun 수정() {
        val 라인 = 데이터_조회()
        라인.color = "초록색"
        라인.name = "이호선"
        val actual = lineRepository.save(라인)
        라인_데이터_체크(actual, "초록색", "이호선")
    }

    @Test
    fun 삭제() {
        lineRepository.delete(line)
        val line = lineRepository.findById(1L)
        assertThat(line).isEmpty
    }

    private fun 데이터_조회() = lineRepository.findById(1L).get()

    private fun 라인_데이터_체크(actual: Line, color: String, name: String) {
        assertAll(
            { assertThat(line.color).isEqualTo(color) },
            { assertThat(line.name).isEqualTo(name) },
            { assertThat(actual.id).isNotNull() },
            { assertThat(actual.createdDate).isNotNull() },
            { assertThat(actual.modifiedDate).isNotNull() },
        )
    }
}
