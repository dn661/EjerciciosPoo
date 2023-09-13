package com.campusdual.ejercicio4;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Diet {
    private HashMap<String,Integer[]> alimentos=new HashMap<>();
    private Integer maxcal;
    private Boolean women;
    private Integer age;
    private Integer maxCarbs;
    private Integer maxProtein;
    private Integer maxFats;
    private Integer height;
    private Integer weight;

    public Diet(){

    }
    public Diet(int maxcal){
        this.setMaxcal(maxcal);
    }
    public Diet(Integer maxCarbs, Integer maxProtein, Integer maxFats){
        this.setMaxProtein(maxProtein);
        this.setMaxCarbs(maxCarbs);
        this.setMaxFats(maxFats);
    }

    public Diet(Boolean women, Integer age, Integer height, Integer weight){
        this.setWomen(women);
        this.setAge(age);
        this.setHeight(height);
        this.setWeight(weight);
    }

    public void add_alimentos(){
        Scanner myObj = new Scanner(System.in);
        String nom;
        int carb,prot,gras;

        boolean continua=false;
        do {
            out.println("Añadir alimento: \n");
            out.println("\t\t-Nombre\n");
            nom = myObj.next();
            out.println("\t\t-Carbos\n");
            carb=Integer.parseInt(myObj.next());
            out.println("\t\t-Protes\n");
            prot=Integer.parseInt(myObj.next());
            out.println("\t\t-Grasas\n");
            gras=Integer.parseInt(myObj.next());
            if (check_dieta(nom,carb,prot,gras)) {
                setAlimentos(nom, carb, prot, gras);
                muestra_alimentos();
            }
            out.println("Añadir otro alimento? s/n");
            if (myObj.next().equals("s")){
                continua=true;
            }else {
                continua=false;
            }
        }while (continua);
    }

    public void get_dietinfo(Diet diet){
        if(maxcal!=null){
            out.println("\ncalorias maximas: "+maxcal);
        }
        out.println("macros maximas totales: "+ this.getMaxCarbs()+"carbs, "+ this.getMaxProtein()+"prote,"+this.getMaxFats()+"grasa");
        out.println("calorias totales: "+ this.getTotalCals());
        out.println("macros totales actuales: "+ this.getTotalCarbs()+"carbs, "+ this.getTotalProt()+"prote,"+this.getTotalGras()+"grasa\n");
    }

    public boolean check_dieta(String nom, int c, int p, int g){
        int newfoodcals = getFoodCals(c,p,g);
        if(maxcal != null){

            if(newfoodcals > maxcal || (getTotalCals()+newfoodcals)>maxcal){
                out.println("te has pasado de calorias con: " + nom);
                return false;
            }
        }
        if (maxCarbs!=null && (maxCarbs<c || maxCarbs<getTotalCarbs())){
            out.println("te has pasado de carbohidratos con: " + nom);
            return false;
        }
        if (maxProtein!=null && (maxProtein<p || maxProtein<getTotalProt())){
            out.println("te has pasado de proteinas con: " + nom);
            return false;
        }
        if (maxFats!=null && (maxFats<g || maxFats<getTotalGras())){
            out.println("te has pasado de grasas con: " + nom);
            return false;
        }
        else {
            return true;
        }

    }
    public int getFoodCals(int c, int p, int g){
        return (c*4)+(p*4)+(g*9);
    }
    public int getTotalCals(){
        int tcals=0;
            tcals=tcals+(getTotalCarbs()*4)+(getTotalProt()*4)+(getTotalGras()*9);
        return tcals;
    }

    public int getTotalCarbs(){
        int tcarbs=0;
        for (var alim:alimentos.entrySet()){
            tcarbs=tcarbs+alim.getValue()[0];
        }
        return tcarbs;
    }
    public int getTotalProt(){
        int tprot=0;
        for (var alim:alimentos.entrySet()){
            tprot=tprot+alim.getValue()[1];
        }
        return tprot;
    }
    public int getTotalGras(){
        int tgras=0;
        for (var alim:alimentos.entrySet()){
            tgras=tgras+alim.getValue()[2];
        }
        return tgras;
    }


    public void muestra_alimentos(){
        out.println("Alimentos actuales:");
        HashMap<String,Integer[]> ali_act = getAlimentos();
        ali_act.forEach(
                (key, value)
                        -> System.out.println(key + ", valores(carbs,prot,gras):" + value[0]+","+value[1]+","+value[2]));
    }
    public HashMap<String, Integer[]> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(String name, Integer carbs, Integer protes, Integer gras) {
        this.alimentos.put(name, new Integer[]{carbs,protes,gras});
    }

    public int getMaxcal() {
        return maxcal;
    }

    public void setMaxcal(int cal) {
        this.maxcal = cal;
    }

    public Boolean getWomen() {
        return women;
    }

    public void setWomen(Boolean women) {
        this.women = women;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(Integer maxProtein) {
        this.maxProtein = maxProtein;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
