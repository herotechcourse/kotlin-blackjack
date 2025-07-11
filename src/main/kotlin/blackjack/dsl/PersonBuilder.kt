package blackjack.dsl

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)

data class Skills(val soft: MutableList<String>, val hard: MutableList<String>)

data class Languages(val languages: MutableMap<String, Int>)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder() {
    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills = Skills(softSkills, hardSkills)
}

class LanguagesBuilder() {
    private var languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Languages = Languages(languages)
}
