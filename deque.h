#ifndef DEQUE_H_
#define DEQUE_H_

#include <string>

using namespace std;

template <class T>
struct Node {
  T value;
  Node<T> *next, *prev;
};

template <class T>
class Deque {
  public:
  Deque();
  void PushBack(const T& item);
  void PushFront(const T& item);
  Node<T>* PopBack();
  Node<T>* PopFront();
  bool IsEmpty() { return head_ == NULL; }
  string ToString();  

  private:
  Node<T> *head_;
  Node<T>* MakeNewNode(const T& item, Node<T>* next = 0, Node<T>* prev = 0);
};
#endif // DEQUE_H_
