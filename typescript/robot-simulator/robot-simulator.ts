export default class Robot {
    private readonly directions = ['south', 'west', 'north', 'east']
    bearing: string
    coordinates: number[]

    constructor(x = 0, y = 0, bearing = 'north') {
        this.coordinates = [x, y]
        this.bearing = bearing
    }

    orient(direction: string) {
        if (!this.directions.some((d) => d === direction)) throw "Invalid Robot Bearing"
        else this.bearing = direction
    }

    turnLeft() { this.turn('left') }

    turnRight() { this.turn('right') }

    at(x: number, y: number) {
        this.coordinates = [x, y]
    }

    advance() {
        let x = 0
        let y = 0

        switch (this.bearing) {
            case 'south': y = -1
                break
            case 'north': y = 1
                break
            case 'east': x = 1
                break
            case 'west': x = -1
                break
        }

        this.coordinates = [this.coordinates[0] + x, this.coordinates[1] + y]
    }

    instructions(command: string) {
        return command
            .split('')
            .map((code) => {
                switch (code) {
                    case 'L': return 'turnLeft'
                    case 'R': return 'turnRight'
                    case 'A': return 'advance'
                }
                throw 'Invalid Command.'
            })
    }

    evaluate(command: string) {
        let commands = this.instructions(command)
        let that = this
        commands.forEach((cmd) => that[cmd]())
    }

    private turn(direction: string) {
        let offset = (direction == 'left') ? -1 : 1
        let newBearingIndex = Math.abs(4 + this.directions.indexOf(this.bearing) + offset) % 4

        this.bearing = this.directions[newBearingIndex]
    }
}