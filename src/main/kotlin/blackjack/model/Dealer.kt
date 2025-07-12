package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    private val deck = Deck()

    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards <= 16

    fun selfDrawCard() {
        drawCard(deck.giveCard())
    }

    fun dealCardToPlayer(player: Player) {
        player.drawCard(deck.giveCard())
    }

    fun selfDrawInitialCards() {
        repeat(2) {
            selfDrawCard()
        }
    }

    fun dealInitialCardsToPlayers(players: List<Player>) {
        repeat(2) {
            players.forEach { it -> dealCardToPlayer(it) }
        }
    }
}
