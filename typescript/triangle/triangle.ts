export default class Triangle {
    a: number
    b: number
    c: number

    constructor(...sides: number[]) {
        this.a = sides[0]
        this.b = sides[1]
        this.c = sides[2]
    }

    kind() {
        if (!TriangleValidation.isValid(this)) {
            throw "The triangle must be a valid triangle"
        }

        else if (this.isEquilateral()) {
            return "equilateral"
        } else if (this.isIsosceles()) {
            return "isosceles"
        }

        return "scalene"
    }

    private isEquilateral() {
        return this.a == this.b && this.b == this.c
    }

    private isIsosceles() {
        return this.a == this.b || this.b == this.c || this.c == this.a
    }
}

class TriangleValidation {
    static isValid(triangle: Triangle): boolean {
        function isValidTriangleSide(side: number, ...otherSides: number[]) {
            return otherSides.reduce(((previousValue, currentValue) => previousValue + currentValue)) > side && side > 0
        }

        let valid = true

        valid = valid && isValidTriangleSide(triangle.a, triangle.b, triangle.c)
        valid = valid && isValidTriangleSide(triangle.b, triangle.a, triangle.c)
        valid = valid && isValidTriangleSide(triangle.c, triangle.a, triangle.b)

        return valid;
    }
}