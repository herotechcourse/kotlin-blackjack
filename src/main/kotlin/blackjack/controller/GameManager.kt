package blackjack.controller

import blackjack.model.holder.Deck
import blackjack.model.participant.Participant

class GameManager(
    private val participant: Participant,
) {
    private val cardDeck = Deck()

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        dealer: Player,
        players: List<Player>,
        askForCard: () -> Boolean = { true },
    ) {
        players.forEach { round(it, askForCard) }
        round(dealer)
    }

    internal fun round(
        player: Player,
        askForCard: () -> Boolean = { true },
    ) {
        when (player) {
            dealer -> roundForDealer(player)
            else -> roundForPlayers(player, askForCard)
        }
    }

    private fun roundForPlayers(
        player: Player,
        askForCard: () -> Boolean,
    ) {
        while (ableToReceive(player)) {
            OutputView.printAskForCard(player)
            if (askForCard()) {
                cardDeck.hit(player)
                OutputView.printOnePlayer(player)
            } else {
                break
            }
        }
    }

    private fun roundForDealer(player: Player) {
        while (ableToReceive(player)) {
            cardDeck.hit(player)
        }
        OutputView.printDealerDrawsCards(player)
    }

    private fun ableToReceive(player: Player): Boolean {
        val isDealer = player === dealer
        if (isDealer) return dealer.calculatePoints() <= ABLE_TO_RECEIVE
        return player.calculatePoints() < BLACKJACK
    }

    companion object {
        private const val ABLE_TO_RECEIVE = 16
        private const val BLACKJACK = 21
    }
}