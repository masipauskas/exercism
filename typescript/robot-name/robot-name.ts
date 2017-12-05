export default class RobotName {
    name: string

    constructor() {
        this.resetName()
    }

    private static firstLetterCode = 'A'.charCodeAt(0)
    private static character(): string {
        let letter = RobotName.random(25)
        return String.fromCharCode(RobotName.firstLetterCode + letter)
    }

    private static firstLetter = 'A'
    private static counter = 0
    private static number(): string {
        if (RobotName.counter == 1000) {
            RobotName.counter = 1
            RobotName.firstLetter = String.fromCharCode((RobotName.firstLetter.charCodeAt(0) + 1) % 26)
        } else {
            RobotName.counter++
        }

        return ("00" + (RobotName.counter - 1).toString()).slice(-3)
    }

    private static random(max: number): number {
        return Math.floor((Math.random() * max) % max)
    }

    resetName() {
        this.name = "".concat(
            RobotName.firstLetter,
            RobotName.character(),
            RobotName.number()
        )
    }
}