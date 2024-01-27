export class ClassesUtil {

  public static formatGroupOccupancy(numberOfPlacesTaken: number, numberOfPlacesOverall: number): string {
    return `${numberOfPlacesTaken}/${numberOfPlacesOverall}`;
  }
}
