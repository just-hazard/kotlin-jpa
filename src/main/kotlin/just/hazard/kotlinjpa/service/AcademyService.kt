package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.Academy
import just.hazard.kotlinjpa.repositories.AcademyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.toList

@Service
class AcademyService(private val academyRepository: AcademyRepository) {

    @Transactional(readOnly = true)
    fun findAllSubjectNames() : MutableList<String> {
        return extractSubjectNames(academyRepository.findAll())
    }

    private fun extractSubjectNames(academies: MutableList<Academy>) : MutableList<String> {
        return academies.stream()
            .map {
                it.subjects[0].name
            }.collect(toList())
    }
}
