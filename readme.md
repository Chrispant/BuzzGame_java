# 🎮 Buzz! Quiz Game (Java GUI Edition)

An interactive, local multiplayer party quiz game built entirely in Java using the Swing framework[cite: 7]. Heavily inspired by the classic party game *Buzz!*, this application supports up to two players matching wits across multiple randomized trivia categories[cite: 5, 7]. It features dynamic round mechanics (including wagering structures) and handles local score persistence[cite: 7].

---

## 🕹️ Game Controls & How to Play

Because this is a head-to-head arcade game played on a single keyboard, each player has a dedicated set of "buzzer" keys corresponding to choices 1 through 4[cite: 7]:

| Game Choice | 👤 Player 1 Keys (Left)[cite: 7] | 👥 Player 2 Keys (Right)[cite: 7] |
| :--- | :--- | :--- |
| **Option 1** | `Q`[cite: 7] | `U`[cite: 7] |
| **Option 2** | `W`[cite: 7] | `I`[cite: 7] |
| **Option 3** | `E`[cite: 7] | `O`[cite: 7] |
| **Option 4** | `R`[cite: 7] | `P`[cite: 7] |

### The Game Loop
1. **Setup:** Choose between **1 Player** or **2 Players** and enter customized nicknames (defaults include *Adam* and *Bella*)[cite: 7].
2. **Select Round Style:** Choose how points are calculated for the round[cite: 7]:
   * **Answer Correct:** A standard round where matching the correct answer nets a flat `+1000` points[cite: 7].
   * **Bet:** Players are presented with a wagering panel before the question reveals[cite: 7]. Bet values (`250`, `500`, `750`, or `1000` points) are added on a correct answer or completely deducted on a wrong guess[cite: 7].
   * **Stop The Clock:** A speed-based countdown challenge[cite: 7].
3. **Trivia Execution:** Face 5 rounds of randomly picked questions spanning **History**, **Science**, **Sports**, **Health**, and **Cinema**[cite: 5, 7].
4. **GameOver & Persistence:** The winner is declared via an overlay dialog box, and high-scores are automatically verified and updated inside `Records/records.txt`[cite: 7].

---

## 🛠️ Tech Stack & Architecture

* **Core Engine:** Java (utilizing `java.util.Timer`, custom `Collections.shuffle()`, and data indexing via `HashMap`)[cite: 1, 3, 5].
* **Graphics Layout:** Java Swing & Abstract Window Toolkit (AWT) handling image rendering layers and native keyboard `KeyEvents`[cite: 7].
* **Persistence Layer:** Java File I/O (`BufferedReader` / `PrintWriter`) writing to plain-text structured databases[cite: 7].

### Codebase Blueprint

* **`Main.java`** — The application bootstrapper that instantiates the main `Game` class and launches the `GUI`[cite: 1].
* **`Game.java`** — The core state machine managing categorized question pools and pool generation logic.
* **`GameType.java`** — Struct enum identifying distinct mathematical scoring variations (`CORRECT_ANSWER`, `STOP_ALARM`, `BETTING`, `QUICK_ANSWER`, `THERMOMETRO`)[cite: 6].
* **`GUI.java`** — Layout container managing screen refreshes, player configuration panels, and button frameworks[cite: 7].
* **`Player.java`** — Encapsulates individual user identity, tracking names, ongoing point tallies, live selections, and active bets[cite: 2].
* **`Question.java`** — Models individual questions and self-shuffles multiple-choice arrays using `Collections.shuffle()` upon instantiation.
* **`Round.java`** — Structural class representing active round rules[cite: 4].

---

## ⚙️ Setup and Installation

### Prerequisites
* Ensure you have a Java Development Kit (JDK 8 or higher) installed.
* Ensure you preserve your asset paths (`images/basic_frame_image.jpg` and the directory `Records/`) in your working directory[cite: 7].

### Running via Terminal

1. **Clone the repository:**
```bash
   git clone [https://github.com/Chrispant/BuzzGame_java.git](https://github.com/Chrispant/BuzzGame_java.git)
   cd BuzzGame_java