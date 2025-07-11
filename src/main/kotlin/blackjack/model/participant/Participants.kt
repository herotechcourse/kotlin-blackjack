package blackjack.model.participant

class Participants private constructor(private val players: List<Participant>) : List<Participant> by players {
    val dealer = Dealer()

    constructor(vararg player: Player) : this(player.toList())
    constructor(vararg names: String) : this(names.map { Player(it) })

    internal fun contain(name: String): Boolean {
        return players.any { it.name == name }
    }

    fun contain(participant: Participant): Boolean {
        return participant::class == dealer::class || players.any { it == participant }
    }
}