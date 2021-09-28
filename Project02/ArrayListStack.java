/*
 * Author: Dale Skrien, Linn Cao Nguyen Phuong
 * Date: March 1, 2021
 */

import java.util.ArrayList;

// implements a stack using an ArrayList to store the data
public class ArrayListStack<T>
{
    private ArrayList<T> data;

    public ArrayListStack() {
        this.data = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public int size() {
        return this.data.size();
    }

    public void push(T obj) {
        this.data.add(obj);
    }

    public T pop() {
        if (this.data.size() == 0) {
            return null;
        } 
        else {
            return this.data.remove(this.data.size()-1);
        }
    }

    public T peek() {
        if (this.data.size() == 0) {
            return null;
        } 
        else {
            return this.data.get(data.size()-1);
        }
    }

    // returns true if x is an element of the stack. Otherwise it returns false.
    public boolean contains(T x) {
        ArrayListStack<T> stack = new ArrayListStack<>();

        if (this.isEmpty() == true) {
            return false;
        }

        else {
            while (this.size() > 0) {
                if (this.peek() != x) {
                    stack.push(this.peek());
                    this.pop();
                }
                else {
                    return true;
                }
            }
            while (stack.size() > 0) {
                this.push(stack.pop());
            }
            return false;
        }
    }

    public static void main(String[] args) {
        ArrayListStack<Integer> stack = new ArrayListStack<>();
        stack.push(3);
        stack.push(4);
        System.out.println(stack.contains(3));
        System.out.println(stack.contains(5));
        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}

/* On line 57, I'm not sure why I can't push this.peek into stack, although both ArrayListStack's elements are of the same data type. */