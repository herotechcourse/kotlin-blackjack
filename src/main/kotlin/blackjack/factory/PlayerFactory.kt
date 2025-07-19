package blackjack.factory

import blackjack.model.participant.Player
import blackjack.validator.Validator

interface BasePlayerFactory {
    fun createPlayers(): List<Player>
}

class PlayerFactory(private val names: List<String>, private val amounts: List<Int> = emptyList()) : BasePlayerFactory {
    init {
        Validator.createPlayers(names, amounts)
    }

    override fun createPlayers(): List<Player> {
        return names.zip(amounts) { name, amount ->
            Player(name, amount)
        }
    }
}
