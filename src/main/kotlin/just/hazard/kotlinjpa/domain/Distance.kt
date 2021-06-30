package just.hazard.kotlinjpa.domain

class Distance(distance: Int) {

    init {
        validationPositiveNumber(distance)
    }

    private fun validationPositiveNumber(distance: Int) {
        if(distance < 0)
            throw IllegalArgumentException("양수만 입력이 가능합니다.")
    }
}
