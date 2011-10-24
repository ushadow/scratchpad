#include "deque.h"
#include <stdlib.h>
#include <iostream>

using namespace std;

int main() {
  Deque<int> deque;
  deque.PushBack(1);
  cout << deque.ToString() << endl;
   deque.PushBack(3);
  cout << deque.ToString() << endl;
  deque.PushFront(2);
  cout << deque.ToString() << endl;
  deque.PopBack();
  cout << deque.ToString() << endl;
  deque.PopFront();
  cout << deque.ToString() << endl;
  deque.PopFront();
  cout << deque.ToString() << endl;  
}
