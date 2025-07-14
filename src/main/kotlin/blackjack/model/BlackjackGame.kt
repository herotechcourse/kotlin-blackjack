package blackjack.model

class BlackjackGame {
    companion object {
        fun setUpTable(
            dealer: Dealer,
            players: List<Player>,
            cardDeck: CardDeck,
        ) {
            players.forEach { player ->
                repeat(2) {
                    player.state = player.state.draw(cardDeck.drawCard())
                }
            }
            repeat(2) { dealer.state = dealer.state.draw(cardDeck.drawCard()) }
        }
    }
}
