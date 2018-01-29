export default class Diamond {
    makeDiamond(letter: string): string {
        let start = 'A'.charCodeAt(0)
        let end = letter.charCodeAt(0)
        let result = ''

        for (let i = end; i >= start; i--) {
            let line = this.makeLine(end - start + 1, i - start + 1, String.fromCharCode(i))

            if (i == end) {
                result = line
            } else {
                result = line + result + line
            }
        }

        return result
    }

    private makeLine(width: number, line: number, letter: string) {
        function pad(n: number): string {
            let result = ''
            for (let i = 0; i < n; i++) {
                result += ' '
            }

            return result
        }

        let padding = width - line
        let innerPadding = (width - padding - 1) * 2 - 1

        if (line == 1) {
            return pad(padding) + letter + pad(padding) + '\n'
        }

        return pad(padding) + letter + pad(innerPadding) + letter + pad(padding) + '\n'
    }
}