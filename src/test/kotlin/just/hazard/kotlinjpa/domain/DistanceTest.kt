package just.hazard.kotlinjpa.domain


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DistanceTest {

    private lateinit var distance: Distance

    @Test
    fun 양수값_체크() {
        assertThrows<IllegalArgumentException> {
            distance = Distance(-1)
        }
    }
}
