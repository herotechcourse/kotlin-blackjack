package blackjack.model.participant

import blackjack.model.result.Result

infix fun Dealer.vs(player: Player): Result =
    when {
        player.isBusts() -> Result.LOSE
        this.isBusts() -> Result.WIN
        player.hasBlackJack() && !this.hasBlackJack() -> Result.WIN
        player.getScore() > this.getScore() -> Result.WIN
        player.getScore() < this.getScore() -> Result.LOSE
        else -> Result.TIE
    }
