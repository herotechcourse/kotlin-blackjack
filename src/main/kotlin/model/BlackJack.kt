package model

import view.InputView
import view.OutputView

class BlackJack {
    companion object {
        fun getPlayerNames(): List<String> {
            val players = InputView.requestPlayerNames()
            require(players.size <= 6) { "Maximum player names must be 6" }
            return players
        }

        fun initGame(
            players: List<Player>,
            dealer: Dealer,
        ) {
            players.forEach { player ->
                repeat(2) {
                    player.drawCard(dealer.dealCard())
                }
            }
            repeat(2) {
                dealer.drawCard(dealer.dealCard())
            }
        }

        fun runGame(
            players: List<Player>,
            dealer: Dealer,
        ) {
            playersTurn(players, dealer)
            dealersTurn(dealer)
        }

        private fun playersTurn(
            players: List<Player>,
            dealer: Dealer,
        ) {
            players.forEach { player ->
                while (player.makeDecision()) {
                    player.drawCard(dealer.dealCard())
                    OutputView.displayPlayersTurn(player)
                }
            }
        }

        private fun dealersTurn(dealer: Dealer) {
            while (dealer.makeDecision()) {
                dealer.drawCard(dealer.dealCard())
                OutputView.displayDealersGame()
            }
        }
    }
}
