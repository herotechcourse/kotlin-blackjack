package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.PlayingCard
import blackjack.model.Stats
import blackjack.view.InputView
import blackjack.view.OutputView

object GameMaster {
    fun run() {
        PlayingCard.deck.shuffle()
        val dealer = Dealer()
        val players = initPlayers()
        OutputView.displayInitialState(players, dealer)
        askPlayersToHit(players)
        drawDealerCards(dealer)
        OutputView.displayFinalState(players, dealer)
        val winStatistics = Stats(players, dealer)
        OutputView.displayEarnings(winStatistics)
    }

    private fun initPlayers(): List<Player> {
        val names = InputView.retryable { InputView.readPlayerNames() }
        val bets = names.map { InputView.retryable { InputView.readPlayerBettingAmount(it) } }
        val players =
            names.zip(bets)
                .map { (name, bet) -> Player(name, bet) }
        return players.toList()
    }

    private fun askPlayersToHit(players: List<Player>) {
        players.forEach { player ->
            askPlayerHit(player)
        }
    }

    private fun askPlayerHit(player: Player) {
        var isFirst = true

        while (!player.hand.isBust) {
            isFirst = drawOrNot(player, isFirst)
            if (isFirst) break
        }
    }

    private fun drawOrNot(
        player: Player,
        isFirst: Boolean,
    ): Boolean {
        val wantsCard =
            player.requestCard {
                InputView.retryable { InputView.readYesOrNo(player.name) }
            }

        if (!wantsCard) {
            if (isFirst) OutputView.displayCurrentHand(player)
            return true
        }

        drawAndDisplayAndReturnFalse(player)
        return false
    }

    private fun drawAndDisplayAndReturnFalse(player: Player): Boolean {
        val deck = PlayingCard.deck
        player.drawCard(deck.giveCard())
        OutputView.displayCurrentHand(player)
        return false
    }

    private fun drawDealerCards(dealer: Dealer) {
        val deck = PlayingCard.deck
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(deck.giveCard())
            OutputView.displayDealerDrawsCard()
        }
    }
}
