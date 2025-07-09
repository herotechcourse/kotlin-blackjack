package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameManager {
    val cardManager = CardManager()
    var players: List<Player> = emptyList()
    val dealer: Dealer = Dealer()
//    val winStatistics: Stats

    fun addPlayer(name: String) {
        val mutableList = players.toMutableList()
        val player = Player(name)
        mutableList.add(player)
        players = mutableList.toList()
    }

    // TODO: add display user hand method inside of logic
    fun askPlayerHit(player: Player) {
        var isFirst = true

        // player not busted
        while (!player.isBust()) {
            // ask and take input to draw another card from {0} player
            val requestMessage = player.requestCard { InputView.readYesOrNo(player.name) }

            // player want more card
            if (requestMessage) {
                player.drawCard(cardManager.giveCard())
                if (isFirst) {
                    OutputView.displayCurrentHand(player)
                    isFirst = false
                } else if (requestMessage) {
                    OutputView.displayCurrentHand(player)
                }
                continue
            } else if (isFirst) {
                OutputView.displayCurrentHand(player)
            }
            // until the player say no or bust
            break
        }
        return
    }

    fun run() {
        // [x] generate CardManager
        // [x] generate Cards

        val names = InputView.readPlayerNames()
        // [x] create players
        names.forEach { name -> addPlayer(name) }
        // [x] create dealer

        // [x] give two cards to each player and dealer
        repeat(2) {
            players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealer.drawCard(cardManager.giveCard())
        }

        // display initial state of players and dealer
        OutputView.displayInitialState(players, dealer)

        // loop over players
        players.forEach { askPlayerHit(it) }

        // dealer take cards based on the condition
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(cardManager.giveCard())
            OutputView.displayDealerDrawsCard()
        }

        // display final state of players and dealer
        OutputView.displayFinalState(players, dealer)

        // display final result
    }
}
