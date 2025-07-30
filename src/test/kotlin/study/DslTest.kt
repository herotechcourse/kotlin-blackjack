package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *      name("Jason")
 *      company("DH")
 *      skills {
 *          soft("teamwork")
 *          hard("kotlin")
 *      }
 *      languages {
 *          "Korean" level 5
 *          "English" level 3
 *      }
 * }
 */
class DslTest {
    @Test
    fun `test name fun`() {
        val person: Person =
            introduce {
                name("Jason")
            }
        assertThat(person.name).isEqualTo("Jason")
    }

    @ParameterizedTest
    @ValueSource(strings = ["DH", "JetBrains", "Google"])
    fun `test company fun`(args: String) {
        val person: Person =
            introduce {
                company(args)
            }
        assertThat(person.company).isEqualTo(args)
    }

    @Test
    fun `test skills fun`() {
        val person: Person =
            introduce {
                skills {
                    soft("communication")
                    hard("java")
                }
            }
        assertThat(person.skills.soft).isEqualTo("communication")
        assertThat(person.skills.hard).isEqualTo("java")
    }

    @Test
    fun `test languages fun`() {
        val person: Person =
            introduce {
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.languages.table).containsEntry("Korean", 5)
        assertThat(person.languages.table).containsEntry("English", 3)
    }

    @Test
    fun everything()  {
        val person: Person =
            introduce {
                name("Jason")
                company("Google")
                skills {
                    soft("communication")
                    hard("java")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.name).isNotEmpty()
        assertThat(person.company).isNotEmpty()
        assertThat(person.skills.soft).isEqualTo("communication")
    }
}

fun introduce(function: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(function).build()
}

class PersonBuilder() {
    var name: String = ""
    var company: String = ""
    var skills: Skills = Skills("", "")
    var languages: Languages = Languages(emptyMap())

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: SkillBuilder.() -> Unit) {
        val builder = SkillBuilder()
        builder.init()
        this.skills = builder.build()
    }

    fun languages(init: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(init).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder {
    lateinit var soft: String
    lateinit var hard: String

    fun hard(hard: String) {
        this.hard = hard
    }

    fun soft(soft: String) {
        this.soft = soft
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

class LanguageBuilder {
    private var languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages,
)

data class Skills(val soft: String, val hard: String)

data class Languages(var table: Map<String, Int>)
