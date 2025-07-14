# Kotlin Blackjack

## Features : Step2 (Updates from Step1)

### Enhanced Blackjack Rules
- [x] Update `Player.isBlackJack()` to check exactly 2 cards with total score 21
- [] Player loses entire bet when busting (total > 21)

### Betting System Infrastructure
- [] Add `betAmount` property to `Player` class
- [] Update `InputView` to get betting amounts from users
  - [] Validate: not empty, greater than 0, numeric input
  - [] Add `getBettingAmount(playerName: String): Int` method
- [] Update `Controller` to handle betting phase after player creation
  - [] Add `getBettingAmounts()` method to collect bets from all players
  - [] Call betting phase in main game flow

### Payout System
- [] Create `Payout` class for earnings calculations only
  - [] Method to calculate individual player earnings
  - [] Blackjack payouts (1.5x bet)
  - [] Normal wins (1x bet)
  - [] Losses (-1x bet)
  - [] Ties/Push (0x bet)
- [] Calculate dealer earnings (negative sum of all player earnings)

### Update Game Results
- [] Replace `FinalResult` class logic
  - [] Remove `win`, `draw`, and `lose` lists
  - [] Add earnings calculation using `Payout` class
  - [] Add `earnings: Map<Player, Int>` property
  - [] Add `dealerEarnings: Int` property
- [] Update `OutputView` to display earnings instead of win/lose
  - [] Add `displayFinalEarnings()` method
  - [] Format: "## Final Earnings"
  - [] Show dealer and player earnings amounts

## Updates : Step1 (after implementing step2)
- [] implement abstract class and inheritance for players and dealer

## Features : Step1 
### Data class `Card`
- [x] has `Rank`
- [x] has `Suit`
- [x] method `toString()`

#### enum class `Rank`
- [x] includes names: "ACE"(1), "TWO", "THREE", ... "TEN", "JACK", "QUEEN", "KING"(13)
- [x] includes values: 1-13

#### enum class `Suit`
- [x] includes names: "SPADE", "HEART", "CLUB", "DIAMOND"

### class `PlayerCard`
- [x] contains fun `addCard` : updates total score, add element `card`
- [x] Considers Ace as the value of 1 if the total score is going above 21

### value class `GamblerInfo`
- [x] contains value `name`
  - [x] should not be empty
  - [x] can't be 'Dealer' which is already reserved for it (Case Insensitive)

### class `Player`
- [x] contains class `GamblerInfo`
- [x] contains class `hand`
- [x] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'

### class `Deck`
- [x] contains function `generateCards()`
- [x] contains function `shuffle()`
- [x] contains function `Draw()`

### directory `view` : object `InputView`, object `OutputView`
#### `InputView`
- [x] fun `get names of the player`
- [x] fun `read String`
- [x] fun `get hit or stand`
#### `OutputView`
- [x] fun `display names of players`
- [x] fun `display the first draw`(parameter: List<Player>)
  - [x] private fun `display message for first draw`(parameter: Player)
  - [x] private fun `display cards and score`

### object `Controller`
- [x] has function `startRound()`
- [x] has function `TakesTurn()`
  - [x] has function `askDrawOrNot()` : until score is greater than 21 or user says 'n'

### class `Statistic`
- [x] will use List<Player> as parameter

### class `Application`
- [x] fun main() to and use `run` from controller