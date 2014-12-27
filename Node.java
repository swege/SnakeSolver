/**
 * Part of the AutoSolver
 */
class Node {
  int tileId;
  float hCost;
  Node parent;

  Node(int tileId) {
    this.tileId = tileId;
  }

  int getGCost() {
    if (this.parent != null) {
      return this.parent.getGCost() + 1;
    }
    return 0;
  }

  int getFCost() {
    return this.getGCost() + (int)this.hCost;
  }
}
