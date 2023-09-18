package com.campusdual.ejercicio5;
/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos.
* El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */

import java.util.*;
import java.util.regex.Pattern;

import static java.lang.System.out;


public class Menu {
    public static List<String> macros = Arrays.asList("carbos", "protes", "grasas");
    public static List<String> datos = Arrays.asList("Introduce genero(mj/h)", "Introduce edad", "Introduce altura", "Introduce  peso");
    public static Pattern patrn = Pattern.compile("\\d+");
    public static void main(String[] args){
        HashMap<String, Diet> dietas =new HashMap<>();
        Diet dieta=new Diet();
        Scanner myObj = new Scanner(System.in);
        String cs;
    do{
        out.println("-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial\n" +
                "-2. Mostrar información: muestra calorías y macronutrientes de la dieta y permite editarla\n" +
                "-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento " +
                "a la lista de alimentos disponible\n" +
                "\t\t-a. Nuevo alimento\n" +
                "\t\t-b. Alimento existente\n" +
                "-4. Salir");
        cs = myObj.nextLine();
        String res="";
        String nxtline="";
            switch (cs) {
                case "1":
                    out.println("Introduce el nombre de la dieta a añadir:");
                    nxtline =myObj.nextLine();
                    dietas.put(nxtline,get_diet_input());
                    break;
                case "2":
                    manage_diets(dietas);
                    break;
                case "3":
                    show_dietas(dietas);
                    out.println("Introduce dieta a la que añadir o consultar alimentos:");
                    nxtline =myObj.nextLine();
                    if (patrn.matcher(nxtline).matches()) {
                        dieta=dietas.get(nxtline);
                    }
                    gest_alimentos(dieta);
                    break;
                case "4":
                    break;
                default:
                    out.println("respuesta erronea, el formato de la opcion es una letra unica, eg: '1'");
                    break;

        }
    }while(!cs.equalsIgnoreCase("4"));

    }


    private static void manage_diets(HashMap<String, Diet> dietas){
        Scanner myObj = new Scanner(System.in);
        String res="";
        Diet dieta= new Diet();
        do{
            out.println("\nEscoge opcion:" +
                    "\na. mostrar dietas" +
                    "\nb. editar dietas" +
                    "\ns. salir");
            res = myObj.nextLine();

            switch (res){
                case "a":
                    show_dietas(dietas);
                    break;
                case "b":
                    out.println("\nIntroduce el nombre de la dieta a editar(s para salir):");
                    res = myObj.nextLine();
                    if (res.equalsIgnoreCase("s")) {
                        break;
                    }
                    if(dietas.containsKey(res)) {
                        dieta = dietas.get(res);
                        dieta.editar_dieta();
                    }else{
                        out.println("No existe esa dieta con ese nombre");
                    }
                    break;
                case "s":
                    break;
            }
        }while(!res.equalsIgnoreCase("s"));

    }
    private static ArrayList<Integer> get_macros(){
        Scanner myObj = new Scanner(System.in);
        ArrayList<Integer> custom_macs=new ArrayList<>();
        for (String m:macros){
            out.println("Introduce " + m + " maximas:");
                while(!myObj.hasNextInt()){
                    out.println("Por favor introduce un numero valido entero:");
                    custom_macs.add(Integer.parseInt(myObj.nextLine()));
                }
            custom_macs.add(Integer.parseInt(myObj.nextLine()));
        }
        return custom_macs;

    }

    private static ArrayList<String> get_data(){
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> custom_data=new ArrayList<>();
        for (String d:datos){
            out.println(d +":");
            if(d.equals("Introduce genero(mj/h)")&&(myObj.nextLine().equals("mj"))){
                custom_data.add("true");
            }
            else {
                custom_data.add(myObj.nextLine());
            }
        }
        return custom_data;
    }

    private static Diet get_diet_input(){
        Diet dieta = null;
        Scanner myObj = new Scanner(System.in);
        int maxcal=0;
        String res;

        do{
            out.println("Crear/reiniciar dieta: \n" +
                    "\t\t-a. Sin limite\n" +
                    "\t\t-b. Por calorías\n" +
                    "\t\t-c. Por macronutrientes\n" +
                    "\t\t-d. Por datos personales\n" +
                    "\t\t-s. salir\n" +
                    "\t\t-r. reiniciar");

            res = myObj.nextLine();
            switch (res) {
                case "a":
                    dieta = new Diet();
                    res="s";
                    break;
                case "b":
                    do {
                        out.println("Introduce calorias maximas:");
                        String nxtline =myObj.nextLine();
                        if (patrn.matcher(nxtline).matches()) {
                            maxcal=Integer.parseInt(nxtline);
                        }
                    }while (maxcal==0);

                    dieta = new Diet(maxcal);
                    out.println("cals maximas "+dieta.getMaxcal());
                    res="s";
                    break;
                case "c":
                    ArrayList<Integer> result_macros = get_macros();
                    dieta = new Diet(result_macros.get(0), result_macros.get(1), result_macros.get(2));
                    res="s";
                    break;
                case "d":
                    ArrayList<String> result_data = get_data();
                    dieta = new Diet(Boolean.parseBoolean(result_data.get(0)), Integer.parseInt(result_data.get(1)),
                            Integer.parseInt(result_data.get(2)), Integer.parseInt(result_data.get(3)));
                    res="s";
                    break;
                case "r":
                    dieta=new Diet();
                    break;
                case "s":
                    dieta=new Diet();
                    break;
                default:
                    out.println("respuesta erronea, el formato de la opcion es una letra unica, eg: 'a'");
                    break;
            }

        }while(!res.equalsIgnoreCase("s"));

        return dieta;
    }

    private static void gest_alimentos(Diet dieta){
        Scanner myObj = new Scanner(System.in);
        out.println("Introduce operacion a realizar:\n"+
                "\t\t-a. Nuevo alimento\n" +
                "\t\t-b. Alimento existente\n");
        String nxtline =null;
        do{
            nxtline =myObj.nextLine();
            switch (nxtline) {
                case "a":
                    dieta.add_alimentos();
                    break;
                case "b":
                    dieta.muestra_alimentos();
                    break;
                default:
                    out.println("respuesta erronea, el formato de la opcion es una letra unica, eg: 'a'");
                    break;
            }
            out.println("hacer otra tarea? s/n");
            nxtline =myObj.nextLine();

        }while(nxtline.equalsIgnoreCase("s"));
    }

    public static void show_dietas(HashMap<String, Diet> dietas){
        out.println("\nEstas son las dietas actuales:");
            dietas.forEach((clave,valor) ->{
                out.println("Dieta: "+ clave);
                out.println("valores:");
                valor.get_dietinfo(valor);
            });
            }

}

