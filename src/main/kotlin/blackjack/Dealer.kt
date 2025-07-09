package blackjack

class Dealer : Participant("Dealer") {
    private val deck: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                deck.add(Card(suit, rank))
            }
        }
        deck.shuffle()
    }

    private fun dealCard(): Card {
        if (deck.isEmpty()) throw IllegalStateException("Deck is empty")
        return deck.removeFirst()
    }

    fun giveCardTo(participant: Participant) {
        val card = dealCard()
        participant.addCard(card)
    }

    fun askPlayerDraw(player: Player) {
        while (true) {
            println("Would ${player.name} like to draw another card? (y for yes, n for no)")
            val input = readln().trim()
            if (input == "y") {
                giveCardTo(player)
                println("${player.name}'s cards: ${player.displayHand()}")
                if (isBusted(player)) break
            } else if (input == "n") {
                break
            } else {
                println("Invalid input. Please enter 'y' or 'n'.")
            }
        }
    }
}
