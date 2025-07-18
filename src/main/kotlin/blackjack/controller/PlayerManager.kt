package blackjack.controller

import blackjack.model.Player
import blackjack.model.PlayingCard
import blackjack.view.InputView
import blackjack.view.OutputView

class PlayerManager {
    private var _players: List<Player> = emptyList()
    val players: List<Player> get() = _players

    fun addPlayer(name: String) {
        val mutableList = _players.toMutableList()
        val player = Player(name)
        mutableList.add(player)
        _players = mutableList.toList()
    }

    fun askPlayerHit(
        player: Player,
        receiveCard: () -> PlayingCard,
    ) {
        var isFirst = true

        while (!player.isBust()) {
            isFirst = drawOrNot(player, receiveCard, isFirst)
            if (isFirst) break
        }
    }

    private fun drawOrNot(
        player: Player,
        receiveCard: () -> PlayingCard,
        isFirst: Boolean,
    ): Boolean {
        val wantsCard =
            player.requestCard {
                InputView.retryable { InputView.readYesOrNo(player.name) }
            }

        if (!wantsCard) {
            if (isFirst) OutputView.displayCurrentHand(player)
            return true
        }

        drawAndDisplayAndReturnFalse(player, receiveCard)
        return false
    }

    private fun drawAndDisplayAndReturnFalse(
        player: Player,
        receiveCard: () -> PlayingCard,
    ): Boolean {
        player.drawCard(receiveCard())
        OutputView.displayCurrentHand(player)
        return false
    }
}
