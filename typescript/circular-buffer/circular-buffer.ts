import {isNullOrUndefined} from "util";

export class BufferEmptyError {}
export class BufferOverflowError {}

export default class CircularBuffer<T> {
    private position: number = 0
    private readPosition: number = 0
    private buffer: T[]

    constructor(size: number) {
        this.buffer = Array(size)
    }

    write(value: T) {
        if (!this.isEmpty(this.position)) throw CircularBuffer.OVERFLOW
        else {
            this.buffer[this.index(this.position)] = value
            this.position++
        }
    }

    read(): T {
        if (this.isEmpty(this.readPosition)) throw CircularBuffer.EMPTY
        else {
            let idx = this.index(this.readPosition)
            let value = this.buffer[idx]

            this.buffer[idx] = undefined
            this.readPosition++

            return value
        }
    }

    forceWrite(value: T) {
        if (this.isEmpty(this.position)) {
            this.write(value)
        } else {
            this.buffer[this.index(this.readPosition)] = value
            this.readPosition++
        }
    }

    clear() {
        this.buffer = new Array(this.buffer.length)
    }

    private index(position: number): number {
        return position % this.buffer.length
    }

    private isEmpty(position: number): boolean {
        return isNullOrUndefined(this.buffer[this.index(position)])
    }

    private static EMPTY = new BufferEmptyError()
    private static OVERFLOW = new BufferOverflowError()
}