package blackjack.controller

import blackjack.model.Bet
import blackjack.model.Game
import blackjack.model.Results
import blackjack.model.cards.CardDeck
import blackjack.model.table.Dealer
import blackjack.model.table.Player
import blackjack.model.table.Players
import blackjack.view.ErrorMessage
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {
    fun runGame() {
        val players = initializePlayers()
        val dealer = Dealer()
        val deck = CardDeck()

        players.forEach { setBettingAmount(it) }

        val game = Game(players, dealer, deck)
        game.play()

        displayCardsAndTotal(dealer, players)
        calculateAndPrintResults(dealer, players)
    }

    private fun initializePlayers(): Players {
        repeat(MAX_TRIES) {
            try {
                val playerNames = InputView.readNames()
                val players = Players(playerNames.map { Player(it) })
                return players
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    private fun setBettingAmount(player: Player) {
        repeat(MAX_TRIES) {
            try {
                player.bet = Bet.of(InputView.readBettingAmount(player))
                return
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    private fun calculateAndPrintResults(
        dealer: Dealer,
        players: Players,
    ) {
        val (dealerResult, playersResult) = Results(dealer, players).calculate()

        OutputView.displayFinalResultString()
        OutputView.displayResult(dealerResult, dealer)
        playersResult.forEach { OutputView.displayResult(it.value, it.key) }
    }

    private fun displayCardsAndTotal(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.displayParticipantStatus(dealer)
        players.forEach { OutputView.displayParticipantStatus(it) }
    }

    companion object {
        const val MAX_TRIES = 5
    }
}
