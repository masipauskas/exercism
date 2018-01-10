export default class Triangle {
    rows: number[][] = []
    lastRow: number[] = []

    constructor(n: number) {
        function value(position: number, row: number): number {
            if (position == 0 || position == row - 1) return 1
            else if (position == row) return 0
            else {
                return value(position - 1, row - 1) + value(position, row - 1)
            }
        }

        for (let i = 1; i <= n; i++) {
            let row = []
            for (let j = 0; j < i; j++) {
                row.push(value(j, i))
            }

            this.rows.push(row)
        }

        this.lastRow = this.rows[n - 1]
    }
}
