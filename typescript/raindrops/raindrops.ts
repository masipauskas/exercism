export default class Raindrops {
    convert(n: number): string {
        let factors: number[] = []

        let remainder = this.divide(n, 2)
        if (n != remainder) factors.push(2)
        for (let i = 3; i <= Math.sqrt(n); i = i + 2) {
            if (remainder % i == 0) {
                remainder = this.divide(remainder, i)
                factors.push(i)
            }
        }
        if (remainder > 2) factors.push(remainder)

        let result = factors.map((value) => {
            switch (value) {
                case 3: return "Pling"
                case 5: return "Plang"
                case 7: return "Plong"
                default: return ""
            }
        }).join("")

        return (result.length != 0) ? result : n.toString()
    }

    private divide(n: number, factor: number) {
        while (n % factor == 0) {
            n = n / factor
        }

        return n
    }
}