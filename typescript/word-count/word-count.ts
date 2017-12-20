export default class Words {
    count(text: String): Map<string, number> {
        let result = new Map()
        let tokens = text
            .trim()
            .replace(/\s+/g, ' ')
            .toLowerCase()
            .split(" ")

        tokens
            .forEach((token) => {
                if (!result.has(token)) result.set(token, 0)

                result.set(token, result.get(token) + 1)
            })

        return result
    }
}