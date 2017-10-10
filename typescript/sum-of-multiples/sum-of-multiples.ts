export default function SumOfMultiples(multiples: number[]) {
    return new SumOfMultiplesImpl(multiples)
}

class SumOfMultiplesImpl {
    protected multiples: number[]
    constructor(multiples: number[]) {
        this.multiples = multiples
    }

    to(n: number): number {
        let result = 0
        for (let i = 1; i < n; i++) {
            if (this.isMultiple(i)) {
                result = result + i
            }
        }

        return result
    }

    isMultiple(n: number): boolean {
        return !(this.multiples.find((multiple) => n % multiple === 0) === undefined)
    }
}