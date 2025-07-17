import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/* {
    name("Alex")
    company("DH")
    skills {
        soft("Flexibility")
        soft("Sharing candies")
        hard("Kotlin")
    }
    languages {
        "Spanish" level 5
        "English" level 4
        "German" level 3
    }
}*/

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> = languages
}

class SkillBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skill = Skill(soft, hard)
}

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: List<Language>? = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

data class Language(val name: String, val level: Int)

data class Skill(val soft: MutableList<String>?, val hard: MutableList<String>?)

data class Person(val name: String?, val company: String?, val skills: Skill?, val languages: List<Language>?)

class DslTest {

    @Test
    fun company() {
        val person =
            introduce {
                name("Alex")
                company("DH")
            }
        assertThat(person.name).isEqualTo("Alex")
        assertThat(person.company).isEqualTo("DH")
    }

    @Test
    fun skills() {
        val softSkills = listOf("Flexibility", "Sharing candies")
        val hardSkills = listOf("Kotlin")
        val person =
            introduce {
                skills {
                    soft("Flexibility")
                    soft("Sharing candies")
                    hard("Kotlin")
                }
            }
        assertThat(person.skills?.soft).isEqualTo(softSkills)
        assertThat(person.skills?.hard).isEqualTo(hardSkills)
    }

    @Test
    fun languages() {
        val expectedLanguages =
            listOf(
                Language("Spanish", 5),
                Language("English", 4),
                Language("German", 3),
            )
        val person =
            introduce {
                languages {
                    "Spanish" level 5
                    "English" level 4
                    "German" level 3
                }
            }
        assertThat(person.languages).isEqualTo(expectedLanguages)
    }
}
