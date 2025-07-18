package blackjack.model.participant

class Participants(
    val players: List<Player>,
) {
    val dealer: Dealer = Dealer()

    internal fun get(index: Int) = players[index]

    fun containsAll(vararg names: String): Boolean {
        return players.map { it.name }.containsAll(names.toList())
    }

    fun contains(name: String): Boolean {
        return players.any { it.name == name }
    }

    fun contains(participant: Participant): Boolean {
        return when (participant) {
            is Dealer -> true
            is Player -> players.contains(participant)
            else -> false
        }
    }
}
