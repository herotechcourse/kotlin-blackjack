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
- [] contains value `name`
  - [] should not be empty
  - [] its length should be at least of length 2
  - [] can't be 'Dealer' which is already reserved for it

### class `Player`
- [] contains class `Person`
- [] contains class `PlayerCards`
- [] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'

### class `Deck`
- [] contains function `generateCards()`
- [] contains function `shuffle()`
