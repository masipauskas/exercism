import {isNullOrUndefined} from "util";

class Animal {
    constructor(public name: string, public description: string | undefined = undefined, public suffix: string = "") {}
}

export default class FoodChain {
    private static _animals = [
        new Animal("fly"),
        new Animal("spider", "It wriggled and jiggled and tickled inside her.", " that wriggled and jiggled and tickled inside her"),
        new Animal("bird", "How absurd to swallow a bird!"),
        new Animal("cat", "Imagine that, to swallow a cat!"),
        new Animal("dog", "What a hog, to swallow a dog!"),
        new Animal("goat", "Just opened her throat and swallowed a goat!"),
        new Animal("cow", "I don't know how she swallowed a cow!"),
        new Animal("horse")
    ]

    static verse(v: number): string {
        let foodChain = this._animals.slice(0, v - 1).reverse()
        let lastAnimal = this._animals[v - 1]

        let verse = `I know an old lady who swallowed a ${lastAnimal.name}.\n`
        if (!isNullOrUndefined(lastAnimal.description)) {
            verse += `${lastAnimal.description}\n`
        }

        if (v == FoodChain._animals.length) {
            verse += "She's dead, of course!\n"
        } else {
            for (let animal of foodChain) {
                verse += `She swallowed the ${lastAnimal.name} to catch the ${animal.name}${animal.suffix}.\n`

                lastAnimal = animal
            }
            verse += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
        }
        return verse
    }

    static verses(from: number, to: number): string {
        let song = ""

        for (let v = from; v <= to; v++) {
            song += `${this.verse(v)}\n`
        }

        return song.trim() + "\n"
    }
}