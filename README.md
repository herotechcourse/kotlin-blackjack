# Kotlin Blackjack

## Features

### Data class `Card`
- [x] has `Rank`
- [x] has `Suit`

#### enum class `Rank`
- [x] includes names: "ACE"(1), "TWO", "THREE", ... "TEN", "JACK", "QUEEN", "KING"(13)
- [x] includes values: 1-13

#### enum class `Suit`
- [x] includes names: "SPADE", "HEART", "CLUB", "DIAMOND"

### value class `GamblerInfo`
- [x] contains value `name`
  - [x] should not be empty

### data class `PlayerBet`
- [x] Double: `betAmount`
- [x] Double: `winnings`

### abstract class `Player`
- [x] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'
- [x] contains class `GamblerInfo`
- [x] contains class `PlayerBet`
- [x] contains list<Card>
- [x] `addCard()` to add card
  - [x] private fun `updateScore` to update score on each insertion
- [x] `isBusted` if score > 21
- [x] `isBlackJack` if score == 21
- [x] `setBetAmount`

### class `Gambler` inherits `Player`
- [x] fun `isPlayerBelowBlackJack`
  - [x] returns true if score is below 21
- [x] `setWinnings(Boolean)`

### class `Dealer` inherits `Player`
- [x] fun `isDealerBelowMinScore`
  - [x] returns true if score is below 16
- [x] `setWinnings(Double)`

### class `Deck`
- [x] contains function `generateCards()`
- [x] contains function `shuffle()`
- [x] contains function `Draw()`

### class `FinalResult`
- [x] Calculates the winnings for Player

### directory `view` : object `InputView`, object `OutputView`
#### `InputView`
- [x] fun `getNamesOfPlayers`
- [x] fun `getHitOrStand`
- [x] fun `getBetAmount`
- [x] private fun `readString`

#### `OutputView`
- [x] fun `displayErrorMessages`
- [x] fun `displayNamesOfPlayers`
- [x] fun `displayCardsOfPlayers`
- [x] fun `displayCardsOfDealer`
- [x] fun `displayDealersTurn`
- [x] fun `displayCardsOfPlayersWithScore`
- [x] fun `displayFinalResultsHeading`
- [x] fun `displayFinalEarning`
- [x] fun `displayPlayerResult`
- [x] fun `displayPlayerResult`
- [x] fun `printEmptyLine`
- [x] fun `getCardsOfPlayers`


## Sample Output
```
Enter the names of the players (comma-separated):
player1, player2, player3

Dealing two cards to dealer, player1, player2, player3.
Enter player1’s betting amount:
10
Enter player2’s betting amount:
20
Enter player3’s betting amount:
30
Dealer: 9♣
player1's card: Q♦, 10♦
player2's card: K♣, 9♦
player3's card: 2♣, 9♥

Would player1 like to draw another card? (y for yes, n for no)
n
player1's card: Q♦, 10♦
Would player2 like to draw another card? (y for yes, n for no)
n
player2's card: K♣, 9♦
Would player3 like to draw another card? (y for yes, n for no)
y
player3's card: 2♣, 9♥, K♦

Dealer draws one more card due to having 16 or less.
Dealer's card: 9♣, 6♣, 8♣ – Total: 23
player1's card: Q♦, 10♦ – Total: 20
player2's card: K♣, 9♦ – Total: 19
player3's card: 2♣, 9♥, K♦ – Total: 21

## Final Results
Dealer: 0 Win 0 Draw 3 Lose
player1: Win
player2: Win
player3: Win

## Final Earnings
Dealer: -60
player1: 10
player2: 20
player3: 30
```