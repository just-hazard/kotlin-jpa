package just.hazard.kotlinjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class KotlinJpaApplication

fun main(args: Array<String>) {
    runApplication<KotlinJpaApplication>(*args)
}
