import {isNull} from "util";

export class WordProblem {
    private problem: string

    constructor(question: string) {
        this.problem = question
    }

    private statementParser =  /^What is (-?\d+) (((plus|minus|multiplied by|divided by) (-?\d+).?)*)\?$/g
    private actionParser = /(plus|minus|multiplied by|divided by) (-?\d+)/gm
    private firstDecimalParser = /(-?\d+)/gm
    answer(): number {
        if (this.statementParser.test(this.problem)) {
            const parseResult = this.problem.match(this.statementParser) || []
            const firstDecimal = (this.firstDecimalParser.exec(parseResult[0]) || ["0"]) [0]
            let result = parseFloat(firstDecimal)

            let lastParseResult = this.actionParser.exec(parseResult[0])
            while (!isNull(lastParseResult)) {
                const nextValue = parseFloat(lastParseResult[2])
                switch (lastParseResult[1]) {
                    case "plus":
                        result = result + nextValue
                        break
                    case "minus":
                        result = result - nextValue
                        break
                    case "multiplied by":
                        result = result * nextValue
                        break
                    case "divided by":
                        result = result / nextValue
                        break
                }

                lastParseResult = this.actionParser.exec(parseResult[0])
            }

            return result
        } else {
            throw ArgumentError.INSTANCE
        }
    }
}

export class ArgumentError {
    static INSTANCE = new ArgumentError()
}
