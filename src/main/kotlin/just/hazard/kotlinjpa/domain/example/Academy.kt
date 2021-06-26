package just.hazard.kotlinjpa.domain.example

import javax.persistence.*

@Entity
class Academy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name="academy_id")
    var subjects: MutableList<Subject> = mutableListOf()
) {
    fun addSubject(subject: Subject) {
        this.subjects.add(subject)
        subject.updateAcademy(this)
    }
}
