package blackjack

class GameLogic {
    fun namesToPlayers(names: List<String>): List<Player> {
         return names.map { name -> Player(name) }
    }

    fun firstTurnCards(players: List<Player>, dealer: Dealer) {
        repeat(2, {
            players.forEach { player ->
                val card = dealer.drawCard()
                player.addCard(card)
            }
        })
        repeat(2, {
            val card = dealer.drawCard()
            dealer.addCard(card)
        })
    }

    fun otherTurnCards(players: List<Player>, dealer: Dealer) {
        val output = OutputView()
        players.forEach { player ->
            while (player.shouldHit()) {
                player.addCard(dealer.drawCard())
                output.printCurrentCardsOfOnePlayer(player)
            }
        }
        while (dealer.shouldHit()) {
            dealer.addCard(dealer.drawCard())
            output.printCurrentDealerCards(dealer)
        }
    }
}
