package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {
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
            GameLogic.handlePlayerTurn(dealer, player)
        }
        OutputView.displayLineBreak()
        OutputView.displayPlayerHands(players)
    }

    private fun handleDealerTurn(dealer: Dealer) {
        dealer.playDealerTurn()
        OutputView.displayLineBreak()
        OutputView.displayDealerStatus(dealer)
    }

    private fun displayGameResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        val dealerScore = dealer.getScore()
        val isDealerBusted = dealer.isBusted()
        val isDealerBlackjack = dealer.isBlackJack()

        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()

        players.forEach { player ->
            val result =
                GameLogic.getGameResult(
                    playerScore = player.getScore(),
                    dealerScore = dealerScore,
                    isPlayerBusted = player.isBusted(),
                    isDealerBusted = isDealerBusted,
                    isPlayerBlackjack = player.isBlackJack(),
                    isDealerBlackjack = isDealerBlackjack,
                )

            val earnings = BettingLogic.calculateEarnings(result, player.isBlackJack(), player.bet)
            player.earnings = earnings

            when (result) {
                GameResult.WIN -> dealerLosses++
                GameResult.LOSE -> dealerWins++
                GameResult.DRAW -> {}
            }

            OutputView.displayPlayerResult(player.name, result.label)
            OutputView.displayPlayerStatus(player)
        }

        OutputView.displayDealerResult(dealerWins, dealerLosses)
        OutputView.displayFinalEarnings(players, players.sumOf { -it.earnings })
    }
}
