package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Game {
    private val deck = Deck()

    fun startGame() {
        val names = InputView.askPlayerNames()
        val players = Players(names.map { Player(it) })
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        participants.dealInitialCards(deck)
        OutputView.showInitialCards(dealer, players.toList())

        players.forEach { player ->
            while (player.isStillInGame() && InputView.askToDrawCard(player.name)) {
                player.drawAndUpdate(deck)
                OutputView.showPlayerCards(player)
            }
        }

        if (dealer.mustDraw()) {
            OutputView.showDealerDrawsCard()
        }
        participants.dealerTurn(deck)

        OutputView.showFinalHands(dealer, players.toList())
        OutputView.showFinalResults(dealer, players.toList())
    }
}
