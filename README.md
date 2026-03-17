# Rushing on the Teaching Road: Teacher's Ultra-Fast Commute
🔄 [中文版本](./README_CN.md)
Homework for the Software Development Course of the School of Software Engineering

## Game Overview
This game - *Rushing on the Teaching Road: Teacher's Commute* is a training project of the Fan Hanxuan Fan Club. The game is set in the scenario where a teacher commutes between two campuses.

## Quick Start
1. Click `Game.exe` to launch the game.
2. **Account Registration/Login**:
   - New users need to register an account first; existing users can log in directly.
   - Account and password requirements: 1-10 character string, consisting of numbers, uppercase/lowercase letters (no spaces in between).

## Game Rules
### 1. Levels & Vehicles
- Initial unlock: Level 1 + 1 vehicle for land/air/sea respectively.
- Level Classification:
  - Land levels: 1, 4, 7
  - Air levels: 2, 5, 8
  - Sea levels: 3, 6, 9
- Difficulty: Increases progressively with each level.
- Vehicle Development: Obtain parts in levels to unlock new vehicles:
  - Gold part = 3 regular parts
  - Silver part = 2 regular parts
  - Copper part = 1 regular part

### 2. Core Gameplay
- **Fuel Mechanic**:
  - Colliding with obstacles reduces fuel; collecting fuel tanks increases fuel.
  - Game over if fuel drops to 0.
  - Level clearance: Reach a travel distance of 1000 units (teacher arrives at the destination).
  - Level unlock rule: Only clear the previous level to unlock the next one.

#### Land Levels (Cars)
- Control: Click the **Up** button to make the car jump and avoid obstacles.
- Extra mechanic: Step on top of obstacles to trigger a second jump.

#### Air Levels (Planes)
- Control: Click the **Up** button to make the plane take off.
- After takeoff: The plane will jump briefly then start falling downwards.
- Game over: If the plane flies out of the screen boundary.

#### Sea Levels (Ships)
- Control: Use **Up/Down** buttons to move the ship.
- Fuel collection: The ship can collect fuel tanks in its current lane and the lane above.

### 3. Pause Function
Press the **Spacebar** to pause the game at any time.

## Screenshots (Add Your Images Here)
| Game Login Interface | Land Level Gameplay | Air Level Gameplay |
|----------------------|---------------------|--------------------|
| ![Login Interface](https://github.com/LINllllllllll/Rushing-on-the-Teaching-Road-Teacher-s-Ultra-Fast-Commute/raw/main/screenshots/login.png) | ![Land Level](https://github.com/LINllllllllll/Rushing-on-the-Teaching-Road-Teacher-s-Ultra-Fast-Commute/raw/main/screenshots/land_level.png) | ![Air Level](https://github.com/LINllllllllll/Rushing-on-the-Teaching-Road-Teacher-s-Ultra-Fast-Commute/raw/main/screenshots/air_level.png) |

## Notes
### 1. Game Launch
- Ensure all game resource files (e.g., textures, audio, configuration files) are stored in their original directories relative to `Game.exe`; missing files may cause launch failures or runtime errors.
- Double-click `Game.exe` in the packaged folder to start the game directly (no additional installation required).

### 2. Runtime Environment
- The game relies on **Java Runtime Environment (JRE) 1.8** to run properly.
- The packaged folder already includes the complete JRE 1.8 environment, so you don’t need to install JRE separately on your system.

### 3. Compatibility
- Recommended OS: Windows 10/11 (64-bit)
- Known issue: The game may not run properly on Windows 7 or lower due to JRE compatibility limitations.
