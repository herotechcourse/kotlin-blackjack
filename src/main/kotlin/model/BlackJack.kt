package model

class BlackJack(names: List<String>) {
    val players = names.map { Player(it) }
    val dealer = Dealer()

    init {
        require(players.size <= 6) { "Maximum player names must be 6" }
        initGame()
    }

    private fun initGame() {
        players.forEach { player ->
            repeat(2) {
                player.drawCard(dealer.dealCard())
            }
        }
        repeat(2) {
            dealer.drawCard(dealer.dealCard())
        }
    }
}
