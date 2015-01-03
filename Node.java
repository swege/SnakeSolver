/**
 * Part of the AutoSolver
 */
class Node {
  int tileId;
  float hCost;
  int minimumDistance;
  Node parent;
  Snake mainClass;
  static int DOT_SIZE = GameTile.TILE_SIZE / 4;

  Node(Snake mainClass, int tileId) {
    this.mainClass = mainClass;
    this.tileId = tileId;
    this.minimumDistance = 0;
  }

  int getGCost() {
    if (this.parent != null) {
      return this.parent.getGCost() + 1;
    }
    return 0;
  }

  int getNumberOfParents() {
    return getGCost();
  }


  int getFCost() {
    return this.getGCost() + (int)this.hCost;
  }

  int getX() {
    return this.tileId % this.mainClass.gameWorld.width;
  }

  int getY() {
    return this.tileId / this.mainClass.gameWorld.width;
  }

  void draw(int color) {
    this.mainClass.strokeWeight(0);
    this.mainClass.fill(color);
    int x = this.getX() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
    int y = this.getY() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
    this.mainClass.strokeWeight(1);
    this.mainClass.stroke(color);
    if (this.parent != null) {
      int parentX = this.parent.getX() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
      int parentY = this.parent.getY() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
      float vectorX = parentX - x;
      float vectorY = parentY - y;
      vectorX *= 0.4;
      vectorY *= 0.4;
      this.mainClass.line(x, y, x+vectorX, y+vectorY);
    }
    this.mainClass.ellipse(x, y, DOT_SIZE, DOT_SIZE);
  }
}
