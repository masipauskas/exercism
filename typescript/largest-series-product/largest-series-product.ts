export default class Series {
    protected series: number[] = []

    public constructor(series: string) {
        this.series = series.split("")
            .map((digit) => Number.parseInt(digit))
    }

    largestProduct(n: number): number {
        function product(series: number[], offset: number) {
            let result = series[offset]

            for (let i = 1; i < n; i++) {
                result = result * series[offset + i]
            }

            return result
        }

        if (this.series.length < n) throw "Slice size is too big."
        else if (this.series.some((value => isNaN(value))) || n < 0) throw "Invalid input."
        else if (n == 0) return 1
        else {
            let max = product(this.series, 0)
            for (let i = 1; i <= this.series.length - n; i++) {
                let current = product(this.series, i)
                max = (current > max) ? current : max
            }

            return max
        }
    }
}