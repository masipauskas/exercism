export default class Say {
    private scales = [
        new Scale("billion", 1000000000),
        new Scale("million", 1000000),
        new Scale("thousand", 1000),
        new Scale("hundred", 100)
    ]

    inEnglish(n: number): string {
        if (!(n >= 0 && n <= 999999999999)) throw 'Number must be between 0 and 999,999,999,999.'

        let result = ""
        let remainder = n

        for(let scale of this.scales) {
            result += this.scale(remainder, scale)
            remainder = this.remainder(remainder, scale)
        }

        if (n == 0 || remainder != 0) result += this.digitToWords(remainder)

        return result.trim()
    }

    private digitToWords(n: number): string {
        switch (n) {
            case 0: return 'zero'
            case 1: return 'one'
            case 2: return 'two'
            case 3: return 'three'
            case 4: return 'four'
            case 5: return 'five'
            case 6: return 'six'
            case 7: return 'seven'
            case 8: return 'eight'
            case 9: return 'nine'
            case 10: return 'ten'
            case 11: return 'eleven'
            case 12: return 'twelve'
            case 13: return 'thirteen'
            case 14: return 'fourteen'
            case 15: return 'fifteen'
            case 16: return 'sixteen'
            case 17: return 'seventeen'
            case 18: return 'eighteen'
            case 19: return 'nineteen'
            case 20: return 'twenty'
            case 30: return 'thirty'
            case 40: return 'forty'
            case 50: return 'fifty'
            case 60: return 'sixty'
            case 70: return 'seventy'
            case 80: return 'eighty'
            case 90: return 'ninety'
            default: {
                let remainder = n % 10
                let decimal = n - remainder

                return `${this.digitToWords(decimal)}-${this.digitToWords(remainder)}`
            }
        }
    }

    private scale(n: number, scale: Scale): string {
        if (n < scale.scale) return ""

        let number = Math.floor(n / scale.scale)
        return `${this.inEnglish(number)} ${scale.name} `
    }

    private remainder(n: number, scale: Scale): number {
        return n % scale.scale
    }
}

class Scale {
    constructor(public name: string, public scale: number) {}
}
