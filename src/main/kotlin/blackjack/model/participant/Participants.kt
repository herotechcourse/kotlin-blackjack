package blackjack.model.participant

class Participants private constructor(private val players: List<Participant>) {
    private val dealer = Dealer()

    fun getDealer(): Dealer = dealer

    constructor(vararg player: Player) : this(player.toList())
    constructor(vararg names: String) : this(names.map { Player(it) })

    internal fun contain(name: String): Boolean {
        return players.any { it.name == name }
    }

    internal fun containsAll(vararg names: String): Boolean {
        return players.map { it.name }.containsAll(names.toList())
    }

    operator fun get(index: Int) = players[index]

    fun contain(participant: Participant): Boolean {
        return participant::class == dealer::class || players.any { it == participant }
    }
}
