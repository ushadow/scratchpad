#include <iostream>
#include <stdlib.h>
#include <climits>
#include <time.h>
#include <cstdio>

namespace {

typedef unsigned char data_t;
#define DATA_MAX 256

#ifndef NUM_REPETITIONS
#define NUM_REPETITIONS 1
#endif

class StopWatch {
public:
  void tic() {
    tic_ = clock();
  }

  // Returns time elapsed in ms.
  int toc(const char* msg) {
    clock_t t = clock();
    int elapsed = int((t - tic_) * 1000 / CLOCKS_PER_SEC);
    printf("%s\telapsed: %d ms.\n", msg, elapsed);
    tic_ = t;
    return elapsed;
  }

private:
  clock_t tic_;
};

template<typename T>
void print_matrix(const T* M, int w, int h) {
  int i = 0;
  for (int y = 0; y < h; y++) {
    for (int x = 0; x < w; x++, i++)
      printf("%.1f ", float(M[i]));
    std::cout << std::endl;
  }
  std::cout << std::endl;
}

void print_usage(std::ostream& stream) {
  stream << "Usage: gradient  <width> <height>" << std::endl;
  stream << "  width, height: positive integers, and width * height <= INT_MAX." << std::endl; 
}

void init_random(data_t* M, int w, int h) {
  int base, base2;

  for (int y = 1; y <= h; y++) {
    base = y * (w + 2);
    for (int x = 1; x <= w; x++) {
      M[base + x] = rand() % DATA_MAX;
    }
  }

  base = w + 2; 
  for (int x = 1; x <= w; x++) {
    M[x] = M[base + x];
  }

  base = (h + 1) * (w + 2);
  base2 = base - w - 2; 
  for (int x = 1; x <= w; x++) {
    M[base + x] = M[base2 + x];
  }

  base = 0;
  for (int y = 1; y <= h; y++) {
    M[base] = M[base + 1];
    base += w + 2;
  }

  base = w + 1;
  for (int y = 1; y <= h; y++) {
    M[base] = M[base - 1];
    base += w + 2;
  }

}

void minmax(const short* d, int n, short* min, short* max) {
  *max = -DATA_MAX;
  *min = DATA_MAX;
  for (int i = 0; i < n; i++) {
    *max = d[i] > *max ? d[i] : *max;
    *min = d[i] < *min ? d[i] : *min;
  }
}

// Computes dx and dy in separate loops.
// This seems to be faster.
int gradient2(data_t* M, int w, int h) {
  data_t *My, *My0, *My1, *Mx0, *Mx1; // indices
  short *dx, *dy, *dxp, *dyp;
  short dx_max, dy_max, dx_min, dy_min;
  int w2 = w + 2;
  StopWatch sw;

  sw.tic();
  
  int n = w * h;
  dx = dxp = new short[n];
  dy = dyp = new short[n];

  Mx0 = M + w2 - 2;
  Mx1 = M + w2;
  for (int y = 1; y <= h; y++) {
    Mx0 += 2; 
    Mx1 += 2;  
    for (int x = 1; x <= w; x++) {
      *(dxp++) = *Mx1 - *Mx0;
      Mx1++; Mx0++;
    }
  }
//  minmax(dx, n, &dx_min, &dx_max);

  My0 = M - 1;
  My1 = M + w2 * 2 - 1;
  for (int y = 1; y <= h; y++) {
    My0 += 2;
    My1 += 2;
    for (int x = 1; x <= w; x++) {
      *(dyp++) = *My1 - *My0;
      My0++; My1++; 
    }
  }
  minmax(dy, n, &dy_min, &dy_max);

  int time = sw.toc("Total");

  printf("min dx = %.1f\n", dx_min * .5);
  printf("max dx = %.1f\n", dx_max * .5);
  printf("min dy = %.1f\n", dy_min * .5);
  printf("max dy = %.1f\n", dy_max * .5);

#ifdef DEBUG
  print_matrix(M, w, h);
  print_matrix(dx, w, h);
  print_matrix(dy, w, h);
#endif

  delete [] dx;
  delete [] dy;
  return time;
}

// w * h should be <= INT_MAX
int compute(int w, int h) {
  int n = (w + 2) * (h + 2);
  data_t* M = new data_t[n];
  init_random(M, w, h);
  int elapsed = gradient2(M, w, h);
  delete [] M;
  return elapsed;
}

bool is_valid_range(long int size) {
  return size > 0 && size <= INT_MAX;
}
}

int main(int argc, char* argv[]) {
  if (argc < 3) {
    print_usage(std::cerr);
    return 1;
  }

  long int width = strtol(argv[1], NULL, 10);
  long int height = strtol(argv[2], NULL, 10);
  
  if (!is_valid_range(width) || !is_valid_range(height) ||
      !is_valid_range(width * height)) {
    print_usage(std::cerr);
    return 1;
  }

  srand(time(NULL));

  int elapsed = 0;
  for (int i = 0; i < NUM_REPETITIONS; i++) {
    elapsed += ::compute(width, height);
  }

  if (NUM_REPETITIONS > 1)
    printf("Average elapsed: %d ms\n", elapsed / NUM_REPETITIONS);

  return 0;
}
