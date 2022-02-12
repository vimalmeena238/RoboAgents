# RoboAgents
This application simulates autonomous robots running around a 2D world

## Functional Requirements
1. The World class encapsulates a simple 2D world.
2. World can have free path (can be represented by '0') and obstacles (can be represented by '1').
3. World must provides a way to place robots, and keeps track of the location of them. Each robot exists in a single coordinate space. 
4. World must provides a movement API for the Robots.
5. World continuously displays the world (obstacles plus robots) to the terminal.
6. robot should move based on some simple behavior (such as move one space a second in one direction until blocked, then move in another direction). This behavior can be the same for each instance of Robot, and does not need to be complex.
7. Robot cannot interact with or have knowledge about the world beyond the move API.
8. Each Robot instance should run independently and not depend on a global "tick" (ie, each should be in a different thread).
