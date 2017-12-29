export default class Anagram {
    private word: string

    constructor(word: string) {
        this.word = word.toLowerCase()
    }

    matches(...candidates: string[]) {
        return candidates
            .filter((candidate) => this.isSameLength(candidate, this.word))
            .filter((candidate) => this.isNotSameWord(candidate, this.word))
            .filter((candidate) => this.hasSameLetters(candidate, this.word))
    }

    private isSameLength(candidate: string, word: string): boolean {
        return candidate.length == word.length
    }

    private isNotSameWord(candidate: string, word: string): boolean {
        return candidate.toLowerCase() != word
    }

    private hasSameLetters(candidate: string, word: string): boolean {
        let wordLetters = Anagram.letters(word)
        let candidateLetters = Anagram.letters(candidate)

        return candidateLetters.every((letter, index) => letter == wordLetters[index] )
    }

    private static letters(word: string): string[] {
        let letters = word.toLowerCase().split('')
        letters.sort()
        return letters
    }
}