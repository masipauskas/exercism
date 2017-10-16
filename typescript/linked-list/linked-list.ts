import {isUndefined} from "util";

export default class LinkedList<T> {
    private size: number = 0
    private head?: Node<T>
    private tail?: Node<T>

    count(): number {
        return this.size
    }

    pop(): T {
        if (isUndefined(this.tail)) throw "Tail of empty list";
        this.size--
        let t = this.tail.data
        this.tail = this.tail.left
        return t;
    }

    push(elem: T) {
        this.tail = new Node(elem, this.tail, void 0)
        this.size++
        if (isUndefined(this.head)) this.head = this.tail
    }

    shift(): T {
        if (isUndefined(this.head)) throw "Head of empty list";
        this.size--
        let h = this.head.data
        this.head = this.head.right
        return h;
    }

    unshift(elem: T) {
        this.size++
        this.head = new Node(elem, void 0, this.head)
        if (isUndefined(this.tail)) this.tail = this.head
    }

    delete(elem: T) {
        let node = this.head
        while (!isUndefined(node)) {
            if (node.data == elem) {
                if (!isUndefined(node.left)) {
                    new Node(node.left.data, node.left.left, node.right)
                } else {
                    this.head = node.right
                }
                this.size--
                return
            }

            node = node.right
        }
    }
}

class Node<T> {
    constructor(data: T, left?: Node<T>, right?: Node<T>) {
        this.data = data

        if (!isUndefined(left)) {
            this.left = left
            left.right = this
        }

        if (!isUndefined(right)) {
            this.right = right
            right.left = this
        }
    }

    data: T
    left: Node<T>
    right?: Node<T>
}