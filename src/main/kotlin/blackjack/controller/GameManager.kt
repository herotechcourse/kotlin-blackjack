package blackjack.controller

import blackjack.model.game.CardDeck
import blackjack.model.participant.PlayerBackup
import blackjack.view.OutputView

class GameManager(private val dealer: PlayerBackup, private val players: List<PlayerBackup>) {
    private val cardDeck = CardDeck()

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        dealer: PlayerBackup,
        players: List<PlayerBackup>,
        askForCard: () -> Boolean = { true },
    ) {
        players.forEach { round(it, askForCard) }
        round(dealer)
    }

    internal fun round(
        player: PlayerBackup,
        askForCard: () -> Boolean = { true },
    ) {
        when (player) {
            dealer -> roundForDealer(player)
            else -> roundForPlayers(player, askForCard)
        }
    }

    private fun roundForPlayers(
        player: PlayerBackup,
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

    private fun roundForDealer(player: PlayerBackup) {
        while (ableToReceive(player)) {
            cardDeck.hit(player)
        }
        OutputView.printDealerDrawsCards(player)
    }

    private fun ableToReceive(player: PlayerBackup): Boolean {
        val isDealer = player === dealer
        if (isDealer) return dealer.calculatePoints() <= ABLE_TO_RECEIVE
        return player.calculatePoints() < BLACKJACK
    }

    companion object {
        private const val ABLE_TO_RECEIVE = 16
        private const val BLACKJACK = 21
    }
}