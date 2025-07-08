package blackjack

data class CardDeck(val cards: Cards) {
    constructor() : this(initPokerCards()) {
    }

    companion object {
        private fun initPokerCards(): Cards {
            return Cards(listOfCardDeck().shuffled().toSet())
        }

        private fun listOfCardsWith(symbol: Symbol): List<Card> {
            var index = 1
            return List(13) {
                Card(symbol, index++)
            }
        }

        fun listOfCardDeck(): List<Card> {
            return listOfCardsWith(Symbol.SPADES)
                .plus(listOfCardsWith(Symbol.HEART))
                .plus(listOfCardsWith(Symbol.CLUBS))
                .plus(listOfCardsWith(Symbol.DIAMONDS))
        }
    }
}
