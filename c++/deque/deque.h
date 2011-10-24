#ifndef DEQUE_H_
#define DEQUE_H_

#include <string>
#include <iostream>
#include <sstream>

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

// From the point of view of the compiler, templates are not normal functions
// or classes. They are compiled on demand, meaning that the code of a template
// function is not compiled until an instantiation with specific template
// arguments is required.
//
// Because templates are compiled when required, the implementation of a 
// template class or function must be in the same file as its declaration.
template <class T>
Deque<T>::Deque() {
  head_ = NULL;
}

template <class T>
void Deque<T>::PushFront(const T& item) {
  Node<T>* new_node;  
  if (head_ == NULL) {
    new_node = MakeNewNode(item); 
    new_node->next = new_node;
    new_node->prev = new_node;
  } else {
    new_node = MakeNewNode(item, head_, head_->prev);
    head_->prev->next = new_node;
    head_->prev = new_node;
  }
  head_ = new_node;
}

template <class T>
void Deque<T>::PushBack(const T& item) {
  Node<T>* new_node;  
  if (head_ == NULL) {
    new_node = MakeNewNode(item); 
    new_node->next = new_node;
    new_node->prev = new_node;
    head_ = new_node;
  } else {
    new_node = MakeNewNode(item, head_, head_->prev);
    head_->prev->next = new_node;
    head_->prev = new_node;
  }
}

template <class T>
Node<T>* Deque<T>::PopFront() {  
  if (head_ == NULL)
    return NULL;
  
  Node<T>* node = head_;
  if (head_ == head_->next) {
    head_ = NULL;
    return node;
  }    
  head_ = head_->next;
  head_->prev = node->prev;
  node->prev->next = head_;
  return node;
}

template <class T>
Node<T>* Deque<T>::PopBack() {  
  if (head_ == NULL)
    return NULL;
  
  Node<T>* node = head_->prev;
  if (head_ == head_->prev) {
    head_ = NULL;
    return node;
  }    
  head_->prev = node->prev;
  node->prev->next = head_;
  return node;
}

template <class T>
string Deque<T>::ToString() {
  ostringstream oss;
  if (IsEmpty())
    return oss.str();
  Node<T>* cursor = head_;
  do {
    oss << cursor->value << " ";
    cursor = cursor->next;  
  } while (cursor != head_);
  return oss.str();
}

template <class T>
Node<T>* Deque<T>::MakeNewNode(const T& item, Node<T>* next, Node<T>* prev) {
  Node<T> *node = new Node<T>;
  node->value = item;
  node->next = next;
  node->prev = prev;
  return node;
} 
#endif // DEQUE_H_
