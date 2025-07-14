package blackjack.model

import blackjack.view.InputView
import blackjack.view.OutputView

class Dealer(
    override val name: String = "Dealer",
    override var handCards: HandCards = HandCards(),
    private val deck: CardDeck,
    private val players: Players,
) : Participant() {
    fun shouldDraw(): Boolean {
        return handCards.total <= Rules.DEALER_DRAW_THRESHOLD
    }

    fun serviceParticipants() {
        serviceDealer()
        servicePlayers(players)
    }

    private fun serviceDealer() {
        while (this.shouldDraw()) {
            OutputView.displayDealerDrawMessage()
            this.drawCard(deck)
        }
    }

    private fun servicePlayers(players: Players) {
        players.forEach { dealCards(it) }
    }

    private fun dealCards(player: Player) {
        while (player.isNotBusted() && InputView.promptForDraw(player)) {
            player.drawCard(deck)
            OutputView.displayAllCardsMessage(player)
        }
    }

    override fun toString(): String {
        return name
    }
}
