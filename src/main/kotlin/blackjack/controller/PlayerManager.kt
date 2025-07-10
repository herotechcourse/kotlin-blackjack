package blackjack.controller

import blackjack.model.Card
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class PlayerManager {
    var players: List<Player> = emptyList()

    fun addPlayer(name: String) {
        val mutableList = players.toMutableList()
        val player = Player(name)
        mutableList.add(player)
        players = mutableList.toList()
    }

    // TODO: resolve indent depth
    fun askPlayerHit(
        player: Player,
        receiveCard: () -> Card,
    ) {
        var isFirst = true
        while (!player.isBust()) {
            val requestMessage = player.requestCard { InputView.readYesOrNo(player.name) }
            if (requestMessage) {
                isFirst = false
                player.drawCard(receiveCard())
                OutputView.displayCurrentHand(player)
                continue
            } else if (isFirst) {
                OutputView.displayCurrentHand(player)
            }
            break
        }
        return
    }
}
