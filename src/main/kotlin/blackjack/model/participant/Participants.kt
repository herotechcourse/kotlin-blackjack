package blackjack.model.participant

class Participants private constructor(
    private val players: List<Player>,
    private val dealer: Dealer = Dealer(),
) {
    fun getDealer(): Dealer = dealer

    fun getPlayers(): List<Player> = players

    internal fun getPlayer(index: Int) = players[index]

    fun contain(name: String): Boolean {
        return players.any { it.name == name }
    }

    fun containsAll(vararg names: String): Boolean {
        return players.map { it.name }.containsAll(names.toList())
    }

    fun contain(participant: Participant): Boolean {
        return when (participant) {
            is Dealer -> true
            is Player -> players.contains(participant)
            else -> false
        }
    }

    companion object {
        fun from(names: List<String>) = Participants(names.map { Player(it) })
    }
}
