package blackjack.model

class Dealer : Participant() {
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

    fun dealInitialCardsToPlayers(players: Players) {
        repeat(BlackJackValues.FIRST_HAND_CARDS) {
            players.getPlayers().forEach { it -> dealCardToPlayer(it) }
        }
    }
}
