package blackjack.model

abstract class Player(val gamblerInfo: GamblerInfo) {
    private val _cards: MutableList<Card> = mutableListOf()
    private val playerBet = PlayerBet()

    var score: Int = 0
        private set
    val cards: List<Card>
        get() = _cards

    val name: String
        get() = gamblerInfo.name

    val betAmount:Int
        get() = playerBet.betAmount

    val winnings: Int
        get() = playerBet.winnings

    fun setBetAmount(amount: Int) {
        playerBet.betAmount = amount
    }

    fun setWinnings(amount: Int) {
        playerBet.winnings = amount
    }

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
        updateScore()
    }

    private fun updateScore() {
        var aceCount = _cards.count { it.rank == Rank.ACE }
        var totalScore = _cards.sumOf { it.rank.value }
        while (totalScore > WINNING_SCORE && aceCount > 0) {
            totalScore -= 10
            aceCount--
        }
        score = totalScore
    }

    companion object {
        const val WINNING_SCORE = 21
    }
}
