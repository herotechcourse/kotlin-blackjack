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
        val players =
            Players(
                names.map {
                    val bet = InputView.askPlayerBet(it)
                    Player(it, bet)
                },
            )

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

        while (dealer.mustDraw()) {
            OutputView.showDealerDrawsCard()
            dealer.drawCard(deck.draw())
        }
        participants.dealerTurn(deck)

        OutputView.showFinalHands(dealer, players.toList())

        participants.evaluateResults()
        OutputView.showFinalResults(dealer, players.toList())
    }
}
