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
    static String[] randomEvents = { "Fallo en el sistema de propulción",
            "Fallo en el sistema de combustible",
            "Lluvia de asteroides",
            "Falla en el sistema de alimentación",
            "Perdidda de potencia del motor 2",
            "Objeto no identificado en la trayectoria"
    };
    static double velocity = 100_000; // km/h
    static double[] averages = { 550, 1.7, 2.5, 350 };// {litros/dia, kg/dia , litros/dia, Ton/dia}
    // ajustar recursos
    static double litrosOxigeno = 0;
    static double toneladasCombustible = 0;
    static boolean makeAdjust = false;
    // ajustes seleccionddaos
    static int shipSelected = 0;
    static int planetSelected = 0;

    // Códigos ANSI
    static final String RESET = "\u001B[0m"; // Restablece el color
    static final String GREEN = "\u001B[32m"; // Verde
    static final String BLUE = "\u001B[34m"; // Azul

    public static void main(String[] args) throws Exception {
        Menu();
    }

    public static void Menu() {
        int opc;
        do {
            System.out.println(GREEN + "\n---Menú Principal---" + RESET);
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
                        startSimulation();
                        break;
                    case 4:
                        System.out.println("Has salido del programa");
                        break;
                    default:
                        System.out.println("opción no valida");
                        break;
                }
            } else {
                System.out.println("\n por favor, ingresa un número");
                scanner.next();
                opc = 6;
            }
        } while (opc != 4);
    }

    private static void startSimulation() {
        if (planetSelected == 0 || shipSelected == 0) {
            System.out.println("Primero debes seleccionar un planeta de destino y una nave espacial");
            return;
        }
        System.out.println("Inicio del viaje");
        delay();
        System.out.println("¡Prepárate para el despegue!");
        delay();
        System.out.println("Despegando en 3, 2, 1...");
        delay();
        System.out.println("estas viajando en la nave: " + ships[shipSelected - 1] + " con destino a "
                + planets[planetSelected - 1]);
        System.out.println("velocidad de la nave: " + velocitysShip[shipSelected - 1] + " km/h");
        System.out.println(
                "Tiempo estimado de viaje estimado: " + estimateTimePerShip(velocitysShip[shipSelected - 1]) + " días");
        int total = 100; // Valor total
        int incremento = 5; // Incremento de progreso
        delay5();
        for (int progreso = 0; progreso <= total; progreso += 5) {
            // Mensaje de progreso
            int porcentaje = (progreso * 100) / total;
            String barra = "[" + "=".repeat(progreso / incremento) + " ".repeat((total - progreso) / incremento) + "]";

            // Mostrar la barra y el porcentaje
            System.out.print("\r" + barra + " " + porcentaje + "%");

            // Etapas clave usando if
            if (progreso == 100 / 2) {
                System.out.println("¡Has alcanzado la mitad del camino!");
            }

            if (progreso == 100) {
                System.out.println("¡Has llegado al destino!");
            }

            // Opcional: Mensajes adicionales usando switch
            switch (progreso) {
                case 25:
                    System.out.println("Etapa 1: 25 km completados. ¡Buen trabajo!");
                    break;
                case 75:
                    System.out.println("Etapa 2: 75 km completados. ¡Casi llegas!");
                    System.out.println("falla en el sistema de propulsión");
                    System.out.println("Reparando el sistema de propulsión...");
                    delay();
                    System.out.println("Reparación completada. ¡Continúa el viaje!");
                    break;
            }

            // retraso
            delay();

        }
    }

    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación: " + e.getMessage());
        }
    }

    private static void delay5() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación: " + e.getMessage());
        }
    }

    public static void selectPlanet() {
        System.out.println(BLUE + "\n---Seleccionar un planeta de destino---" + RESET);
        System.out.println("1. Marte");
        System.out.println("2. Júpiter");
        System.out.println("3. Saturno");
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
                adjustResource();
                break;

            case 5:
                System.out.println("Has salido del programa");
                break;

            default:
                System.out.println("opcion no válida");
                break;
        }
    }

    private static void adjustResource() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adjustResource'");
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

    private static double estimateTimePerShip(double velocitysShip) {
        double timeInHours = distances[planetSelected - 1] / velocitysShip;
        double timeInDays = timeInHours / 24;
        return timeInDays;
    };

    public static void selectSpaceship() {
        if (planetSelected == 0) {
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
                selectionShipgetPasajeros(spaceShip);
                break;

            case 2:
                selectionShipgetPasajeros(spaceShip);
                break;

            case 3:
                selectionShipgetPasajeros(spaceShip);
                break;

            case 4:
                System.out.println("Has salido del programa");
                break;

            default:
                System.out.println("opcion no válida a");
                break;
        }
    }

    private static void selectionShipgetPasajeros(int spaceShip) {
        int pasajeros;
        System.out.println("Has seleccionado la nave " + ships[spaceShip - 1] + " con velocidad de: "
                + velocitysShip[spaceShip - 1] + "km/h y " + capacidadPasajeros[spaceShip - 1] + " tripulantes");
        do {
            System.out.println("Ingresa el número de pasajeros: ");
            pasajeros = scanner.nextInt();

            if (pasajeros < 0) {
                System.out.println("No puedes ingresar un número negativo. Por favor, ingresa un número positivo.");
            }
        } while (pasajeros < 0);
        if (pasajeros <= capacidadPasajeros[spaceShip - 1]) {
            System.out.println("Has seleccionado " + pasajeros + " pasajeros");
            shipSelected = spaceShip;
        } else {
            System.out.println("La nave " + ships[spaceShip - 1] + " no puede llevar todos los pasajeros");
        }
        System.out.printf("El tiempo de viaje estimado segun la velocidad de la nave es:  %.2f días%n",
                estimateTimePerShip(velocitysShip[spaceShip - 1]));
        shipSelected = spaceShip;
    }
}
