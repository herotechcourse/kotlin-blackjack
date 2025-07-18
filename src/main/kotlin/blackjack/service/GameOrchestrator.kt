package blackjack.service

import blackjack.controller.Controller.Companion.BLACKJACK_SCORE
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.OutputView

class GameOrchestrator(private val deck: Deck, private val inputProcessor: InputProcessor) {
    companion object {
        private const val DEALER_THRESHOLD = 16
        private const val INITIAL_CARDS_COUNT = 2
    }

    fun dealInitialCards(
        dealer: Player,
        players: List<Player>,
    ) {
        dealer.addCard(deck.drawCard(INITIAL_CARDS_COUNT))
        players.forEach { it.addCard(deck.drawCard(INITIAL_CARDS_COUNT)) }
    }

    fun runPlayerTurn(player: Player) {
        var wantsToHit = false
        while (player.score <= BLACKJACK_SCORE) {
            wantsToHit = inputProcessor.processHitOrStay(player.name)
            when {
                !wantsToHit -> break
                else -> {
                    player.addCard(deck.drawCard())
                    OutputView.displayCardsOfPlayers(player)
                }
            }
        }
        when {
            !wantsToHit && player.cards.size == INITIAL_CARDS_COUNT -> {
                OutputView.displayCardsOfPlayers(player)
            }
        }
    }

    fun runDealerTurn(dealer: Player) {
        if (dealer.score <= DEALER_THRESHOLD) {
            OutputView.displayDealersTurn()
            while (dealer.score <= DEALER_THRESHOLD) {
                dealer.addCard(deck.drawCard())
            }
        }
    }
}
