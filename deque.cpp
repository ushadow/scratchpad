#include "deque.h"
#include <sstream>
#include <stdlib.h>

using namespace std;

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
    head_ = new_node;
  }
}

template <class T>
void Deque<T>::PushBack(const T& item) {
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
  if (head_ == NULL)
    return oss.str();
  Node<T>* cursor = head_;
  do {
    oss << cursor->value << " ";
    cursor = cursor->next;  
  } while (cursor != head_);
  return oss.str();
}

template <class T>
Node<T>* MakeNewNode(const T& item, Node<T>* next, Node<T>* prev) {
  Node<T> *node = new Node<T>;
  node->next = next;
  node->prev = prev;
  return node;
} 
