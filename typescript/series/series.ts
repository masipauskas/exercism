export default class Series {
    digits: number[]
    constructor(test: string) {
        this.digits = test.split('').map((value => parseInt(value, 10)))
    }

    slices(n: number) {
        let steps = this.digits.length - n
        if (steps < 0) throw "n is larger than a list of steps needed"

        let result = []
        for(let i = 0; i <= steps; i++) {
            result.push(this.digits.slice(i, i + n))
        }

        return result
    }
}