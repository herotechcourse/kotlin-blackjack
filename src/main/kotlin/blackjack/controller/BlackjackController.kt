package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Participant
import blackjack.model.ParticipantWallet
import blackjack.model.Player
import blackjack.model.PlayerFactory
import blackjack.state.Finished
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    val deck = Deck.generate(true)
    val dealer = Dealer(deck)
    val input = InputView()
    val output = OutputView()

    fun run() {
        output.printNameQuestion()

        val players = createPlayers()
        collectPlayerBets(players)

        val participants = players + dealer

        output.printPlayersIntroToTheirCards(players)

        playFirstTurn(participants)
        playSecondTurn(players)

        calculateEarnings(players)
        printFinalResults(players, participants)
    }

    fun executePlayerTurn(player: Player) {
        while (player.state !is Finished) {
            if (input.askPlayerIfShouldHit(player)) {
                player.playTurn()
                output.printCurrentCardsOfOnePlayer(player)
            } else {
                player.stay()
                break
            }
        }
    }

    fun executeDealerTurn() {
        while (dealer.state !is Finished) {
            output.printDealerDrawsOneMoreCardMessage()
            dealer.playTurn()
            output.printCurrentDealerCards(dealer)
        }
    }

    private fun createPlayers(): List<Player> {
        val playersNames = input.readPlayersName()
        return PlayerFactory.fromNames(playersNames, deck)
    }

    private fun collectPlayerBets(players: List<Player>) {
        players.forEach { player ->
            output.printBetQuestion(player)
            val placedBet = input.readPlayerBet().toDouble()
            player.bet(placedBet)
            dealer.wallet.addToEarnings(placedBet)
        }
    }

    private fun playFirstTurn(participants: List<Participant>) {
        participants.forEach { it.playTurn() }
        output.printDealerFirstTurnCards(dealer)
        output.printPlayersFirstTurnCards(participants.filterIsInstance<Player>())
    }

    private fun playSecondTurn(players: List<Player>) {
        players.forEach { executePlayerTurn(it) }
        executeDealerTurn()
    }

    private fun calculateEarnings(players: List<Player>) {
        var dealerEarnings = 0.0
        players.forEach { player ->
            val earningsRate = player.calculateEarningsRate(dealer)
            player.updateWalletWithEarningsRate(earningsRate)
            dealerEarnings -= player.wallet.total()
        }
        dealer.wallet.addToEarnings(dealerEarnings)
    }

    private fun printFinalResults(
        players: List<Player>,
        participants: List<Participant>,
    ) {
        output.printDealerFinalScore(dealer)
        output.printPlayersFinalScore(players)

        val participantAndWallet = mutableMapOf<String, ParticipantWallet>()
        participants.forEach { participant ->
            participantAndWallet[participant.name] = participant.wallet
        }

        output.printFinalParticipantResult(participantAndWallet)
    }
}
