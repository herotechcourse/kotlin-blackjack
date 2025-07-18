package blackjack.model

open class Dealer : Participant(name = "Dealer") {
    private val deck = Deck()

    fun giveTwoCardsTo(participants: List<Participant>) {
        repeat(2) {
            giveCardTo(this)
            participants.forEach { giveCardTo(it) }
        }
    }

    fun giveCardTo(participant: Participant) {
        val card = deck.drawCard()
        participant.addCard(card)
    }

    fun shouldDraw(): Boolean {
        return getScore() <= 16
    }

    fun judge(player: Player): GameResult {
        val playerScore = player.getScore()
        val isPlayerBusted = player.isBusted()
        val isPlayerBlackjack = player.isBlackJack()

        val dealerScore = this.getScore()
        val isDealerBusted = this.isBusted()
        val isDealerBlackjack = this.isBlackJack()

        return when {
            isPlayerBusted -> GameResult.LOSE
            isDealerBusted -> GameResult.WIN
            isPlayerBlackjack && isDealerBlackjack -> GameResult.DRAW
            isPlayerBlackjack -> GameResult.WIN
            isDealerBlackjack -> GameResult.LOSE
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
