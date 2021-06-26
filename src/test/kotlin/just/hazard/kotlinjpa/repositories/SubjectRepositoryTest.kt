package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.example.Academy
import just.hazard.kotlinjpa.domain.example.Subject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private lateinit var subjectRepository: SubjectRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var subject: Subject

    @BeforeEach
    fun setUp() {
        subject = Subject(0,"이름",Academy(0, "아카데미"))
        subjectRepository.save(subject)
    }

    @Test
    @Transactional
    fun 쿼리확인() {
        val subject = entityManager.find(Subject::class.java, 1L)
        println(subject.academy.name)
    }
}
