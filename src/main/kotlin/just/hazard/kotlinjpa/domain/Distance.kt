package just.hazard.kotlinjpa.domain

import javax.persistence.Embeddable

@Embeddable
class Distance(var distance: Int = 0) {

    init {
        validationPositiveNumber(distance)
    }

    private fun validationPositiveNumber(distance: Int) {
        if(distance < 0)
            throw IllegalArgumentException("양수만 입력이 가능합니다.")
    }
}
