export default class Pangram {
    text: string
    letters: string[]

    constructor(text: string) {
        this.text = text.toLocaleLowerCase()
        this.letters = this.alphabet()
    }

    isPangram() {
        return this.letters.every((letter) => this.text.includes(letter) )
    }

    private alphabet() {
        return Array(26).fill(0).map ((_, index) => String.fromCharCode(97 + index))
    }
}