package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class Dealer : Participant("Dealer") {

    private val deck = Deck()

    fun giveTwoCardsTo(participants: List<Participant>) {
        repeat(2) {
            giveCardTo(this)
            participants.forEach { giveCardTo(it) }
        }
    }

    fun giveCardTo(participant: Participant) {
        val card = deck.drawCard()
        participant.addCard(card)
    }

    fun askPlayerDraw(player: Player) {
        while (true) {
            val wantsToDraw = InputView.askPlayerWantsToDraw(player.name)
            if (wantsToDraw) {
                giveCardTo(player)
                OutputView.displayPlayerHand(player)
                if (player.getScore() >= 21) break
            } else {
                break
            }
        }
    }

    fun playDealerTurn() {
        while (shouldDraw()) {
            OutputView.displayDealerDraw()
            giveCardTo(this)
            OutputView.displayDealerStatus(this)
        }
    }

    fun shouldDraw(): Boolean {
        return (this.getScore()) <= 16
    }
}
