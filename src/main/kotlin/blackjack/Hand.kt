package blackjack

class Hand() {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

   fun sumCards(): Int {
        val cardValues = cards.map { card -> card.number }.toMutableList()
        val elevenSumCheck = cardValues.sum() + 10
        cardValues.replaceAll { if (it == 1 && elevenSumCheck <= 21) 11 else it }
        cardValues.replaceAll { if (it == 11) 10 else it }
        cardValues.replaceAll { if (it == 12) 10 else it }
       
        return cardValues.sum()
    }

    fun checkWinOrLose(): String {
        val cardValues = cards.map { card -> card.number }
        val score = cardValues.sum()
        return when {
            score == 21 -> "Win"
            score > 21 -> "Lose"
            else -> "Stay"
        }
    }

    override fun toString(): String {
        val cards = cards.joinToString(",")
        return cards
    }
}