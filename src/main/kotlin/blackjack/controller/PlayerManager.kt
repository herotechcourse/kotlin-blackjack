package blackjack.controller

import blackjack.model.Card
import blackjack.model.Player

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
            val requestMessage =
                player.requestCard {
                    callGameManagerToReadYesOrNo(player.name)
                }
            if (requestMessage) {
                isFirst = false
                player.drawCard(receiveCard())
                callGameManagerToDisplayHand(player)
                continue
            } else if (isFirst) {
                callGameManagerToDisplayHand(player)
            }
            break
        }
        return
    }

    fun callGameManagerToReadYesOrNo(name: String): Boolean {
        return GameManager.inputView.retryable { GameManager.inputView.readYesOrNo(name) }
    }

    fun callGameManagerToDisplayHand(player: Player) {
        GameManager.outputView.displayCurrentHand(player)
    }
}
