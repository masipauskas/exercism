import {isUndefined} from "util";

export default class Triplet {
    constructor(private a: number, private b: number, private c: number) {}

    sum() {
        return this.a + this.b + this.c
    }

    product() {
        return this.a * this.b * this.c
    }

    isPythagorean() {
        function square(x: number) {
            return Math.pow(x, 2)
        }

        return square(this.a) + square(this.b) == square(this.c)
    }

    static where(_to: number | undefined = undefined, _from: number | undefined = undefined, _sum: number | undefined = undefined) {
        let sumPredicate = (isUndefined(_sum)) ? (_: Triplet) => true : (n: Triplet) => n.sum() == _sum

        let to = _to || 1
        let from = _from || 1
        let triplets = []

        for (let a = from; a <= to; a++) {
            for (let b = a + 1; b <= to; b++) {
                for (let c = b + 1; c <= to; c++) {
                    triplets.push(new Triplet(a, b, c))
                }
            }
        }

        return triplets
            .filter((triplet) => triplet.isPythagorean())
            .filter(sumPredicate)
    }
}