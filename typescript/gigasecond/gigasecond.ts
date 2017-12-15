export default class Gigasecond {
    private static readonly gigasecondInMs: number = 1000000000 * 1000

    private value: Date

    constructor(date: Date) {
        this.value = new Date(date.getTime() + Gigasecond.gigasecondInMs)
    }

    date(): Date { return this.value }
}