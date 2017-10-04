
import {isNumber} from "util";

export default class Bob {
    hey(phrase: string): string {
        const value = phrase.trim()
        if (this.isSilence(value)) { return "Fine. Be that way!" }
        else if (this.isYell(value)) { return "Whoa, chill out!" }
        else if (this.isQuestion(value)) { return "Sure." }
        else { return "Whatever." }
    }

    isQuestion(value: string): boolean {
        return value.endsWith("?")
    }

    isYell(value: string): boolean {
        const characters = value.split('')
        const isUpperCase = characters.every((v) => v.toUpperCase() === v)
        const isNumbersAndSpecialCharactersOnly = characters.every(this.isNumberOrSpecialCharacter)

        return isUpperCase && !isNumbersAndSpecialCharactersOnly
    }

    isSilence(value: string): boolean {
        return value === ""
    }

    isNumberOrSpecialCharacter(value: string): boolean {
        return /([\d,. ?])/.test(value)
    }
}