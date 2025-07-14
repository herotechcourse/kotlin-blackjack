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
  - [x] has Rank
  - [x] has symbol : enum class { hearts, spades , clubs, diamonds }
  - [x] patter object pool

### CardDeck
- [x] implement CardDeck
- [x] add shuffle logic when initialed

### Rank
- [x] Face cards (King, Queen, Jack) are each worth 10
- [x] toString(): return symbol character with Rank()

## State Pattern Feature Plan

- [x] `interface State` -> interface for all states
- [x] FirstTurn -> First 2 cards are being drawn
  - [x] Accepts 0–2 cards
  - [x] If 2 cards and total is 21 → return Blackjack
  - [x] If 2 cards and total < 21 → return Hit
  - [x] Otherwise → stay in FirstTurn
  - [x]stay() throws → can’t stay before 2 cards

- [x] Hit - Player has drawn 2+ cards
  - [x]  If new card makes total > 21 → return `Bust`
  - [x]  Else → remain in `Hit`
  - [x]  `stay()` returns `Stay`
- [x] Finished : State -> End state — can be Blackjack, Bust, or Stay
  - [ ]  `profit(money: Int)` returns correct payout
- [x] Bust
- [x] Stay
- [x] BlackJack

## Participant Architecture features
- [x] interface Participant 
  - [x] Expose the current State 
  - [x] Expose the current Hand (from the state)
  - [x] Handle the transition of turns through playTurn(deck)
- [x] Player
  - [x] Starts in FirstTurn state (receives initial 2 cards)
  - [x] Can draw cards (Hit) or stop (Stay)
  - [x] Transitions through states: FirstTurn → Hit → Stay | Bust | Blackjack
  - [ ] Chooses actions based on external input (DecisionMaker??)
  - [ ] Calculates profit()
- Dealer
  - [x] Starts in FirstTurn state (just like Player)
  - [x] Automatically draws cards while total < 17
  - [x] Stops automatically when total ≥ 17 (calls stay())
  - [x] Transitions through states without external input
  - [ ] Calculates profit() for comparison in result phase??


## Features for step2
- [ ] Each player must place a bet at the start of the game.
- [ ] If a player hits 21 with the initial two cards (Blackjack), they receive 1.5x their bet.
- [ ] If both the player and dealer have Blackjack, the player’s bet is returned.
- [ ] If the dealer busts (exceeds 21), all remaining players automatically win and receive payouts based on their bets.
