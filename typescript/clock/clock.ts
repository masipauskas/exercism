export default class Clock {
    private hours: number
    private minutes: number

    constructor(hours: number, minutes: number = 0) {
        hours = hours + Math.floor(minutes / 60)
        this.hours = Clock.rolloverWithinRange(hours, 24)
        this.minutes = Clock.rolloverWithinRange(minutes, 60)
    }

    toString(): string {
        return `${this.hours.toString().padStart(2, "0")}:${this.minutes.toString().padStart(2, "0")}`
    }

    equals(other: Clock): boolean {
        return this.hours == other.hours && this.minutes == other.minutes
    }

    plus(minutes: number): Clock {
        return new Clock(this.hours, this.minutes + minutes)
    }

    minus(minutes: number): Clock {
        return this.plus(minutes * -1)
    }

    private static rolloverWithinRange(n: number, max: number) {
        return(n < 0 && n % max != 0) ? max + (n % max) : n % max
    }
}