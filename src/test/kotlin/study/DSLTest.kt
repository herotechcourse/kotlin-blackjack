package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class DSLTest {
    @ParameterizedTest
    @ValueSource(strings = ["Johnny", "Jack"])
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("Jason")
                company("NEXTSTEP")
            }

        assertThat(person.name).isEqualTo("Jason")
        assertThat(person.company).isEqualTo("NEXTSTEP")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("Jason")
                skills {
                    soft("Teamwork", "Speech", "Leadership")
                    hard("Kotlin", "Gradle")
                }
            }

        assertThat(person.skills.soft).containsExactly("Teamwork", "Speech", "Leadership")
        assertThat(person.skills.hard).containsExactly("Kotlin", "Gradle")
    }

    @ParameterizedTest
    @CsvSource(
        "English,5",
        "Korean,0",
    )
    fun languages(
        lang: String,
        lvl: Int,
    ) {
        val person =
            introduce {
                name("Lisa")
                languages { lang level lvl }
            }

        assertThat(person.languages).containsExactly(Language(lang, lvl))
    }

    @Test
    fun fullDsl() {
        val person =
            introduce {
                name("Lisa")
                company("DH")
                skills {
                    soft("Communication")
                    hard("Docker")
                }
                languages {
                    "Spanish" level 4
                    "German" level 2
                }
            }

        assertThat(person).isEqualTo(
            Person(
                name = "Lisa",
                company = "DH",
                skills =
                    Skills(
                        soft = listOf("Communication"),
                        hard = listOf("Docker"),
                    ),
                languages =
                    listOf(
                        Language("Spanish", 4),
                        Language("German", 2),
                    ),
            ),
        )
    }

    @Test
    fun testOuterScopeNotAccessibleInsideInnerScope() {
        val builder = PersonBuilder()

        builder.skills {
            soft("Communication")
            // The following line does NOT compile because name is inaccessible due to DSL marker
            // name("Jason")
        }
    }

    @Test
    fun testFunctionsFromCurrentReceiverAreAccessible() {
        val skillsBuilder = SkillsBuilder()
        skillsBuilder.soft("Teamwork")
        skillsBuilder.hard("Kotlin")
    }
}
