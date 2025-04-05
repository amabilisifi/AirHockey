# Air Hockey

A real-time multiplayer air hockey game with physics-based gameplay, customizable tables, and both local and online multiplayer modes.


##  Overview

Air Hockey is a digital recreation of the popular arcade game. Players control paddles on opposite sides of a table and attempt to score goals by knocking the puck into their opponent's goal. This implementation features realistic physics, smooth controls, and multiple game modes.

##  Features

- **Realistic Physics**: Accurate puck movement, collisions, and rebounds
- **Multiple Game Modes**: 
  - Single-player vs AI with adjustable difficulty
  - Local multiplayer on the same device
  - Online multiplayer with matchmaking
- **Customization**:
  - Various table designs and themes
  - Different puck and paddle options
  - Custom game rules (speed, goal size, etc.)
- **Tournament Mode**: Create and participate in tournaments
- **Leaderboards**: Global and friend rankings
- **Achievements**: Unlock achievements through gameplay
- **Replays**: Save and watch game replays

##  Controls

- **Mouse/Touch**: Move your paddle by dragging or using the mouse
- **Keyboard**: Alternative control using arrow keys or WASD
- **Gamepad**: Support for gamepad controllers

##  Installation

### Prerequisites
- Java JDK 11 or higher
- Maven (for dependency management)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/amabilisifi/AirHockey.git
   ```

2. Navigate to the project directory:
   ```bash
   cd AirHockey
   ```

3. Compile the project:
   ```bash
   mvn clean compile
   ```

4. Run the game:
   ```bash
   mvn exec:java -Dexec.mainClass="com.amabilisifi.airhockey.AirHockeyGame"
   ```


## Development

### Project Structure
```
AirHockey/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amabilisifi/
│   │   │           └── airhockey/
│   │   │               ├── game/
│   │   │               │   ├── Game.java
│   │   │               │   ├── Table.java
│   │   │               │   ├── Puck.java
│   │   │               │   ├── Paddle.java
│   │   │               │   └── ...
│   │   │               ├── physics/
│   │   │               ├── ai/
│   │   │               ├── network/
│   │   │               ├── ui/
│   │   │               ├── audio/
│   │   │               ├── util/
│   │   │               ├── AirHockeyGame.java
│   │   │               └── ...
│   │   └── resources/
│   │       ├── images/
│   │       ├── audio/
│   │       ├── config/
│   │       └── ...
│   └── test/
│       └── java/
│           └── com/
│               └── amabilisifi/
│                   └── airhockey/
│                       └── ...
├── pom.xml
├── LICENSE
└── README.md
```


##  Game Mechanics

### Scoring
- Points are scored when the puck enters the opponent's goal
- First player to reach the score limit (default: 7) wins
- Tiebreakers go into sudden death mode

### Special Moves
- Power shots: Hold and release for stronger hits
- Bank shots: Bounce the puck off walls strategically 
- Spin moves: Apply spin to the puck with circular paddle movements

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

For bug reports and feature requests, please use the [GitHub Issues](https://github.com/amabilisifi/AirHockey/issues) page.
