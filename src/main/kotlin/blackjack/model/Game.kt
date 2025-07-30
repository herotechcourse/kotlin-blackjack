package blackjack.model

import blackjack.controller.Controller.Companion.MAX_TRIES
import blackjack.model.cards.CardDeck
import blackjack.model.states.Running
import blackjack.model.table.Dealer
import blackjack.model.table.Player
import blackjack.model.table.Players
import blackjack.view.ErrorMessage
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(private val players: Players, private val dealer: Dealer, private val deck: CardDeck) {
    fun play() {
        firstRound()
        dealToPlayers()
        dealToDealer()
    }

    private fun firstRound() {
        OutputView.displayFirstRoundMessage(players)
        repeat(2) {
            dealer.state = dealer.state.draw(deck.hit())
            players.forEach { it.state = it.state.draw(deck.hit()) }
        }
        OutputView.displayFirstCardMessage(dealer)
        players.forEach { OutputView.displayAllCardsMessage(it) }
    }

    private fun dealToDealer() {
        while (dealer.state is Running) {
            if (dealer.shouldDraw()) {
                OutputView.displayDealerDrawMessage()
                dealer.state = dealer.state.draw(deck.hit())
            } else {
                dealer.state = dealer.state.stay()
            }
        }
    }

    private fun dealToPlayers() {
        players.forEach { dealToPlayer(it) }
    }

    private fun dealToPlayer(player: Player) {
        while (player.state is Running) {
            if (wantsToDraw(player)) {
                player.state = player.state.draw(deck.hit())
            } else {
                player.state = player.state.stay()
            }
            OutputView.displayAllCardsMessage(player)
        }
    }

    private fun wantsToDraw(player: Player): Boolean {
        repeat(MAX_TRIES) {
            try {
                return InputView.promptForDraw(player)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                return@repeat
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }
}
