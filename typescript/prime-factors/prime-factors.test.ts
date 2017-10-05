import calculatePrimeFactors from './prime-factors'

describe('calculatePrimeFactors', () => {
  test('returns an empty array for 1', () => expect(calculatePrimeFactors(1)).toEqual([]))

  test('factors 2', () => expect(calculatePrimeFactors(2)).toEqual([2]))

  test('factors 3', () => expect(calculatePrimeFactors(3)).toEqual([3]))

  test('factors 4', () => expect(calculatePrimeFactors(4)).toEqual([2, 2]))

  test('factors 6', () => expect(calculatePrimeFactors(6)).toEqual([2, 3]))

  test('factors 8', () => expect(calculatePrimeFactors(8)).toEqual([2, 2, 2]))

  test('factors 9', () => expect(calculatePrimeFactors(9)).toEqual([3, 3]))

  test('factors 27', () => expect(calculatePrimeFactors(27)).toEqual([3, 3, 3]))

  test('factors 625', () => expect(calculatePrimeFactors(625)).toEqual([5, 5, 5, 5]))

  test('factors 901255', () => expect(calculatePrimeFactors(901255)).toEqual([5, 17, 23, 461]))

  test('factors 93819012551', () => expect(calculatePrimeFactors(93819012551)).toEqual([11, 9539, 894119]))
})