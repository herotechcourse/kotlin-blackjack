package blackjack

data class CardDeck(val cards: Cards) {
    constructor() : this(initPokerCards())

    companion object {
        private fun initPokerCards(): Cards {
            return aA(Symbol.SPADES) + aA(Symbol.HEART) + aA(Symbol.CLUBS) + aA(Symbol.DIAMONDS)
        }

        private fun aA(symbol: Symbol): Cards {
            var index = 1
            return Cards(
                List(13) {
                    Card(symbol, index++)
                }.toSet(),
            )
        }
    }
}
