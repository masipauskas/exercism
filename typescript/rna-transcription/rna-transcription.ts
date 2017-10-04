export default class RnaTranscription {
    toRna(dna: string) {
        const result = []
        for (const char of dna) {
            result.push(this.toRnaStrand(char))
        }
        return result.join("")
    }

    toRnaStrand(dnaStrand: string) {
        switch (dnaStrand) {
            case "G": return "C"
            case "C": return "G"
            case "T": return "A"
            case "A": return "U"
        }

        throw new Error("Invalid input DNA.")
    }
}