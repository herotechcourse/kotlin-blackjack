package blackjack.dsl

data class Person(val name: String, val company: String?, val skills: Skills)

data class Skills(val soft: MutableList<String>, val hard: MutableList<String>)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills)
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
