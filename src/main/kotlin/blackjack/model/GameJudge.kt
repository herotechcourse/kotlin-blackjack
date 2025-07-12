package blackjack.model

object GameJudge {
    fun evaluateAll(participants: Participants) {
        participants.players.forEach { evaluate(participants.dealer, it) }
    }

    internal fun evaluate(dealer: Dealer, player: Player) {
        val result = dealer vs player
        player.recordResult(result)
        dealer.recordResult(result.inverse)
    }

    private infix fun Dealer.vs(player: Player): Result = when {
        player.isBusts() -> Result.LOSE
        this.isBusts() -> Result.WIN
        player.hasBlackJack() && !this.hasBlackJack() -> Result.WIN
        player.getScore() > this.getScore() -> Result.WIN
        player.getScore() < this.getScore() -> Result.LOSE
        else -> Result.TIE
    }
}