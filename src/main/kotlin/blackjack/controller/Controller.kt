package blackjack.controller

import blackjack.model.Deck
import blackjack.model.FinalResult
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private var players: List<Player> = mutableListOf()
    private val dealer: Player = createDummyPlayer("dealer")
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            OutputView.displayNamesOfPlayers(players)
            roundOne()
            OutputView.displayCardsOfDealer(dealer)
            players.forEach { OutputView.displayCardsOfPlayers(it) }
            OutputView.printEmptyLine()
            players.forEach { playerTakesTurn(it) }
            dealerTakesTurn()
            OutputView.printEmptyLine()
            OutputView.displayCardsOfPlayersWithScore(dealer)
            players.forEach { OutputView.displayCardsOfPlayersWithScore(it) }
            OutputView.printEmptyLine()
            OutputView.displayFinalResultsHeading()
            showResults(FinalResult(dealer, players))
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    // internal fun: allows testing, while still hiding it from external consumers
    internal fun createPlayers() {
        players =
            processPlayerNames().map { createPlayerWithStatus(it, processBetAmount(it)) }
    }

    // For testing purposes
    internal fun getPlayers(): List<Player> = players

    private fun roundOne() {
        dealer.addCard(deck.drawCard(2))
        players.forEach { it.addCard(deck.drawCard(2)) }
    }

    fun processPlayerNames(): List<String> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names
                    .split(",")
                    .map(String::trim)
                    .filter { it.isNotBlank() }
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    fun processBetAmount(name: String): Int {
        repeat(MAX_ATTEMPTS) {
            try {
                val betAmount = InputView.getBetAmount(name)
                return betAmount
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    private fun dealerTakesTurn() {
        while (dealer.score <= 16) {
            dealer.addCard(deck.drawCard())
            OutputView.displayDealersTurn()
        }
    }

    private fun playerTakesTurn(player: Player) {
        var answer = false
        while (player.score <= BLACKJACK_SCORE) {
            answer = processHitOrStay(player)
            if (!answer) {
                break
            }
            player.addCard(deck.drawCard())
            OutputView.displayCardsOfPlayers(player)
        }
        if (!answer && player.cards.size == 2) {
            OutputView.displayCardsOfPlayers(player)
        }
    }

    private fun showResults(finalResult: FinalResult) {
    }

    fun processHitOrStay(player: Player): Boolean {
        repeat(MAX_ATTEMPTS) {
            try {
                return InputView.getHitOrStand(player.name).isHitOrStand()
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    fun String.isHitOrStand(): Boolean {
        return when (this) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("The answer must be y or n.")
        }
    }

    private fun createDummyPlayer(name: String): Player {
        return Player(name)
    }

    private fun createPlayerWithStatus(
        name: String,
        betAmount: Int,
    ): Player {
        return Player(name, betAmount)
    }

    companion object {
        private const val MAX_ATTEMPTS = 5
        const val BLACKJACK_SCORE = 21
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
    }
}
