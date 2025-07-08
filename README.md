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

### class `PlayerCard`
- [x] contains fun `addCard` : updates total score, add element `card`
- [x] Considers Ace as the value of 1 if the total score is going above 21

### value class `Person`
- [x] contains value `name`
  - [x] should not be empty
  - [x] can't be 'Dealer' which is already reserved for it (Case Insensitive)

### class `Player`
- [] contains class `Person`
- [] contains class `PlayerCards`
- [] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'

### class `Deck`
- [] contains function `generateCards()`
- [] contains function `shuffle()`
- [] contains function `Draw()`

### class `Statistic`
- [] will use List<Player> as parameter

### class `Controller`
- [] has function `startRound()`
- [] has function `playerTakesTurn()`
  - [] has function `askDrawOrNot()` : until score is greater than 21 or user says 'n'

### view : class `InputView`, `OutputView`


### class `Application`