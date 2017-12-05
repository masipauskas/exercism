export default class GradeSchool {
    private db: Map<number, Array<string>> = new Map()

    private grades(grade: number, initialize: boolean = false) {
        if (initialize && !this.db.has(grade)) {
            this.db.set(grade, [])
        }

        return this.db.get(grade) || []
    }

    studentRoster(): Map<string, Array<string>> {
        let roster = new Map()

        for (let grade of this.db.keys()) {
            roster.set(grade.toString(), this.studentsInGrade(grade))
        }

        return roster
    }

    studentsInGrade(grade: number): Array<string> {
        return this.grades(grade).slice().sort()
    }

    addStudent(name: string, grade: number) {
        this.grades(grade, true).push(name)
    }
}