class GameTile {
  
  static final int TILE_SIZE = 20; // I recommend a value of 20
  final int x;
  final int y;
  boolean hasFood;
  boolean occupied;
  // -1: indefinitely occupied; else, it will be decreased every tick by 1, giving the field free when reaching 0
  int occupiedCounter = -1;
  
  GameTile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  void draw(Snake mainClass) {
    int color = this.occupied ? 0xffffffff : 0xff000000;
    mainClass.fill(color);
    mainClass.stroke(0xff555555);
    mainClass.strokeWeight(1);
    int x = this.x * TILE_SIZE;
    int y = this.y * TILE_SIZE;
    mainClass.rect(x, y, TILE_SIZE, TILE_SIZE);
    if (this.hasFood) {
      int margin = TILE_SIZE / 3;
      mainClass.stroke(0xffffffaa);
      mainClass.line(x + margin, y + margin, x + TILE_SIZE - margin, y + TILE_SIZE - margin);
      mainClass.line(x + margin, y + TILE_SIZE - margin, x + TILE_SIZE - margin, y + margin);
    }
  }

  void tick() {
    if (this.occupiedCounter > 0) {
      occupiedCounter--;
    }
    if (this.occupiedCounter == 0) {
      this.occupied = false;
    }
  }
}
