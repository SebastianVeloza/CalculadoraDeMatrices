package co.upb.clases;

import java.util.Scanner;

public class CalculadoraEcuaciones {

    public static void main(String[] args) {

        double[][] matriz = new double[3][4];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        Scanner tec = new Scanner(System.in);

        int opc = -1;

        while (opc != 0) {

            System.out.println("Menú principal ");
            System.out.println("");
            System.out.println("0.Salir");
            System.out.println("1.Método Gauss");
            System.out.println("2.Método Gauss-Jordan");
            System.out.println("3.Método Kramer");

            System.out.println("");
            System.out.println("Ingresa la opción deseada: ");
            try {
                opc = Integer.valueOf(tec.nextLine());

            } catch (Exception e) {
                System.out.println("Tienes que ingresar un número");
            }

            switch (opc) {
                case 0:
                    break;

                case 1:
                    gauss(matriz);
                    break;

                case 2:
                    gaussJordan(matriz);
                    break;

                case 3:
                    int tam = 0;
                    Scanner teclado = new Scanner(System.in);
                    System.out.println("Cual es el tamaño de la matriz");
                    tam = teclado.nextInt();
                    int a[][] = new int[tam][tam]; //ecuacion
                    int b[] = new int[tam]; //resultado de a
                    int solucion[] = new int[tam];
                    float cmr[] = new float[tam]; //temp
                    int t = 0;

                    System.out.println("Inserte los valores de la matriz");
                    insertarM(a); //llamada a procedimiento
                    System.out.println("Inserte los valores del resultado");
                    insertarV(b);
                    cmr = cramer(a, b);
                    System.out.println("Los valores para las X's son:");
                    mostrarX(cmr);
                    break;

                default:
                    System.out.println("No ingresaste ninguna opción.");
                    break;

            }

            System.out.println("");
        }

    }

    public static double[] gauss(double[][] matriz) {
        //Gauss
        double[] filModificadora = new double[matriz.length];
        double valModificador;
        double valAmodificar;

        for (int i = 0; i < matriz.length - 1; i++) {

            valModificador = filModificadora[i];

            for (int j = 0; j < matriz[i].length; j++) { //Se divide toda la fila para generar el 1
                matriz[i][j] /= valModificador;
            }

            for (int j = i + 1; j < matriz.length; j++) { // recorre las filas con los demas valores
                filModificadora = matriz[i]; //obtengo la fila con la que le hago operaciones a las otras
                valAmodificar = matriz[j][i];

                for (int k = 0; k < filModificadora.length; k++) { //Se multiplica la fila modificadora por el contrario del val a modificar
                    filModificadora[k] = filModificadora[k] * -1 * valAmodificar;
                }

                for (int k = 0; k < matriz[j].length; k++) { //Se resta la fila a modificar por la fila modificadora
                    matriz[j][k] += filModificadora[k];
                }

            }
        }

        double[] raices = new double[matriz[1].length];
        int j = 0;
        int ultimaPosicion = matriz[1].length - 1;
        double[] fila;
        int posArranque = matriz[1].length - 2; //penultima
        int posRaiz = 0;

        for (int i = matriz.length - 1; i >= 0; i++) { //Ciclo inverso desde la ultima fila

            fila = matriz[i];
            raices[j] = fila[ultimaPosicion];
            if (i != matriz.length - 1) {
                posRaiz = 0;
                for (int k = posArranque; k < fila.length - 1; k++) {
                    raices[j] -= fila[k] * raices[posRaiz];
                    posRaiz++;
                }
                posArranque--;
            }

            j++;
        }
        return raices;
    }

    public static double[] gaussJordan(double[][] matriz) {

        double[] filModificadora = new double[matriz.length];
        double valModificador;
        double valAmodificar;

        for (int i = 0; i < matriz.length - 1; i++) {

            valModificador = filModificadora[i];

            for (int j = 0; j < matriz[i].length; j++) { //Se divide toda la fila para generar el 1
                matriz[i][j] /= valModificador;
            }

            for (int j = 0; j < matriz.length; j++) { // recorre las filas con los demas valores
                filModificadora = matriz[i]; //obtengo la fila con la que le hago operaciones a las otras
                valAmodificar = matriz[j][i];

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < filModificadora.length; k++) { //Se multiplica la fila modificadora por el contrario del val a modificar
                    filModificadora[k] = filModificadora[k] * -1 * valAmodificar;
                }

                for (int k = 0; k < matriz[j].length; k++) { //Se resta la fila a modificar por la fila modificadora
                    matriz[j][k] += filModificadora[k];
                }

            }
        }

        double[] raices = new double[matriz[1].length];
        int j = 0;
        int ultimaPosicion = matriz[1].length - 1;
        double[] fila;

        for (int i = matriz.length - 1; i >= 0; i++) { //Ciclo inverso desde la ultima fila

            fila = matriz[i];
            raices[j] = fila[ultimaPosicion];
            j++;
        }

        return raices;
    }

    //Declaracion de procedimiento para matriz
    public static void insertarM(int a[][]) {
        Scanner teclado = new Scanner(System.in);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println("Inserte un valor de la posicion :[ " + i + " , " + j + " ]: ");
                a[i][j] = teclado.nextInt();
            }
        }
    }

    public static void mostrarM(int a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println("El resultado de la matriz en la posicion  [ " + i + " , " + j + " ] es: " + a[i][j]);
            }
        }
    }

    //Declaracion de procedimientos para vector
    public static void insertarV(int a[]) {
        Scanner teclado = new Scanner(System.in);
        for (int i = 0; i < a.length; i++) {
            System.out.println("Inserte un valor entero del vector en la posicion: [ " + i + " ]: ");
            a[i] = teclado.nextInt();
        }
    }

    public static void mostrarV(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("El resultado de la matriz en la posicion  [ " + i + " ] es: " + a[i]);
        }

    }

    public static int determinante(int a[][]) {
        int c[][] = new int[a.length + (a.length - 1)][a.length];
        int det = 0;
        //almacena los resultados parciales
        int par[] = new int[(a.length) * 2];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                c[i][j] = a[i][j];
            }
        }
        int k = 0;
        for (int i = a.length; i < c.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[k][j];
            }
            k++;
        }

        //calcula la suma de los productos y la inserta en par
        k = 0;
        int temp = 1;
        int inc = 1;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                temp = temp * c[k][j];
                k++;
            }
            k = inc;
            par[i] = temp;
            temp = 1;
            inc++;
        }

        //calcula la resta de los productos y la inserta en par
        k = a.length - 1;
        temp = 1;
        inc = a.length - 1;
        int l = (par.length) / 2;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                temp = temp * c[k][j];
                k--; //k=k-1;
            }
            par[l] = -temp;
            temp = 1;
            inc++;
            k = inc;
            l++;
        }
        det = suma(par);
        return det;
    }

    //sustituye los valores de b en a en la posicion pos
    public static int[][] sustituye(int a[][], int b[], int pos) {
        int c[][] = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (j == pos) {
                    c[i][j] = b[i];
                } else {
                    c[i][j] = a[i][j];
                }
            }
        }
        return c;
    }

    public static int suma(int a[]) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result = result + a[i];
        }

        return result;
    }

    ///funcion cramer
    public static float[] cramer(int a[][], int b[]) {
        float Rcramer[] = new float[b.length];
        int det = determinante(a);
        if (det == 0) {
            System.out.println("No tiene solucion");
            return Rcramer;
        }

        int detTemp;
        int c[][] = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            c = sustituye(a, b, i);
            detTemp = determinante(c);
            Rcramer[i] = (float) detTemp / (float) det;
        }
        return Rcramer;
    }

    //muestra los resultados de X
    public static void mostrarX(float a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("El resultado de X_" + i + " es: " + a[i]);
        }
    }

}
