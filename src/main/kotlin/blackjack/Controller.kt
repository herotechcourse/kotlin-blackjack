package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private var players: List<Player> = mutableListOf()
    private val dealer: Player = Player(GamblerInfo("dealer"))
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            OutputView.displayNamesOfPlayers(players)
            roundOne()
            OutputView.displayCardsOfDealer(dealer)
            players.forEach { OutputView.displayCardsOfPlayers(it) }
            players.forEach { playerTakesTurn(it) }
            dealerTakesTurn()

            OutputView.displayCardsOfPlayersWithScore(dealer)
            players.forEach { OutputView.displayCardsOfPlayersWithScore(it) }

            OutputView.displayFinalResultsHeading()

            calculateAndDisplayResults()
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers() {
        players =
            processPlayerNames().map { Player(it) }
    }

    private fun roundOne() {
        dealer.addCard(deck.drawCard(2))
        players.forEach { it.addCard(deck.drawCard(2)) }
    }

    fun processPlayerNames(): List<GamblerInfo> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names
                    .split(",")
                    .map(String::trim)
                    .map { it -> GamblerInfo(it) } // to avoid abundant function: caller
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

    fun calculateAndDisplayResults() {
        var win: List<Player>
        var lose: List<Player>
        var draw: List<Player>
        if (dealer.score > BLACKJACK_SCORE) {
            lose = players.filter { it.score > BLACKJACK_SCORE }
            win = players - lose
            draw = emptyList()
        } else {
            win = players.filter { it.score <= BLACKJACK_SCORE && it.score > dealer.score }
            draw = players.filter { it.score == dealer.score }
            lose = players - win - draw
        }
        OutputView.displayPlayerResult(lose.size, win.size, draw.size)
        win.forEach { OutputView.displayPlayerResult(it.name, true) }
        lose.forEach { OutputView.displayPlayerResult(it.name, false) }
        draw.forEach { OutputView.displayPlayerResult(it.name, false) }
    }

    companion object {
        private const val MAX_ATTEMPTS = 5
        private const val BLACKJACK_SCORE = 21
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
    }
}
