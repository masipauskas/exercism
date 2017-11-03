type Predicate<T> = (n: T) => Boolean

function not<T>(p: Predicate<T>): Predicate<T> {
    return (n: T) => !p(n)
}

export function keep<T>(collection: T[], predicate: Predicate<T>): T[] {
    const result: T[] = []

    collection.forEach((value) => {
        if (predicate(value)) { result.push(value) }
    })

    return result
}

export function discard<T>(collection: T[], predicate: Predicate<T>): T[] {
    return keep(collection, not(predicate))
}