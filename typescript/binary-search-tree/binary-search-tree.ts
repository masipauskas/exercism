import {isNullOrUndefined} from "util"

export default class BinarySearchTree {
    data: number
    left: BinarySearchTree
    right: BinarySearchTree

    constructor(root: number) {
        this.data = root
    }

    insert(n: number) {
        if (n > this.data) {
            this.right = BinarySearchTree.insertOrCreate(n, this.right)
        } else {
            this.left = BinarySearchTree.insertOrCreate(n, this.left)
        }
    }

    each(func: (n: number) => void) {
        BinarySearchTree.innerEach(this.left, func)
        func(this.data)
        BinarySearchTree.innerEach(this.right, func)
    }

    private static innerEach(node: BinarySearchTree, func: (n: number) => void) {
        if (!isNullOrUndefined(node)) {
            node.each(func)
        }
    }

    private static insertOrCreate(n: number, node?: BinarySearchTree) {
        if (!isNullOrUndefined(node)) {
            node.insert(n)
            return node
        }
        else {
            return new BinarySearchTree(n)
        }
    }
}