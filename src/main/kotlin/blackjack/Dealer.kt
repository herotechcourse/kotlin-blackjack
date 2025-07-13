package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class Dealer(private val deck: Deck) : Participant("Dealer") {

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
                if (ScoreCalculator.calculate(player) >=21) break
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
        return ScoreCalculator.calculate(this) <=16
    }
}

