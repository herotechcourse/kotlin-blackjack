package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {
    fun run() {
        val playerNames = InputView.askPlayerNames()
        val players = playerNames.map { name ->
            val bet = InputView.getBettingAmount(name)
            Player(name = name, bet = bet)
        }
        val dealer = Dealer()

        OutputView.displayDealing(players.map { it.name })

        dealer.giveTwoCardsTo(players)

        OutputView.displayDealerFirstTwoCards(dealer.getFirstTwoCards())
        OutputView.displayPlayerHands(players)

        OutputView.displayLineBreak()

        players.forEach { player ->
            GameLogic.handlePlayerTurn(dealer, player)
        }

        OutputView.displayLineBreak()
        OutputView.displayPlayerHands(players)

        dealer.playDealerTurn()

        OutputView.displayLineBreak()
        OutputView.displayDealerStatus(dealer)
        players.forEach { OutputView.displayPlayerStatus(it) }

        val dealerScore = dealer.getScore()
        val isDealerBusted = dealer.isBusted()

        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()

        players.forEach { player ->
            val playerScore = player.getScore()
            val isPlayerBusted = player.isBusted()
            val bet = player.bet
            val result = GameLogic.getGameResult(playerScore, dealerScore, isPlayerBusted, isDealerBusted)
            val earnings = BettingLogic.calculateEarnings(result, player.isBlackJack(), bet)
            player.earnings = earnings

            when (result) {
                GameResult.WIN -> dealerLosses++
                GameResult.LOSE -> dealerWins++
                GameResult.DRAW -> {}
            }

            OutputView.displayPlayerResult(player.name, result.label)
        }

        OutputView.displayDealerResult(dealerWins, dealerLosses)

        val dealerEarnings = players.sumOf { -it.earnings }
        OutputView.displayFinalEarnings(players, dealerEarnings)
    }
}
