interface ILetterToScore {
    [key: string]: number
}

interface IScoreToLetters {
    [key: number]: string[]
}

export default function transform(scores: IScoreToLetters): ILetterToScore {
    let result: ILetterToScore = {}

    for (let score in scores) {
        for (let letter of scores[score]) {
            result[letter.toLowerCase()] = parseInt(score)
        }
    }

    return result
}