package blackjack.blackjackgame

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.view.GameResultView
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {
    fun run() {
        val players = createPlayers()
        val dealer = Dealer()

        dealInitialCards(players, dealer)
        handlePlayerTurns(players, dealer)
        handleDealerTurn(dealer)
        displayGameResults(players, dealer)
    }

    private fun createPlayers(): List<Player> {
        val names = InputView.askPlayerNames()
        return names.map { name ->
            val bet = InputView.getBettingAmount(name)
            Player(name = name, bet = bet)
        }
    }

    private fun dealInitialCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        OutputView.displayDealing(players.map { it.name })
        dealer.giveTwoCardsTo(players)

        OutputView.displayDealerFirstTwoCards(dealer.getFirstTwoCards())
        OutputView.displayPlayerHands(players)
        OutputView.displayLineBreak()
    }

    private fun handlePlayerTurns(
        players: List<Player>,
        dealer: Dealer,
    ) {
        players.forEach { player ->
            TurnManager.handlePlayerTurn(dealer, player)
        }
        OutputView.displayLineBreak()
        OutputView.displayPlayerHands(players)
    }

    private fun handleDealerTurn(dealer: Dealer) {
        TurnManager.handleDealerTurn(dealer)
        OutputView.displayLineBreak()
        OutputView.displayDealerStatus(dealer)
    }

    private fun displayGameResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()

        players.forEach { player ->
            val result = dealer.judge(player)
            val label = GameResultView.from(result).label
            val earnings = result.calculateEarnings(player.bet.amount)
            player.earnings = earnings

            when (result) {
                GameResult.BLACKJACK_WIN -> dealerLosses++
                GameResult.WIN -> dealerLosses++
                GameResult.LOSE -> dealerWins++
                GameResult.DRAW -> {}
            }

            OutputView.displayPlayerResult(player.name, label)
            OutputView.displayPlayerStatus(player)
        }

        OutputView.displayDealerResult(dealerWins, dealerLosses)
        OutputView.displayFinalEarnings(players, players.sumOf { -it.earnings })
    }
}
