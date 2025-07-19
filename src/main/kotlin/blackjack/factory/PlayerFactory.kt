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
