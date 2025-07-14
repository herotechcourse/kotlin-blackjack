package blackjack.model

class Deck() {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    init {
        create()
        shuffle()
    }

    private fun create() {
        val suits = Suit.entries
        val ranks = Rank.entries

        suits.forEach { suit ->
            ranks.forEach { rank ->
                when (rank) {
                    Rank.ACE -> _cards.add(AceCard(suit))
                    Rank.JACK, Rank.QUEEN, Rank.KING -> _cards.add(FaceCard(suit, rank))
                    else -> _cards.add(NumberCard(suit, rank))
                }
            }
        }
    }

    private fun shuffle() = _cards.shuffle()

    fun drawCard(): Card {
        check(_cards.isNotEmpty()) { "[FATAL]: Deck cards should not be empty." }

        return _cards.removeLast()
    }
}
