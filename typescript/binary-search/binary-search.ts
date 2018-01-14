import {isNullOrUndefined} from "util";

export default class BinarySearch {
    array: number[] | undefined = undefined
    constructor(array: number[]) {
        for (let index = 0; index < array.length -1; index++) {
            if (array[index] > array[index + 1]) return
        }

        this.array = array
    }

    indexOf(n: number) {
        function find(left: number, right: number, array: number[]): number | undefined {
            if (left > right || (left == right && array[left] != n)) return -1

            let middle = left + Math.round((right - left) / 2)

            if (array[middle] == n) return middle
            else if (array[middle] < n) return find(middle + 1, right, array)
            else return find(left, middle - 1, array)
        }

        if (isNullOrUndefined(this.array)) return undefined
        else return find(0, this.array.length - 1, this.array);
    }
}
