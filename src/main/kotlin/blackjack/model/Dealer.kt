package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    private val deck = Deck()

    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards < BlackJackValues.DEALER_STAND_CONDITION

    fun selfDrawCard() {
        drawCard(deck.giveCard())
    }

    fun dealCardToPlayer(player: Player) {
        player.drawCard(deck.giveCard())
    }

    fun selfDrawInitialCards() {
        repeat(BlackJackValues.FIRST_HAND_CARDS) {
            selfDrawCard()
        }
    }

    fun dealInitialCardsToPlayers(players: List<Player>) {
        repeat(BlackJackValues.FIRST_HAND_CARDS) {
            players.forEach { it -> dealCardToPlayer(it) }
        }
    }
}
