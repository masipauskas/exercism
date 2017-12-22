export default class Hamming {
    compute(left: string, right: string): number {
        if (left.length != right.length) throw 'DNA strands must be of equal length.'

        return left
            .split('')
            .filter((value, index) => value != right.charAt(index))
            .length
    }
}