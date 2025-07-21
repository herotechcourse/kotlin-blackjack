package blackjack.model.result

import blackjack.model.participant.Player

data class PlayerResult(
    val player: Player,
    val outcome: Outcome,
) {
    val finalAmount: Int = calculateTotal()

    private fun calculateTotal(): Int = (player.amount * outcome.payoutMultiplier).toInt()
}
