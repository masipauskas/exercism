export class TwoBucket {
    private left: Bucket
    private right: Bucket

    goalBucket: string
    otherBucket: number

    public constructor(firstBucket: number, secondBucket: number, private goal: number, startingBucket: string) {
        let one = new Bucket(firstBucket, Bucket.One)
        let two = new Bucket(secondBucket, Bucket.Two)

        if (startingBucket == one.name()) {
            this.left = one
            this.right = two
        } else {
            this.left = two
            this.right = one
        }

        this.left.fill()
    }

    moves(): number {
        let m = 1

        while(!this.reachedGoal()) {
            if (this.left.isEmpty()) {
                this.left.fill()
            } else if (this.right.isFull()) {
                this.right.empty()
            } else {
                this.left.pour(this.right)
            }

            m += 1
        }

        if (this.left.reachedGoal(this.goal)) {
            this.update(this.left, this.right)
        } else {
            this.update(this.right, this.left)
        }

        return m
    }

    private reachedGoal() {
        return this.left.reachedGoal(this.goal) || this.right.reachedGoal(this.goal)
    }

    private update(goal: Bucket, other: Bucket) {
        this.goalBucket = goal.name()
        this.otherBucket = other.volume()
    }
}

export class Bucket {
    static One = 'one'
    static Two = 'two'

    private currentVolume: number = 0

    public constructor (private capacity: number, private tag: string) {}
    fill() {
        this.currentVolume = this.capacity
    }

    empty() {
        this.currentVolume = 0
    }

    pour(to: Bucket) {
        let volumeToPour = (this.currentVolume > (to.capacity - to.currentVolume)) ? to.capacity - to.currentVolume : this.currentVolume
        to.currentVolume += volumeToPour
        this.currentVolume -= volumeToPour
    }

    isEmpty() {
        return this.currentVolume == 0
    }

    isFull() {
        return this.currentVolume == this.capacity
    }

    reachedGoal(goal: number) {
        return this.currentVolume == goal
    }

    volume() {
        return this.currentVolume
    }

    name() {
        return this.tag
    }
}