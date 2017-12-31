export default class Beer {

    static verse(n: number): string {
        return [
            Beer.capitalize(Beer.firstPart(n)),
            Beer.capitalize(Beer.secondPart(n)),
            ''
        ].join('\n')
    }

    static sing(...params: number[]): string {
        let result = ''
        let start = params[0] || 99
        let end = params[1] || 0

        while (start >= end) {
            result = result + Beer.verse(start) + "\n"
            start = start - 1
        }

        return result.trim() + "\n"
    }

    private static nBottlesOfBeer(n: number) {
        if (n == 0) return "no more bottles"
        else if (n == 1) return "1 bottle"
        else return `${n} bottles`
    }

    private static secondPart(n: number) {
        if (n == 0) return "Go to the store and buy some more, 99 bottles of beer on the wall."
        else return `Take ${n == 1 ? "it" : "one"} down and pass it around, ${Beer.nBottlesOfBeer(n - 1)} of beer on the wall.`
    }

    private static firstPart(n: number) {
        return `${Beer.nBottlesOfBeer(n)} of beer on the wall, ${Beer.nBottlesOfBeer(n)} of beer.`
    }

    private static capitalize(text: string): string {
        let firstLetter = text.charAt(0).toUpperCase()
        return firstLetter + text.slice(1)
    }
}