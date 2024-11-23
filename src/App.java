import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static double[] distances = { 54_600_000, 590_000_000, 1_200_000_000 }; // km
    // planets description
    static String[] descriptionPlanets = {
            "Marte es el llamado planeta rojo en la mitología romana, seleccionado por su color rojizo, este planeta es el cuarto en el sistema solar y el segundo más pequeño después de Mercurio",
            " Júpiter es el planeta mas grande del sistema solar y el segundo mas grande despues de Marte",
            "Saturno es el planeta mas grande del sistema solar y el tercer mas grande despues de Júpiter"
    };
    // Datos de nave
    static String[] ships = { "Falcon 9", "Starship", "SLS" };
    static double[] velocitysShip = { 27_000, 40_000, 29_000 }; // en km/h
    static int[] capacidadPasajeros = { 28, 40, 30 };
    // Datos de planetas
    static String[] planets = { "Marte", "Jupiter", "Saturno" };
    static double velocity = 100_000; // km/h
    static double[] averages = { 550, 1.7, 2.5, 350 };// {litros/dia, kg/dia , litros/dia, Ton/dia}
    // ajustar recursos
    static double litrosOxigeno = 0;
    static double toneladasCombustible = 0;
    static boolean makeAdjust = false;
    // ajustes seleccionddaos
    static int shipSelected = 0;
    static int planetSelected = 0;

    public static void main(String[] args) throws Exception {
        Menu();
    }

    public static void Menu() {
        int opc;
        do {
            System.out.println("\n---Menú Principal---");
            System.out.println("1. Seleccion planeta de destino");
            System.out.println("2. Seleccionar una nave espacial");
            System.out.println("3. Iniciar la simulación del vuelo");
            System.out.println("4. Exit");
            System.out.println("Por favor, elige una opción: ");
            if (scanner.hasNextInt()) {
                opc = scanner.nextInt();
                switch (opc) {
                    case 1:
                        selectPlanet();
                        break;
                    case 2:
                        selectSpaceship();
                        break;
                    case 3:
                        // calcularRecursos();
                        break;
                    case 4:
                        System.out.println("Has salido del programa");
                        break;
                    default:
                        System.out.println("opción no valida");
                        break;
                }
            } else {
                System.out.println("Por favor, ingresa un número");
                scanner.next();
                opc = 6;
            }
        } while (opc != 4);
    }

    public static void selectPlanet() {
        System.out.println("\n---Seleccionar un planeta de destino---");
        System.out.println("1. Marte");
        System.out.println("2. Júpiter");
        System.out.println("3. Saturno");
        System.out.println("4. Revisar y ajustar recursos");
        System.out.println("4. Revisar y ajustar recursos");
        System.out.println("5. exit");
        System.out.println("Elige una opción: ");
        var planet = scanner.nextInt();
        String confirm;
        switch (planet) {
            case 1:
                System.out.println("estas seguro de selecionar " + planets[planet - 1] + " (si/no)");
                confirm = scanner.next();

                if (confirm.equals("si")) {
                    showDataPlanets(planet);

                } else {
                    System.out.println("ingresaste mal la opción o escogiste NO");
                }
                break;

            case 2:
                System.out.println("estas seguro de selecionar Jupiter? (si/no)");
                confirm = scanner.next();
                if (confirm.equals("si")) {
                    showDataPlanets(planet);
                } else {
                    System.out.println("ingresaste mal la opción o escogiste NO");
                }
                break;

            case 3:
                System.out.println("estas seguro de selecionar Saturno? (si/no)");
                confirm = scanner.next();

                if (confirm.equals("si")) {
                    showDataPlanets(planet);
                } else {
                    System.out.println("ingresaste mal la opción o escogiste NO");
                }
                break;

            case 4:
                // adjustResource();
                break;

            case 5:
                System.out.println("Has salido del programa");
                break;

            default:
                System.out.println("opcion no válida");
                break;
        }
    }

    private static void showDataPlanets(int planet) {
        double estimateTime = calculateTime(distances[planet - 1]);
        System.out.println("Has seleccionado el planeta " + planets[planet - 1]);
        System.out.println(descriptionPlanets[planet - 1]);
        System.out.println("La distancia a Marte es: " + distances[planet - 1] + " km");
        System.out.printf("El tiempo de viaje a una velocidad de %.0f km/h tarda %.2f días%n", velocity, estimateTime);
        System.out.println("Se necesitan los siguientes recursos para el viaje: ");
        System.out.printf("Oxígeno: %.2f litros%n", calculateResources(estimateTime, averages[0]));
        System.out.printf("Comida: %.2f kg%n", calculateResources(estimateTime, averages[1]));
        System.out.printf("Agua: %.2f litros%n", calculateResources(estimateTime, averages[2]));
        System.out.printf("combustible: %.2f Ton%n", calculateResources(estimateTime, averages[3]));
        planetSelected = planet;
    }

    private static double calculateResources(double dias, double estimateAverage) {
        return dias * estimateAverage;
    }

    private static double calculateTime(double distance) {
        double timeInHours = distance / velocity;
        double timeInDays = timeInHours / 24;
        return timeInDays;
    }
    private static double estimateTimePerShip(double velocitysShip){
        double timeInHours = distances[planetSelected - 1] / velocitysShip;
        double timeInDays = timeInHours / 24;
        return timeInDays;
    };

    public static void selectSpaceship() {
        if(planetSelected == 0){
            System.out.println("Primero debes seleccionar un planeta de destino");
            return;
        }
        System.out.println("\n--- Naves Disponibles ---");
        System.out.println("1. Falcon 9");
        System.out.println("2. Starship");
        System.out.println("3. Soyus");
        System.out.println("4. Exit");
        System.out.println("Elige una opción");
        var spaceShip = scanner.nextInt();
        switch (spaceShip) {
            case 1:
                int pasajeros;
                System.out.println("Has seleccionado la nave Falcon 9 (27.000 km/h y 8 tripulantes)");
                do {
                    System.out.println("Ingresa el número de pasajeros: ");
                    pasajeros = scanner.nextInt();
                    
                    if (pasajeros < 0) {
                        System.out.println("No puedes ingresar un número negativo. Por favor, ingresa un número positivo.");
                    }
                } while (pasajeros < 0);
                if (pasajeros <= capacidadPasajeros[0]) {
                    System.out.println("Has seleccionado " + pasajeros + " pasajeros");
                    shipSelected = spaceShip;
                } else {
                    System.out.println("La nave Falcon 9 no puede llevar todos los pasajeros");
                }
                System.out.printf("El tiempo de viaje estimado segun la velocidad de la nave es:  %.2f días%n" , estimateTimePerShip(velocitysShip[spaceShip -1]));
                break;

            case 2:
                System.out.println("Has seleccionado la nave Falcon 9 (27.000 km/h y 8 tripulantes)");
                break;

            case 3:
                System.out.println("Has seleccionado la nave Starship (40.000 km/h y cinco tripulantes)");
                break;

            case 4:
                System.out.println("Has salido del programa");
                break;
      
            default:
                System.out.println("opcion no válida a");
                break;
        }
    }
}

