package blackjack

import blackjack.factory.BasePlayerFactory
import blackjack.model.participant.Player

class DummyPlayerFactory(val count: Int) : BasePlayerFactory {
    val names = createNames()
    val players: List<Player> = createPlayers()

    private fun createNames(): List<String> {
        var i = 0
        return List(count) { "guri${i++}" }
    }

    override fun createPlayers(): List<Player> {
        return names.map { Player(it, BET_MONEY_TEST_DEFAULT) }
    }

    companion object {
        const val BET_MONEY_TEST_DEFAULT = 10000
    }
}
