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
        val dealer = Dealer().initHand()
        val players = initPlayers()
        OutputView.displayInitialState(players, dealer)
        askPlayersToHit(players)
        drawDealerCards(dealer)
        OutputView.displayFinalState(players, dealer)
        val winStatistics = calculateStatistics(players, dealer)
        OutputView.displayFinalResults(winStatistics)
    }

    private fun initPlayers(): List<Player> {
        val names = InputView.retryable { InputView.readPlayerNames() }
        val bets = names.map { InputView.retryable { InputView.readPlayerBettingAmount(it) } }
        val players =
            names.zip(bets)
                .map { (name, amount) -> Player(name).placeBets(amount).initHand() }
        return players.toList()
    }

    private fun askPlayersToHit(players: List<Player>) {
        val deck = PlayingCard.deck
        players.forEach { player ->
            askPlayerHit(player) { deck.giveCard() }
        }
    }

    private fun askPlayerHit(
        player: Player,
        receiveCard: () -> PlayingCard,
    ) {
        var isFirst = true

        while (!player.isBust()) {
            isFirst = drawOrNot(player, receiveCard, isFirst)
            if (isFirst) break
        }
    }

    private fun drawOrNot(
        player: Player,
        receiveCard: () -> PlayingCard,
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

        drawAndDisplayAndReturnFalse(player, receiveCard)
        return false
    }

    private fun drawAndDisplayAndReturnFalse(
        player: Player,
        receiveCard: () -> PlayingCard,
    ): Boolean {
        player.drawCard(receiveCard())
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

    private fun calculateStatistics(
        players: List<Player>,
        dealer: Dealer,
    ): Stats {
        val winStatistics = Stats(players, dealer)
        winStatistics.updateDealerStats()
        return winStatistics
    }
}
