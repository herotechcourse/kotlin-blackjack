package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView

class GameManager {
    val cardManager = CardManager()
    private var _players: List<Player> = emptyList()
    val dealer: Dealer = Dealer()
//    val winStatistics: Stats

    fun addPlayer(name: String) {
        val mutableList = _players.toMutableList()
        val player = Player(name)
        mutableList.add(player)
        _players = mutableList.toList()
    }

    // TODO: add display user hand method inside of logic
    fun askPlayerHit(player: Player) {
        //player not busted
        while (!player.isBust()) {
            // ask and take input to draw another card from {0} player
            val requestMessage = player.requestCard { InputView.readYesOrNo(player.name) }

            //player want more card
            if (requestMessage) {
                player.drawCard(cardManager.giveCard())
                continue
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

        // give two cards to each player and dealer
        repeat(2 ) {
            _players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealer.drawCard(cardManager.giveCard())
        }

        // display initial state of players and dealer

        // loop over players
        _players.forEach { askPlayerHit(it) }

        // dealer take cards based on the condition
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(cardManager.giveCard())
        }
        // display instruction how many cards did dealer take

        // display final state of players and dealer

        // display final result
    }
}