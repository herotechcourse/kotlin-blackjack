package study

// Purpose: This Kotlin DSL is designed to create a Person object in a declarative format.

// The "block" term is common in Kotlin for a code lambda passed to a DSL.
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: List<Language>
)

data class Skills(
    val soft: List<String>,
    val hard: List<String>
)

data class Language(val name: String, val level: Int)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private val languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages += LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    // Infix extension function to add a Language with name (String) and level (Int) to the languages list
    infix fun String.level(value: Int) {
        languages += Language(this, value)
    }

    fun build(): List<Language> = languages
}

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills += value
    }

    fun hard(value: String) {
        hardSkills += value
    }

    fun build(): Skills = Skills(softSkills, hardSkills)
}
