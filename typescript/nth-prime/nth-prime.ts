export default class Prime {
    private static readonly start: number = 2

    nth(n: number): number {
        if (n < 1) {
            throw new Error("Prime is not possible")
        }

        const sieve: number[] = []
        let int: number = Prime.start
        while (sieve.length < n) {
            if (sieve.every((n) => int % n !== 0)) {
                sieve.push(int)
            }
            int++
        }

        return sieve[sieve.length - 1]
    }
}