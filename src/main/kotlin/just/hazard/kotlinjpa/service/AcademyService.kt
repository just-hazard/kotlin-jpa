package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.Academy
import just.hazard.kotlinjpa.repositories.AcademyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors.toList
import java.util.stream.Collectors.toSet

@Service
class AcademyService(private val academyRepository: AcademyRepository) {

    @Transactional(readOnly = true)
    fun findAllSubjectNames() : MutableList<String> {
        return extractSubjectNames(academyRepository.findAll())
    }

    @Transactional(readOnly = true)
    fun findAllJPQLSubjectNames() : MutableSet<String>? {
        return extractSubjectNamesTypeSet(academyRepository.findAllFetchJoin())
    }

    @Transactional(readOnly = true)
    fun findAllEntityGraphSubjectNames() : MutableList<String> {
        return extractSubjectNames(academyRepository.findAllEntityGraph())
    }

    private fun extractSubjectNamesTypeSet(academies: MutableSet<Academy>) : MutableSet<String>? {
        return academies.stream()
            .map {
                it.subjects[0].name
            }.collect(toSet())
    }

    private fun extractSubjectNames(academies: MutableList<Academy>) : MutableList<String> {
        return academies.stream()
            .map {
                it.subjects[0].name
            }.collect(toList())
    }
}
