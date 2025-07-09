package blackjack.model

class Player(
    private val person: Person,
    val hand: Hand,
) {
    constructor(name: String) : this(Person(name), Hand())
}
