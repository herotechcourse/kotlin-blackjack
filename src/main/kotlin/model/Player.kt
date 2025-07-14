package model

class Player(name: String) : BasePlayer(name) {
    override fun makeDecision(value: Int): Boolean {
        return value.toChar().toString() == "y"
    }

    override fun toString(): String {
        return "$name's cards: $hand"
    }
}
