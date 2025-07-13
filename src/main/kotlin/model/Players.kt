package model

class Players(val players: List<Player>) {
    init {
        require(players.size <= 6) { "Maximum player names must be 6" }
    }
}
