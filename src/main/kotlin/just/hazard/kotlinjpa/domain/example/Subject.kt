package just.hazard.kotlinjpa.domain.example

import javax.persistence.*

@Entity
class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", foreignKey = ForeignKey(name = "FK_SUBJECT_ACADEMY"))
    var academy: Academy,
) {
    fun updateAcademy(academy: Academy) {
        this.academy = academy
        academy.subjects.add(this)
    }
}
