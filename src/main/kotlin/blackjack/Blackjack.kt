package blackjack

object Blackjack {
    fun run() {
        println("Blackjack")
        val cardGenerator = CardGenerator()

        val cards = cardGenerator.generate()
        cards.forEach { card ->
            println("${card.suit}, ${card.number}")
        }
    }
}