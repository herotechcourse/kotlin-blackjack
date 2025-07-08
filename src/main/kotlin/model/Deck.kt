package model

class Deck {
    private val cards = generateCards()

    // for testing
    fun getCards(): Set<Card> {
        return cards.toSet()
    }

    // TODO: Catch the IllegalArgumentException
    fun pop(): Card {
        require(cards.isNotEmpty()) { "The deck is empty" }
        return cards.first().also { cards.remove(it) }
    }

    // TODO refactor is needed
    private fun generateCards(): MutableSet<Card> {
        val cards = mutableSetOf<Card>()
//        Rank.entries.forEach { rank -> Suite.entries.forEach { _cards.add(Card(rank, it)) } }
//        Rank.entries.map { rank -> Suite.entries.map { suite -> Card(rank, suite) } }
        for (rank in Rank.entries) {
            for (suite in Suite.entries) {
                cards.add(Card(rank, suite))
            }
        }
        return cards.shuffled().toMutableSet()
    }
}
