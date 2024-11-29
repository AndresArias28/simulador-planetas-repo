import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static double[] distances = { 54_600_000, 590_000_000, 1_200_000_000 }; // km
    static double estimateTimeF;
    static double kgComida;
    static double ltAgua;
    static double lltCombustible;
    // descripcion de polanetass
    static String[] descriptionPlanets = {
            "Marte es el llamado planeta rojo en la mitología romana, seleccionado por su color rojizo, este planeta es el cuarto en el sistema solar y el segundo más pequeño después de Mercurio",
            "Júpiter es el planeta mas grande del sistema solar y el segundo mas grande despues de Marte",
            "Saturno es el planeta mas grande del sistema solar y el tercer mas grande despues de Júpiter"
    };
    // datos de naves
    static String[] ships = { "Falcon 9", "Starship", "Soyus" };
    static double[] velocitysShip = { 27_000, 40_000, 29_000 }; // en km/h
    static int[] capacidadPasajeros = { 28, 40, 30 };
    // Datos de planetas
    static String[] planets = { "Marte", "Jupiter", "Saturno" };
    static String[] randomEvents = {
            "Fallo en el sistema de propulción",
            "Fallo en el sistema de combustible",
            "Lluvia de asteroides",
            "Falla en el sistema de alimentación",
            "Perdidda de potencia del motor 2",
            "Objeto no identificado en la trayectoria"
    };
    static double velocity = 100_000; // km/h
    static double[] averages = { 550, 1.7, 2.5, 350 };// {litros/dia, kg/dia , litros/dia, Ton/dia} - oxigeno, comida,
                                                      // agua, combustible
    static String[] resources = { "oxigeno", "comida", "agua", "combustible" };

    // ajustar recursos
    static double litrosOxigeno = 0;
    static double toneladasCombustible = 0;
    static boolean makeAdjust = false;
    // ajustes seleccionddaos
    static int shipSelected = 0;
    static int planetSelected = 0;
    // Códigos ANSI para letras del terminal
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String ORANGE = "\u001B[34m";
    static final String BLUE = "\u001B[36m";
    static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) throws Exception {
        Menu();
        scanner.close();
    }

    public static void Menu() {
        int opc;
        do {
            System.out.println(GREEN + "\n---Menú Principal---" + RESET);
            System.out.println("1. Seleccionar un planeta como destino");
            System.out.println("2. Seleccionar una nave espacial");
            System.out.println("3. Iniciar la simulación del vuelo");
            System.out.println("4. Salir");
            System.out.println("Por favor, elige una opción: ");
            if (scanner.hasNextInt()) {// verifica que el valor ingresado es un número
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
                        System.out.println("ingresa una opción válida");
                        break;
                }
            } else {
                System.out.println("\n por favor, ingresa un número");
                scanner.next();
                opc = 6;
            }
        } while (opc != 4);
    }

    public static void selectPlanet() {
        // menu para seleccionar un planeta
        int planet;
        do {
            System.out.println(BLUE + "\n---Seleccionar un planeta de destino---" + RESET);
            System.out.println("1. Marte");
            System.out.println("2. Júpiter");
            System.out.println("3. Saturno");
            System.out.println("4. Regresar al menú principal");
            System.out.print("Elige una opción: ");
            if (scanner.hasNextInt()) {

            }
            planet = scanner.nextInt();
            switch (planet) {
                case 1:
                    // confirmar la seleccion del planeta
                    validarSeleccion(planet);
                    break;

                case 2:
                    validarSeleccion(planet);
                    break;

                case 3:
                    validarSeleccion(planet);
                    break;

                case 4:
                    System.out.println("Has salido del programa");
                    break;

                default:
                    System.out.println("ingresa opción válida");
                    break;
            }
            if (planet != 4) {
                pressEnter(scanner);
            }
        } while (planet != 4);
    }

    public static void pressEnter(Scanner scanner) {
        System.out.print("Presiona Enter para continuar...");
        scanner.nextLine();
    }

    private static void validarSeleccion(int planet) {
        String confirm;
        System.out.println("estas seguro de selecionar " + planets[planet - 1] + " (si/no)");
        confirm = scanner.next();
        // limpiar buffer
        scanner.nextLine();

        if (confirm.equals("si")) {
            showDataPlanets(planet);

        } else {
            System.out.println("ingresaste mal la opción o escogiste NO");
        }
    }

    private static void adjustResources() {
        var recursoOxy = calculateResources(estimateTimePerShip(velocitysShip[shipSelected - 1]), averages[0]);
        // menu para ajustar los recursos
        System.out.println(YELLOW + " ---Ajustar recursos---" + RESET);
        System.out.printf("Los recursos actuales de oxígeno según la nave seleccionada: %.2f litros%n", recursoOxy);
        System.out.println("desea ajustar recursos? elige una opción: ");
        System.out.println("1. Ajustar oxigeno");
        System.out.println("2. Ajustar comida");
        System.out.println("3. Ajustar agua");
        System.out.println("4. Ajustar combustible");
        System.out.println("5. Regresar al menú principal");
        System.out.println("Elige una opcion: ");
        var adjust = scanner.nextInt();

        switch (adjust) {
            case 1:
                System.out.println("Cuantos litros de " + resources[adjust - 1] + " deseas agregar?");
                litrosOxigeno = scanner.nextDouble();
                System.out.println("Has agregado " + litrosOxigeno + " litros de " + resources[adjust - 1]);
                break;

            case 2:
                System.out.println("Cuantos kg de " + resources[adjust - 1] + " deseas agregar?");
                kgComida = scanner.nextDouble();
                System.out.println("Has agregado " + kgComida + " Kg de " + resources[adjust - 1]);
                break;

            case 3:
                System.out.println("Cuantos lts de " + resources[adjust - 1] + " deseas agregar?");
                ltAgua = scanner.nextDouble();
                System.out.println("Has agregado " + ltAgua + " Kg de " + resources[adjust - 1]);
                break;

            case 4:
                System.out.println("Cuantos lts de " + resources[adjust - 1] + " deseas agregar?");
                lltCombustible = scanner.nextDouble();
                System.out.println("Has agregado " + lltCombustible + " lt de " + resources[adjust - 1]);
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
        // mostrar los datos del planeta seleccionado
        double estimateTime = calculateTime(distances[planet - 1]);
        System.out.println("Has seleccionado  " + planets[planet - 1]);
        System.out.println(descriptionPlanets[planet - 1]);
        System.out.printf("La distancia a %s es: %,.0f km%n", planets[planet - 1], distances[planet - 1]);
        System.out.printf("El tiempo de viaje a una velocidad de %.0f km/h tardaría %.2f días%n", velocity,
                estimateTime);
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
        // verificar que se ha seleccionado un planeta y nave espacial
        if (planetSelected == 0) {
            System.out.println("Primero debes seleccionar un planeta de destino");
            return;
        }
        var spaceShip = 0;
        do {
            // menu para seleccionar una nave espacial
            System.out.println(ORANGE + "\n--- Naves Disponibles ---" + RESET);
            System.out.println("1. Falcon 9");
            System.out.println("2. Starship");
            System.out.println("3. Soyus");
            System.out.println("4. Regresas al menu anterior");
            System.out.println("Elige una opción");

            // valida si la entrada es un nuemro
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Consumir la entrada inválida
                continue;
            }
            spaceShip = scanner.nextInt();
            switch (spaceShip) {
                case 1:
                    showInformationShip(spaceShip);
                    break;

                case 2:
                    showInformationShip(spaceShip);
                    break;

                case 3:
                    showInformationShip(spaceShip);
                    break;

                case 4:
                    System.out.println("Has salido del programa");
                    break;

                default:
                    System.out.println("opcion no válida a");
                    break;
            }
        } while (spaceShip != 4);
    }

    private static void showInformationShip(int spaceShip) {
        int pasajeros;
        var option = 0;
        var timeEstimate = estimateTimePerShip(velocitysShip[spaceShip - 1]);

        System.out.println("Has seleccionado la nave " + ships[spaceShip - 1] + " con velocidad de: "
                + velocitysShip[spaceShip - 1] + " km/h y " + capacidadPasajeros[spaceShip - 1] + " tripulantes");

        // validar datos correctos
        do {
            System.out.println("Ingresa el número de pasajeros (mayor a 0 y menor o igual a "
                    + capacidadPasajeros[spaceShip - 1] + "): ");
            pasajeros = scanner.nextInt();

            if (pasajeros <= 0) {
                System.out.println("El número de pasajeros debe ser mayor a 0.");

            } else if (pasajeros > capacidadPasajeros[spaceShip - 1]) {
                System.out.println("La nave " + ships[spaceShip - 1]
                        + " no puede llevar tantos pasajeros. Selecciona un número menor o igual a "
                        + capacidadPasajeros[spaceShip - 1]);
            }
        } while (pasajeros <= 0 || pasajeros > capacidadPasajeros[spaceShip - 1]);

        System.out.println("Has seleccionado " + pasajeros + " pasajeros.");
        System.out.printf("El tiempo de viaje estimado segun la velocidad de la nave es: %.2f días%n", timeEstimate);
        System.out.println("Se necesitan los siguientes recursos para el viaje: ");
        displayResources(timeEstimate);
        shipSelected = spaceShip;
        do {
            System.out.println("desea ajustar los recursos antes de iniciar el viaje? ");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.println("3. Salir");
            System.out.println("Elige una opción: ");
            // Validar si la entrada es un numero
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Consumir la entrada inválida
                continue;
            }
            option = scanner.nextInt();

            // validar el tipo de datos
            if (option < 1 || option > 3) {
                System.out.println("ingresa una opción válida");
                continue;
            }

            if (option == 1) {
                adjustResources();
                option = 3;
            } else if (option == 2) {
                break;
            } else {
                System.out.println("Has salido del programa");
            }

        } while (option != 3);

      
    }

    private static void displayResources(double timeEstimate) {
        for (int i = 0; i < resources.length; i++) {
            System.out.printf("%s: %.2f %s%n", resources[i], calculateResources(timeEstimate, averages[i]),
                    i == 3 ? "Ton" : i == 1 ? "kg" : "litros");
        }

    }

    private static void startSimulation() {
        double realResourceFood;
        double realResourceWatter;
        double realResourceGas;
        double realResourceOxygen;
        // barra de progreso
        int total = 100;
        int incremento = 2;
        // evento aleatorio
        int randomEvent = (int) (Math.random() * 50) * 2;// para q los eventos se disparen un un progereso par
        int random = (int) (Math.random() * randomEvents.length);
        String selectedEvent = randomEvents[random];
        
        if (planetSelected == 0 && shipSelected == 0) {// valida que se ha seleccionado un planeta o nave espacial
            System.out.println("Primero debes seleccionar un planeta de destino y una nave espacial");
            return;
        }
        // mostrar mensajes del viaje
        System.out.println("Inicio del viaje");
        delay(1);
        System.out.println("Prepárate para el despegue");
        delay(1);
        System.out.println("Despegando en 3, 2, 1...");
        delay(1);
        System.out.println("estas viajando en la nave: " + ships[shipSelected - 1] + " con destino a "
                + planets[planetSelected - 1]);

        System.out.println("velocidad de la nave: " + velocitysShip[shipSelected - 1] + " km/h");

        System.out.printf("Tiempo estimado de viaje: %.2f días%n",
                estimateTimePerShip(velocitysShip[shipSelected - 1]));

        delay(3);
        estimateTimeF = estimateTimePerShip(velocitysShip[shipSelected - 1]);
        realResourceOxygen = calculateResources(estimateTimeF, averages[0]) + litrosOxigeno;
        realResourceFood = calculateResources(estimateTimeF, averages[1]) + kgComida;
        realResourceWatter = calculateResources(estimateTimeF, averages[2]) + ltAgua;
        realResourceGas = calculateResources(estimateTimeF, averages[3]) + lltCombustible;


        // recorre el progreso iniciando la simulacion
        for (int progreso = 0; progreso <= total; progreso += incremento) {

            int porcentaje = (progreso * 100) / total;
            var option = 0;

            // condicion para lanzar el vento aleatorio
            if (randomEvent == progreso) {

                System.out.println("\nEvento aleatorio no esperado: " + selectedEvent);
                System.out.println("Progreso afectado...");
                delay(1);
                System.out.println("que opcines desea tomar? ");
                System.out.println("1. Reparar Nave");
                System.out.println("2. Desviar el rumbo al planeta mas cercano");
                System.out.println("3. Salir");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Reparando la nave...");
                        delay(1);
                        System.out.println("Reparación completada. Continua el viaje...");
                        delay(1);
                        System.out.println("debido al evento aleatorio, el tiempo de llegada se ha extendido");
                        delay(1);
                        if (progreso > 10) {
                            progreso = progreso - 10;// afectacion del evento aleatorio
                            randomEvent = 0;// para que no se presenten mas evcentos
                            porcentaje = (progreso * 100) / total;// vuelve a calcular el porcentaje
                        } else {
                            progreso = progreso - 2;// afectacion del evento aleatorio
                            randomEvent = 0;// para que no se presenten mas evcentos
                            porcentaje = (progreso * 100) / total;// vuelve a calcular el porcentaje
                        }

                        break;

                    case 2:
                        System.out.println("Desviando el rumbo al planeta mas cercano...");
                        delay(1);
                        progreso = 90;
                        porcentaje = (progreso * 100) / total;
                        randomEvent = 0;
                        break;

                    case 3:
                        System.out.println("Has salido del programa");
                        break;

                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            }

            // barra de progreso
            String barra = "[" + "=".repeat(progreso / incremento) + " ".repeat((total - progreso) / incremento) + "]";
            System.out.printf("\nTiempo estimado de llegada: %.2f días\n", estimateTimeF * (total - progreso) / 100);
            System.out.printf("el oxigeno actual: %.2f \n", realResourceOxygen * (total - progreso) / 100);
            System.out.printf("la comida actual: %.2f \n", realResourceFood * (total - progreso) / 100);
            System.out.printf("la agua actual: %.2f \n", realResourceWatter * (total - progreso) / 100);
            System.out.printf("el combustible actual: %.2f \n", realResourceGas * (total - progreso) / 100);


            // Mostrar la barra y el porcentaje
            System.out.print("\r" + barra + " " + porcentaje + "%");
            // mensajes de progreso
            if (progreso == 100 / 2) {
                System.out.println(" --> has alcanzado la mitad del camino!");
            }
            if (progreso == 100) {
                // System.out.println(" --> has llegado al destino");
                // finalizar el programa
                System.out.println("\nHas llegado a " + planets[planetSelected - 1]);
                System.out.println("Fin del viaje");
                break;
            }
            switch (progreso) {
                case 25:
                    System.out.println("\nEtapa 1 completada. Buen trabajo");
                    break;

                case 76:
                    System.out.println("\nEtapa 2 completada. ¡Casi llegas!");
                    break;
            }
            // tiempo de espera del progreso en general
            delay(1);
        }
    }

    private static void delay(int seconds) {
        // retraso de 1 segundo
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación: " + e.getMessage());
        }
    }

}
