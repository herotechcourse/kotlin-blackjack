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

    // TODO: resolve indent depth
    fun askPlayerHit(
        player: Player,
        receiveCard: () -> PlayingCard,
    ) {
        var isFirst = true
        while (!player.isBust()) {
            val requestMessage =
                player.requestCard {
                    InputView.retryable { InputView.readYesOrNo(player.name) }
                }
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
