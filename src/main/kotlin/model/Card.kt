package model

data class Card(val rank: Rank, val suite: Suite, val faceUp: Boolean = true) {
    override fun equals(other: Any?): Boolean {
        return rank == (other as Card).rank && suite == other.suite
    }

    override fun hashCode(): Int {
        var result = rank.hashCode()
        result = 52 * result + suite.hashCode()
        return result
    }
}
