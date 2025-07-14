# kotlin-blackjack

## Description:
A simplified terminal-based Blackjack game written in Kotlin.  
The player or dealer with a total closest to 21—without going over—wins.  
Includes card dealing, drawing logic, and win/loss/draw result handling.

## Features

### Participant
- [x] Create `Participant` abstract class with hand management
- [x] Implement `Card` class with display and score logic

### Dealer
- [x] Deal card to any participant
- [x] Dealer auto-draws when score ≤ 16
- [x] Ask players if they want to draw more cards
- [x] Check if a participant is busted
- [x] Calculate participant score with Ace logic
- [x] Announce game results

### Game
- [x] Accept player names
- [x] Deal two initial cards to all participants
- [x] Handle player turns
- [x] Handle dealer turn
- [x] Display final hands and scores
- [x] Determine and print winners using `GameLogic.getGameResult()`

### Betting System
- [ ] Prompt each player to enter a betting amount at the start of the game
- [ ] Store and associate each player's bet with their name
- [ ] Calculate 1.5x payout for Blackjack (21 with initial two cards)
- [ ] Handle full loss of bet if player busts
- [ ] Handle dealer bust: remaining players win their bets
- [ ] Compare scores between dealer and players to calculate win/loss
- [ ] Display final earnings for each player and the dealer

