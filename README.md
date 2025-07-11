# Kotlin Blackjack

## Features

### Data class `Card`
- [x] has `Rank`
- [x] has `Suit`
- [x] method `toString()`

#### enum class `Rank`
- [x] includes names: "ACE"(1), "TWO", "THREE", ... "TEN", "JACK", "QUEEN", "KING"(13)
- [x] includes values: 1-13

#### enum class `Suit`
- [x] includes names: "SPADE", "HEART", "CLUB", "DIAMOND"

### value class `GamblerInfo`
- [x] contains value `name`
  - [x] should not be empty

### class `Player`
- [x] contains class `GamblerInfo`
- [x] contains list<Card>
- [x] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'
- [x] `addCard()` to add card
  - [x] private fun `updateScore` to update score on each insertion

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
- [x] private fun `readString`

#### `OutputView`
- [x] fun `displayErrorMessages`
- [x] fun `displayNamesOfPlayers`
- [x] fun `displayCardsOfPlayers`
- [x] fun `displayCardsOfDealer`
- [x] fun `displayDealersTurn`
- [x] fun `displayCardsOfPlayersWithScore`
- [x] fun `displayFinalResultsHeading`
- [x] fun `displayPlayerResult`
- [x] fun `displayPlayerResult`
- [x] fun `printEmptyLine`
- [x] fun `getCardsOfPlayers`

#### `InputPrompt`
- [x] `GET_NAME_OF_PLAYER`
- [x] fun `askHitOrStand`
