export default class Squares {
    sumOfSquares: number
    squareOfSums: number
    difference: number

    constructor(n: number) {
        let numbers = []
        for (let i = 1; i <= n; i++) {
            numbers.push(i)
        }

        this.sumOfSquares = numbers.reduce((acc, current) => acc + current * current)

        let sum = numbers.reduce((acc, current) => acc + current)
        this.squareOfSums = sum * sum

        this.difference = this.squareOfSums - this.sumOfSquares
    }


}