package blackjack.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*introduce {
    name("Vito")
    company("Delivery Hero")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Italian" level 5
        "English" level 3
        "German" level 4
    }
}*/

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["Vito", "Test"])
    fun name(value: String) {
        val person =
            introduce {
                name(value)
                skills {
                }
                languages {
                }
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("Vito")
                company("DH")
                skills {
                }
                languages {
                }
            }
        assertThat(person.name).isEqualTo("Vito")
        assertThat(person.company).isEqualTo("DH")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("Vito")
                company("DH")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                }
            }
        assertThat(person.name).isEqualTo("Vito")
        assertThat(person.company).isEqualTo("DH")
        assertThat(person.skills.soft.first()).isEqualTo("A passion for problem solving")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("Vito")
                company("DH")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Italian" level 5
                    "English" level 3
                    "German" level 4
                }
            }
        assertThat(person.name).isEqualTo("Vito")
        assertThat(person.company).isEqualTo("DH")
        assertThat(person.skills.soft.first()).isEqualTo("A passion for problem solving")
        assertThat(person.languages.languages["Italian"]).isEqualTo(5)
    }
}
