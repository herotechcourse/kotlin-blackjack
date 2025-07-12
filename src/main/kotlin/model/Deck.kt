package model

class Deck {
    private val cards = generateCards()

    fun getCards(): Set<Card> {
        return cards.toSet()
    }

    // TODO: Catch the IllegalArgumentException
    fun pop(): Card {
        require(cards.isNotEmpty()) { "The deck is empty" }
        return cards.first().also { cards.remove(it) }
    }

    private fun generateCards(): MutableSet<Card> {
        return Suite.entries.flatMap { suite -> Rank.entries.map { rank -> Card(rank, suite) } }.shuffled().toMutableSet()
    }
}
