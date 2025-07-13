package blackjack

class Hand() {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScoreResults(players: List<Player>, dealer: Dealer): MutableList<ResultTypes> {
        val scores = getScores(players, dealer)
        val results = mutableListOf<ResultTypes>()
        scores.forEach { score ->

            println("${score.key}: ${score.value}")
            when {
                score.value == 21 -> results.add(ResultTypes.BLACKJACK)
                score.value > 21 -> results.add(ResultTypes.BUSTED)
                else -> results.add(ResultTypes.STAY)
            }
        }
        return results
    }

    private fun sumCards(): Int {
        val cardValues = cards.map { card -> card.number }.toMutableList()
        val elevenSumCheck = cardValues.sum() + 10
        cardValues.replaceAll { if (it == 1 && elevenSumCheck <= 21) 11 else it }
        cardValues.replaceAll { if (it == 11) 10 else it }
        cardValues.replaceAll { if (it == 12) 10 else it }

        println("cardValues: $cardValues")
        return cardValues.sum()
    }

    private fun getScores(players: List<Player>, dealer: Dealer): MutableMap<String, Int> {
        val gameScores = mutableMapOf<String, Int>()
        players.map { player ->
            val sum = player.hand.sumCards()
            gameScores.put(player.toString(), sum)
        }
        val dealerSum = dealer.hand.sumCards()
        gameScores.put(dealer.name, dealerSum)
        return gameScores
    }

//    fun aceCheckCardValues(): MutableList<Int> {
//        val cardValues = cards.map { card -> card.number }.toMutableList()
//        val elevenSumCheck = cardValues.sum() + 10
//        cardValues.replaceAll { if (it == 1 && elevenSumCheck <= 21) 11 else it }
//        return cardValues
//    }
}