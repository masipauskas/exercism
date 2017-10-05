export default class SpaceAge {
    private static readonly EarthYearInSeconds: number = 31557600
    private static readonly EarthScale: number = 1;
    private static readonly MercuryScale: number = 0.2408467
    private static readonly VenusScale: number = 0.61519726
    private static readonly MarsScale: number = 1.8808158
    private static readonly JupiterScale: number = 11.862615
    private static readonly SaturnScale: number = 29.447498
    private static readonly UranusScale: number = 84.016846
    private static readonly NeptuneScale: number = 164.79132
    seconds: number

    constructor(spaceAge: number) {
        this.seconds = spaceAge
    }

    onEarth(): number {
        return this.spaceAge(SpaceAge.EarthScale)
    }

    onMercury(): number {
        return this.spaceAge(SpaceAge.MercuryScale)
    }

    onVenus(): number {
        return this.spaceAge(SpaceAge.VenusScale)
    }

    onMars(): number {
        return this.spaceAge(SpaceAge.MarsScale)
    }

    onJupiter(): number {
        return this.spaceAge(SpaceAge.JupiterScale)
    }

    onSaturn(): number {
        return this.spaceAge(SpaceAge.SaturnScale)
    }

    onUranus(): number {
        return this.spaceAge(SpaceAge.UranusScale)
    }

    onNeptune(): number {
        return this.spaceAge(SpaceAge.NeptuneScale)
    }


    private spaceAge(scale: number): number {
        return Math.round((this.seconds / scale / SpaceAge.EarthYearInSeconds) * 100) / 100
    }
}