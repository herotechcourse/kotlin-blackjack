package blackjack.factory

import blackjack.model.participant.Player

interface BasePlayerFactory {
    fun createPlayers(): List<Player>
}

class PlayerFactory(private val names: List<String>, private val amounts: List<Int> = emptyList()) : BasePlayerFactory {
    init {
        require(names.isNotEmpty() && amounts.isNotEmpty()) { /* TODO: implement here */ }
        require(names.size == amounts.size) { /* TODO: implement here */ }
    }

    override fun createPlayers(): List<Player> {
        return names.zip(amounts) { name, amount ->
            Player(name, amount)
        }
    }
}

// TODO: separate file to test
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
