export default function calculatePrimeFactors(n: number): number[] {
    function calculatePrime(n: number): number[] {
        let divisor = 2
        let divisors = []
        let remainder = n

        do {
            if (remainder % divisor == 0) {
                divisors.push(divisor)
                remainder = remainder / divisor
            } else {
                divisor++
            }
        } while (remainder != 1)

        return divisors
    }

    return (n != 0) ? calculatePrime(n) : [];
}