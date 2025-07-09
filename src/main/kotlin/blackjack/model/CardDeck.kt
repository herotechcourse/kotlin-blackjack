package blackjack.model

data class CardDeck(private val hold: Hold) {
    constructor() : this(initPokerCards())

    companion object {
        private fun initPokerCards(): Hold {
            return Hold(listOfCardDeck().shuffled().toSet())
        }

        private fun listOfCardsWith(suit: Suit): List<Card> {
            var index = 1
            return List(13) {
                Card(suit, index++)
            }
        }

        fun listOfCardDeck(): List<Card> {
            return listOfCardsWith(Suit.SPADES)
                .plus(listOfCardsWith(Suit.HEART))
                .plus(listOfCardsWith(Suit.CLUBS))
                .plus(listOfCardsWith(Suit.DIAMONDS))
        }
    }

    fun numberOfCards(): Int {
        return hold.cards.size
    }

    fun getCards() = hold.cards.toList()
}
