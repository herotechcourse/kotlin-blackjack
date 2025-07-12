package blackjack.dsl

fun main() {
    val person =
        introduce {
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
        }
    println(person)
}
