import {isUpperCase} from "tslint/lib/utils";

export default class Acronym {
  static parse(phrase: string): string {
      let acronymWords = Acronym.acronymPhrase(phrase)
          .split(" ")

      return acronymWords
          .map(Acronym.acronymLetter)
          .join('')
  }

  private static acronymPhrase(phrase: string): string {
      let punctuation = /\W/g

      let acronymBoundary = phrase.indexOf(":") >= 0 ? phrase.indexOf(":") : phrase.length

      return phrase
          .substr(0, acronymBoundary)
          .replace(punctuation, " ")
          .replace("-", " ")

  }

  private static acronymLetter(word: string): string {
      let rest = word.substr(1)
          .split('')
          .filter(isUpperCase)
          .join('')

      return word.charAt(0).toUpperCase() + rest
  }
}
