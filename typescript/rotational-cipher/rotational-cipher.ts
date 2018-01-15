export default class RotationalCipher {
    private static uppercase = "A".charCodeAt(0)
    private static lowercase = "a".charCodeAt(0)
    static rotate(text: string, n: number) {
        return text.split('')
            .map((letter) => RotationalCipher.rotateLetter(letter, n))
            .join('');
    }

    private static rotateLetter(text: string, n: number) {
        if (RotationalCipher.isLetter(text)) {
            let bounds = RotationalCipher.isLowercase(text) ? RotationalCipher.lowercase : RotationalCipher.uppercase
            return String.fromCharCode(bounds + ((text.charCodeAt(0) - bounds + n) % 26))
        }

        return text;
    }

    private static isLetter(text: string) {
        return text.toLowerCase() != text.toUpperCase()
    }

    private static isLowercase(text: string) {
        return text.toLowerCase() == text
    }
}
