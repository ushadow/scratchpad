package main

import (
  "math"
  "fmt"
)

const Eps = 1e-15

func Sqrt(x float64) float64 {
  z := x / 2
  for {
    z1 := z - (z * z - x) / (2 * z)
    if (math.Abs(z1 - z) < Eps) {
      break
    }
    z = z1
  }
  return z
}

func main() {
  fmt.Println(Sqrt(2))
}
