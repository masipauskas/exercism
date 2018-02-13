export default class AtbashCipher {
    private encoder: Map<string, string> = new Map()
    private decoder: Map<string, string> = new Map()

    constructor() {
        let alphabet = "abcdefghijklmnopqrstuvwxyz"
        this.buildCypher(alphabet)
    }

    encode(text: string): string {
        let whitespace = /[^\w\d]/g

        let encoded = text
            .toLowerCase()
            .replace(whitespace, '')
            .split('')
            .map(c => AtbashCipher.convertChar(c, this.encoder))

        return AtbashCipher.chunk(encoded).map(v => v.join('')).join(' ')
    }

    decode(text: string): string {
        return text
            .replace(' ', '')
            .split('')
            .map(c => AtbashCipher.convertChar(c, this.decoder))
            .join('')
    }

    private buildCypher(alphabet: string) {
        let key = alphabet.split('')
        let code = key.slice().reverse()

        key.forEach((v, idx) => {
            this.encoder.set(v, code[idx])
            this.decoder.set(code[idx], v)
        })
    }

    private static convertChar(char: string, codec: Map<string, string>) {
        return codec.get(char) || char
    }

    private static chunk(array: string[], size: number = 5): string[][] {
        let chunks = []
        let i = 0
        let n = array.length;

        while (i < n) {
            chunks.push(array.slice(i, i += size));
        }

        return chunks;
    }
}