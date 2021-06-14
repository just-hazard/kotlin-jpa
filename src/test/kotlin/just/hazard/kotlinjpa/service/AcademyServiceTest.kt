package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.Academy
import just.hazard.kotlinjpa.domain.Subject
import just.hazard.kotlinjpa.repositories.AcademyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AcademyServiceTest {
    @Autowired
    private lateinit var academyRepository : AcademyRepository

    @Autowired
    private lateinit var academyService: AcademyService

    @BeforeEach
    fun setUp() {
        val academies: MutableList<Academy> = mutableListOf()

        for(index in 1..10) {
            val academy = Academy(0,"아카데미${index}")
            academy.addSubject(Subject(0, "jpa${index}", academy))
            academies.add(academy)
        }
        academyRepository.saveAll(academies)
    }

//    @AfterEach
//    fun tearDown() {
//        academyRepository.deleteAll()
//    }

    @Test
    fun `N+1`() {
        val subjectNames = academyService.findAllSubjectNames()
        assertThat(subjectNames.size).isEqualTo(10)
    }

    @Test
    fun `JPQL N+1 해결`() {
        val subjectNames = academyService.findAllJPQLSubjectNames()
        assertThat(subjectNames.size).isEqualTo(10)
    }

    @Test
    fun `EntityGraph N+1 해결`() {
        val subjectNames = academyService.findAllEntityGraphSubjectNames()
        assertThat(subjectNames.size).isEqualTo(10)
    }
}
