package algo

import (
  "math"
  "testing"
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

func TestSqrt(t *testing.T) {
  actual := Sqrt(2)
  expected := math.Sqrt(2)
  t.Logf("Sqrt(2) = %v, expected = %v\n", actual, expected)
  if (math.Abs(actual - expected) > Eps) {
    t.Error("Wrong Sqrt result.")
  }
  actual = Sqrt(0.1)
  expected = math.Sqrt(0.1)
  t.Logf("Sqrt(0.1) = %v, expected = %v\n", actual, expected)
  if (math.Abs(actual - expected) > Eps) {
    t.Error("Wrong Sqrt result.")
  }
  actual = Sqrt(1)
  expected = math.Sqrt(1)
  t.Logf("Sqrt(1) = %v, expected = %v\n", actual, expected)
  if (math.Abs(actual - expected) > Eps) {
    t.Error("Wrong Sqrt result.")
  }
}
