package blackjack.controller

import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.Player

class PlayerManager {
    private var _players: List<Player> = emptyList()
    val players: List<Player> get() = _players

    fun addPlayer(
        name: String,
        bet: Bet,
    ) {
        val mutableList = _players.toMutableList()
        val player = Player(name, bet)
        mutableList.add(player)
        _players = mutableList.toList()
    }

    // TODO: resolve indent depth
    fun askPlayerHit(
        player: Player,
        receiveCard: () -> Card,
    ) {
        var isFirst = true
        while (player.canContinue()) {
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

    private fun callGameManagerToReadYesOrNo(name: String): Boolean {
        return GameManager.inputView.retryable { GameManager.inputView.readYesOrNo(name) }
    }

    private fun callGameManagerToDisplayHand(player: Player) {
        GameManager.outputView.displayCurrentHand(player)
    }
}
