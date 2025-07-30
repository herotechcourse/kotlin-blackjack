
# Kotlin Blackjack
Welcome to my simple Blackjack card game in Kotlin. I have implemented 
an interactive game following the original Blackjack rules. Players can also bet
some money. here are the rules:

## Rule
Each player must place a bet at the start of the game. <br/>
Card values follow standard Blackjack rules: <br/>
Number cards are counted by their face value.<br/>
Face cards (King, Queen, Jack) are each worth 10.<br/>
Aces can count as either 1 or 11.<br/>
Players are dealt two cards at the beginning of the game.<br/>
Players can choose to draw additional cards as long as their total does not exceed 21.<br/>
If a player draws a card and the total exceeds 21, they lose their entire bet.<br/>
If a player hits 21 with the initial two cards (Blackjack), they receive 1.5x their bet.<br/>
If both the player and dealer have Blackjack, the player’s bet is returned.<br/>
The dealer must draw an additional card if their initial total is 16 or less.<br/>
The dealer must stand on 17 or more.<br/>
If the dealer busts (exceeds 21), all remaining players automatically win and receive payouts based on their bets.<br/>

## State Pattern
This project allows us to implement the so-called State Pattern
and therefore eliminate scattered and excessive conditionals.

Since the game by nature provides states: hit, busted, blackjack, etc. it makes sense to use this pattern for our game.
The State Pattern holds:
1. State Interface 
2. Concrete States 
3. Context (game logic)

## Features
States
- [x] Hit
  - has to decide if "y" or "n"
  - <= 21 for player (can)
  - <= 16 for dealer (must)
  - {ACTION}
  - -> Hit or Bust or Stay
- [x] Bust 
  - goes over 21
  - {STOP}
- [x] Init 
  - has to draw 2 cards
  - {ACTION}
  - -> Hit
- [x] BlackJack 
  - has exactly 21 with 2 cards
  - {STOP}
- [x] Stay
  - has <= 21, but decided to stop
  - {STOP}

Context
- [x] set betting amount for each player
  - [x] positive Int

## Results
  | Implemented | **Situation**                                     | **Winner** | **Explanation**                                                     |
  |-------------| ------------------------------------------------- | ---------- | ------------------------------------------------------------------- |
  | y           | Player gets blackjack, dealer doesn't             | Player     | Blackjack (Ace + 10) pays 3:2 or 6:5, depending on rules            |
  | y           | Dealer gets blackjack, player doesn't             | Dealer     | Dealer wins unless the player also has blackjack (then it's a push) |
  | y           | Both player and dealer get blackjack              | Push (tie) | No one wins, player's bet is returned                               |
  | y           | Player busts (goes over 21)                       | Dealer     | Automatic loss for the player regardless of dealer's hand           |
  | y           | Dealer busts, player doesn't                      | Player     | Dealer loses automatically if over 21                               |
  | y           | Neither busts, player's hand > dealer's           | Player     | Closest to 21 without busting wins                                  |
  | y           | Neither busts, dealer's hand > player's           | Dealer     | Dealer wins if they are closer to 21                                |
  | y           | Player and dealer have same total (not blackjack) | Push (tie) | No one wins, bet is returned                                        |


## Sample Output
```
Enter the names of the players (comma-separated):
pobi,jason

Enter pobi’s betting amount:
10000

Enter jason’s betting amount:
20000

Dealing two cards to dealer, pobi, jason.
Dealer: 3♦  
pobi's cards: 2♥, 8♠  
jason's cards: 7♣, K♠

Would pobi like to draw another card? (y for yes, n for no)  
y  
pobi's cards: 2♥, 8♠, A♣  
Would pobi like to draw another card? (y for yes, n for no)  
n  
Would jason like to draw another card? (y for yes, n for no)  
n  

Dealer draws one more card due to having 16 or less.

Dealer’s cards: 3♦, 9♣, 8♦ – Total: 20  
pobi's cards: 2♥, 8♠, A♣ – Total: 21  
jason's cards: 7♣, K♠ – Total: 17

## Final Earnings  
Dealer: 10000  
pobi: 10000  
jason: -20000

```

## Features
### Players
- [x] get user input for player names
  - [x] MIN (1), MAX(7) excluding dealer
  - [x] names can contain only letters
  - [x] MAX..MIN length
  - [x] comma separated
- [x] display names

### Cards
- [x] create card deck
- [x] Card values follow standard Blackjack rules:
  - [x] Number cards are counted by their face value.
  - [x] Face cards (King, Queen, Jack) are each worth 10.
  - [x] Aces can be worth either 1 or 11.
#### Deck
#### Card
- [x] suit
- [x] rank
- [x] value

- [x] deal initial cards to player and dealer (participants)
  - [x] 2 per player
- [x] print cards
  - [x] for the dealer only 1
  - [x] for players all
  - [x] color and value

- [x] prompt each player for drawing cards
  - [x] until "n"
  - [x] until he is busted
- [x] draw cards
- [x] calculate sum
- [x] display cards of player

- [x] draw cards for dealer until sum is <= 16
- [x] display message

- [x] display cards
- [x] display sum

- [x] calculate results
  - [x] player 
  - [x] dealer 
- [x] display results

