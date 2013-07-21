import scala.collection.immutable.HashMap
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

object Solution {
  val move = HashMap((-1, 0) -> "LEFT", (1, 0) -> "RIGHT", (0, 1) -> "DOWN",
                     (0, -1) -> "UP")

  def main(args: Array[String]) = {
    val m = Console.readLine.toInt
    val grid = new Array[String](m)
    for (i <- 0 until m) {
      grid.update(i, Console.readLine)
    }
    displayPathtoPrincess(m,grid)
    }

  /* Refer to Output format section for more details */
  /* Head ends here */
  /**
   * @param m size of the grid.
   */
  def displayPathtoPrincess(m: Int, grid: Array[String]) = {
    val startPos = findStartPosition(grid)
    val q = new Queue[(Int, Int)]
    val parent = Array.ofDim[(Int, Int)](m, m)
    parent(startPos._2)(startPos._1) = startPos
    q.enqueue(startPos)
    breakable {
      while (!q.isEmpty) {
        val pos = q.dequeue
        if (isPrincess(pos, grid)) {
          printPath(pos, parent)
          break
        } else {
          move foreach { case(k, v) =>
            val next = (pos._1 + k._1, pos._2 + k._2)
            if (isValidPos(next, parent)) {
              q.enqueue(next)
              parent(next._2)(next._1) = pos
            }
          }
        }
      }
    }
  }

  def findStartPosition(grid: Array[String]): (Int, Int) = {
    grid.zipWithIndex foreach { case(row, y) =>
        val x = row.indexOf('m')
        if (x >= 0)
            return (x, y)
    }
    (0, 0)
  }

  def isPrincess(pos: (Int, Int), grid: Array[String]): Boolean = {
    return grid(pos._2).charAt(pos._1) == 'p'
  }

  def printPath(start: (Int, Int), parent: Array[Array[(Int, Int)]]) = {
    val path = new ListBuffer[String]
    var cur = start
    breakable {
      while (true) {
        val prev = parent(cur._2)(cur._1)
        if (cur != prev) {
          path.prepend(move((cur._1 - prev._1, cur._2 - prev._2)))
          cur = prev
        }
        else
          break
      }
    }
    path foreach ( e => println(e))
  }

  def isValidPos(pos: (Int, Int), parent: Array[Array[(Int, Int)]]): Boolean = {
    val size = parent.length
    return pos._1 >= 0 && pos._1 < size && pos._2 >= 0 && pos._2 < size &&
        parent(pos._2)(pos._1) == null
  }
/* Tail starts here */
}
