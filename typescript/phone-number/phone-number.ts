import {isNullOrUndefined} from "util";

export default class PhoneNumber {
    private phoneNumber: string

    constructor(number: string) {
        this.phoneNumber = number
    }

    number(): string | undefined {
        let pattern = /^\+?1?\D*(\d{3})\D*([2-9]\d{2})\D*(\d{4})\D*$/g
        let match = pattern.exec(this.phoneNumber)

        if (!isNullOrUndefined(match)) {
            return `${match[1]}${match[2]}${match[3]}`
        } else {
            return undefined;
        }
    }
}