import java.util.*;

public class MinStack {

    PriorityQueue<Integer> heap;
    Stack<Integer> stack;

    public MinStack() {
        heap = new PriorityQueue<>();
        stack = new Stack<>();
    }

    // push element x onto stack
    public void push (int x) {
        heap.offer(x);
        stack.push(x);
    }

    // remove element on top of stack
    public void pop() {
        int x = stack.pop();
        heap.remove(x);
    }

    // get top element
    public int top() {
        return stack.peek();
    }

    // get minimum element in stack
    public int getMin() {
        int x = heap.poll();
        return x;
    }



}
