package de.wegenerd;

import processing.core.PConstants;

class Node {
  int tileId;
  float hCost;
  int minimumDistance;
  Node parent;
  Processing processing;
  static int DOT_SIZE = GameTile.TILE_SIZE / 4;
  int costs;

  Node(Processing processing, int tileId) {
    this.processing = processing;
    this.tileId = tileId;
    this.minimumDistance = 0;
    this.costs = 1;
    for (int tile : this.processing.autoSolver.punishedTiles) {
      if (tile == tileId) {
        this.costs*=2;
      }
    }
  }

  int getGCost() {
    if (this.parent != null) {
      return this.parent.getGCost() + this.costs;
    }
    return 0;
  }

  int getNumberOfParents() {
    if (this.parent != null) {
      return this.parent.getNumberOfParents() + 1;
    }
    return 0;
  }


  int getFCost() {
    return this.getGCost() + (int)this.hCost;
  }

  int getX() {
    return this.tileId % this.processing.gameWorld.width;
  }

  int getY() {
    return this.tileId / this.processing.gameWorld.width;
  }

  void draw(int color) {
    this.processing.strokeWeight(0);
    this.processing.fill(color);
    int x = this.getX() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
    int y = this.getY() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
    this.processing.strokeWeight(1);
    this.processing.stroke(color);
    if (this.parent != null) {
      int parentX = this.parent.getX() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
      int parentY = this.parent.getY() * GameTile.TILE_SIZE + GameTile.TILE_SIZE / 2;
      float vectorX = parentX - x;
      float vectorY = parentY - y;
      vectorX *= 0.4;
      vectorY *= 0.4;
      this.processing.line(x, y, x+vectorX, y+vectorY);
    }
    this.processing.ellipse(x, y, DOT_SIZE, DOT_SIZE);
    if (Processing.DEBUG) {
      processing.fill(color);
      processing.textAlign(PConstants.LEFT, PConstants.BOTTOM);
      x = this.getX() * GameTile.TILE_SIZE;
      y = this.getY() * GameTile.TILE_SIZE + GameTile.TILE_SIZE;
      processing.text(this.getGCost(), x+1, y);
      processing.textAlign(PConstants.RIGHT, PConstants.BOTTOM);
      processing.text(this.tileId, x+GameTile.TILE_SIZE-1, y);
    }
  }
}
