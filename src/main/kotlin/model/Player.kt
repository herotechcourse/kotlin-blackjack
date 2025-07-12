package model

class Player(name: String) : BasePlayer(name) {
    override fun toString(): String {
        return "$name's cards: $hand"
    }
}
