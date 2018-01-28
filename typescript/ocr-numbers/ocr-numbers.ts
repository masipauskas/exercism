class DefaultParser {
    value: string = '?'
}

class Parser {
    private pattern: string[][]
    value: string

    constructor(_value: string, _pattern: string) {
        this.value = _value
        this.pattern = _pattern
            .split('\n')
            .map(line => line.split(''))
    }

    canParse(_line: number, _position: number, text: string[][]): boolean {
        return this.pattern.every((line, lIdx) => {
            return line.every((character, cIdx) => {
                let l = (_line) * 4 + lIdx
                let p = (_position) * 3 + cIdx
                return character == text[l][p]
            })
        })
    }
}

export default class OcrParser {
    private static parsers: Parser[] = [
        new Parser('0', " _ \n| |\n|_|"),
        new Parser('1', "   \n  |\n  |"),
        new Parser('2', " _ \n _|\n|_"),
        new Parser('3', " _ \n _|\n _|"),
        new Parser('4', "   \n|_|\n  |"),
        new Parser('5', " _ \n|_ \n _|"),
        new Parser('6', " _ \n|_ \n|_|"),
        new Parser('7', " _ \n  |\n  |"),
        new Parser('8', " _ \n|_|\n|_|"),
        new Parser('9', " _ \n|_|\n _|")

    ]
    static convert(text: string): string {
        let characters = text.split('\n')
            .map(c => c.split(''))

        let length = characters[0].length / 3
        let lines = characters.length / 4

        let result = ''
        for (let l = 0; l < lines; l++) {
            if (l > 0) {
                result += ','
            }

            for (let i = 0; i < length; i++) {
                let parser = OcrParser.parsers.find(p => p.canParse(l, i, characters)) || new DefaultParser()
                result += parser.value
            }
        }

        return result
    }
}
