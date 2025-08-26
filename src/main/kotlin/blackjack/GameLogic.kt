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

    fun playerShouldHit(player: Player): Boolean {
        return player.shouldHit()
    }

    fun dealerShouldHit(dealer: Dealer): Boolean {
        return dealer.shouldHit()
    }

    fun drawCardForParticipant(participant: Participant, dealer: Dealer) {
        participant.addCard(dealer.drawCard())
    }
}
