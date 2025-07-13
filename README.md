# kotlin-blackjack

## Requirements

- Card values follow standard Blackjack rules:
- Number cards are counted by their face value.
- Face cards (King, Queen, Jack) are each worth 10.
- Aces can be worth either 1 or 11.
- Each player starts with two cards.
- Players may draw additional cards as long as their total remains 21 or less.
- The dealer must draw a card if their total is 16 or less, and must stand on 17 or more.
- If the dealer busts (goes over 21), all remaining players automatically win.
- After the game ends, display the result (win/loss) for each player.
- 
## Features
- [x] Validate input name of players

### Card
- [x] implement simple card class
  - [x] has index
  - [x] has symbol : enum class { hearts, spades , clubs, diamonds }

### Hold
- [x] implement Hold, wrapper class, Set<Card>
- [x] provide all unique elements

### CardDeck
- [x] cardDeck can hit cards to players
- [x] implement CardDeck, wrapper class, Hold and Set<Card * 52> 
- [x] add shuffle logic when initialed

### Player / Person
- [x] implement class

### Rank
- [x] Face cards (King, Queen, Jack) are each worth 10
- [x] toString(): return symbol character with Rank()

### Questions
- How can we make testable private functions? What is the good practise?
  1. internal
  2. companion object

## State Pattern Feature Plan

- [ ] `interface State` -> interface for all states
- [ ] FirstTurn -> First 2 cards are being drawn
  - [ ] Accepts 0–2 cards
  - [ ] If 2 cards and total is 21 → return Blackjack
  - [ ] If 2 cards and total < 21 → return Hit
  - [ ] Otherwise → stay in FirstTurn
    - [ ]stay() throws → can’t stay before 2 cards

- [ ] Hit - Player has drawn 2+ cards
  - [ ]  If new card makes total > 21 → return `Bust`
  - [ ]  Else → remain in `Hit`
  - [ ]  `stay()` returns `Stay`
- [ ] Finished : State -> End state — can be Blackjack, Bust, or Stay
  - [ ]  `profit(money: Int)` returns correct payout
- [ ] Bust
- [ ] Stay
- [ ] BlackJack
