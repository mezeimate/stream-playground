package brickset;

import java.util.stream.Collectors;

import repository.Repository;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Visszaadja a Duplo legok darabszámát.
     * @return mennyi darab duplo fajta lego van.
     */
    public long countLegoSetsDuplo() {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals("Duplo"))
                .count();
    }

    /**
     * Kiírja a consolera azokat a fajtákat amik hossza nagyobb mint 5.
     */
    public void printLegoSetsTheme(){
        getAll().stream().filter(legoSet -> legoSet.getTheme().length() > 5)
                .collect(Collectors.groupingBy(LegoSet::getTheme))
                .forEach((s, legoSets) -> System.out.format("%s\n", s));
    }

    /**
     * Visszaadja a lego darabjainak átlagát.
     * @return lego darabszám átlaga.
     */
    public Double returnLegoSetsWithPiecesAverage(){
        return getAll().stream()
                .collect(Collectors.averagingInt(LegoSet::getPieces));
    }

    /**
     * Kiírja azokat a lego neveket egyszer amik a megadott kezdőbetűvel kezdődnek.
     * @param start lego név kezdő karaktere.
     */
    public void printLegoSetsWithStartNameChar(String start){
        getAll().stream()
                .filter(legoSet -> legoSet.getName().startsWith(start))
                .collect(Collectors.groupingBy(LegoSet::getName))
                .forEach((s, legoSets) -> System.out.format("%s\n", s));
    }

    /**
     * Kiírja az első lego elemet.
     */
    public void printLegoSetsFirstElement(){
        getAll().stream()
                .findFirst()
                .ifPresent(System.out::println);
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        //1
        System.out.println(repository.countLegoSetsDuplo()+"db Duplo lego van.");
        //2
        System.out.println("\nThemes: ");
        repository.printLegoSetsTheme();
        //3
        System.out.println("\nÁtlagos darabszám: "+repository.returnLegoSetsWithPiecesAverage());
        //4
        System.out.println();
        repository.printLegoSetsWithStartNameChar("K");
        //5
        System.out.println("\nElső elem:");
        repository.printLegoSetsFirstElement();
    }

}
