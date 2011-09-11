#include "deque.h"
#include <stdlib.h>
#include <iostream>

using namespace std;

int main() {
  Deque<int> deque;
  deque.PushBack(1);
  cout << deque.ToString() << endl;
}
