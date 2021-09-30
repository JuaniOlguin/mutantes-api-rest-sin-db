package com.example.mutantes.matriz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HintSearch {
    private HintAnalizer hintAnalizer = new HintAnalizer();
    private int contador = 0; //nos dice cuantas hints encontradas son una secuencia de adn correcta, verificada por HintAnalizer

    public void busqHoriz(char[][] dna){

        boolean hintHoriz = false;

        //recorrer la matriz para buscar hints de manera horizontal
        for(int i = 0; i < dna.length; i++){
            for (int j = 0; j < dna[i].length; j += 2) {
                if (contador < 2) {
                    //si el elemento en donde estamos no es cercano al ultimo de la fila (para evitar OutOfBoundsException)
                    if(j+2 < dna[i].length - 1) {
                        if (i % 2 == 0) { //si estamos en un nro de fila par
                            if (dna[i][j] == dna[i][j + 2]) { //si el elemento coincide con el elemento ubicado dos lugares despues, puede que tengamos una cadena de 4 elementos iguales
                                if (j == 0) { //si el elemento es el primero de la fila
                                    char[] analizar = {dna[i][j], dna[i][j + 1], dna[i][j + 2], dna[i][j + 3]}; //enviamos solo 4 elementos ya que no hay anterior
                                    //llamada al analizador
                                    hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                    if (hintHoriz) contador++; //si la hint enviada es un gen mutante
                                } else {
                                    if (j + 2 == dna[i].length - 1) {//si la secuencia de 4 coincidira con el final de una fila
                                        char[] analizar = {dna[i][j - 1], dna[i][j], dna[i][j + 1], dna[i][j + 2]};
                                        hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                        if (hintHoriz) contador++;
                                    } else {//si se encuentra en el medio de una fila y no es cercano al final, envia tambien el anterior
                                        char[] analizar = {dna[i][j - 1], dna[i][j], dna[i][j + 1], dna[i][j + 2], dna[i][j + 3]};
                                        hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                        if (hintHoriz) contador++;
                                    }
                                }
                            }
                        } else {
                            //nro de fila impar
                            if (dna[i][j + 1] == dna[i][j + 3]) {//debido a que examinamos los indices de forma intermitente (uno si, uno no)
                                if (j + 2 == dna[i].length -1) {//si nos ubicamos cercanos al final de la fila
                                    char[] analizar = {dna[i][j - 1], dna[i][j], dna[i][j + 1], dna[i][j + 2]};
                                    hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j+1], i, j+1);
                                    if (hintHoriz) contador++;
                                } else {
                                    if(j + 3 == dna.length - 1){//cuando quedan 4 elementos en una fila impar (evita OutOfBounds)
                                        char[] analizar = {dna[i][j], dna[i][j + 1], dna[i][j + 2], dna[i][j + 3]};
                                        hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j+1], i, j+1);
                                        if (hintHoriz) contador++;
                                    } else{//cuando estamos en el medio de una fila impar
                                        char[] analizar = {dna[i][j], dna[i][j + 1], dna[i][j + 2], dna[i][j + 3], dna[i][j + 4]};
                                        hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j+1], i, j+1);
                                        if (hintHoriz) contador++;
                                    }
                                }
                            }
                        }
                    } /*else { BORRAR
                        //cuando falta un paso mas para llegar al ultimo elemento en una fila par (caso extraño, evita OutOfBoundsException)
                        if(j + 2 == dna[i].length-1) {
                            if (dna[i][j] == dna[i][j + 2]) {
                                char[] analizar = {dna[i][j - 1], dna[i][j], dna[i][j + 1], dna[i][j + 2]};
                                hintHoriz = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintHoriz) contador++;
                            }
                        }
                    }*/
                }else{
                    break;//si el contador es 2, rompe el bucle
                }
            }
        }
    }

    public void busqVertical(char[][] dna){
        boolean hintVertical = false;

        for(int j = 0; j < dna[0].length; j++){
            for (int i = 0; i < dna.length; i += 2) {
                if (contador < 2) {
                    if(i+2 < dna.length - 1) {
                        if (j % 2 == 0) {
                            //nro de columna par
                            if (dna[i][j] == dna[i + 2][j]) {
                                if (i == 0) {
                                    //si es el primer elemento
                                    char[] analizar = {dna[i][j], dna[i + 1][j], dna[i + 2][j], dna[i + 3][j]};
                                    //llamada al analizador
                                    hintVertical = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                    if (hintVertical) contador++;
                                } else {
                                    //si esta en el medio
                                    if (i + 2 == dna.length - 1) {
                                        //si es el ultimo elemento en una matriz impar
                                        char[] analizar = {dna[i - 1][j], dna[i][j], dna[i + 1][j], dna[i + 2][j]};
                                        hintVertical = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                        if (hintVertical) contador++;
                                    } else {
                                        //si todavia no es el ultimo elemento, con una matriz impar
                                        char[] analizar = {dna[i - 1][j], dna[i][j], dna[i + 1][j], dna[i + 2][j], dna[i + 3][j]};
                                        hintVertical = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                        if (hintVertical) contador++;
                                    }
                                }
                            }
                        } else {
                            //nro de columna impar
                            if (dna[i + 1][j] == dna[i + 3][j] && i + 2 < dna.length) {
                                if (i + 2 == dna.length -1) {
                                    char[] analizar = {dna[i - 1][j], dna[i][j], dna[i + 1][j], dna[i + 2][j]};
                                    hintVertical = hintAnalizer.analizarHints(analizar, dna[i + 1][j], i+1, j);
                                    if (hintVertical) contador++;
                                } else {
                                    if(i + 3 == dna[i].length - 1){
                                        char[] analizar = {dna[i][j], dna[i + 1][j], dna[i + 2][j], dna[i + 3][j]};
                                        hintVertical = hintAnalizer.analizarHints(analizar, dna[i + 1][j], i + 1, j);
                                        if (hintVertical) contador++;
                                    } else{
                                        char[] analizar = {dna[i][j], dna[i + 1][j], dna[i + 2][j], dna[i + 3][j], dna[i + 4][j]};
                                        hintVertical = hintAnalizer.analizarHints(analizar, dna[i + 1][j], i + 1, j);
                                        if (hintVertical) contador++;
                                    }
                                }
                            }
                        }
                    } /*else { BORRAR
                        //cuando pasa un paso mas para llegar al ultimo elemento en una fila par
                        if(i + 2 == dna.length-1) {
                            if (dna[i][j] == dna[i + 2][j]) {
                                char[] analizar = {dna[i - 1][j], dna[i][j], dna[i + 1][j], dna[i + 2][j]};
                                hintVertical = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintVertical) contador++;
                            }
                        }
                    }*/
                }else{
                    break;//si el contador es 2, rompe el bucle
                }
            }
        }
    }

    public void busqDiagonal(char[][] dna){
        boolean hintDiagonal = false;

        for(int i = 0; i < dna.length; i++){
            for(int j = 0; j < dna[i].length; j++){
                if(contador < 2) {
                    if (i + 2 < dna.length && j + 2 < dna[i].length && dna[i][j] == dna[i + 2][j + 2]) {
                        if (i == j) {//recorriendo diagonal principal
                            if (i == 0) {//primer elemento de la diagonal principal
                                char[] analizar = {dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (i > 0 && i + 3 <= dna.length - 1) {//elementos intermedios de la diagonal
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (i + 3 == dna.length) {//ultimo hint de la diagonal principal
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            }
                        } else if (j + 1 == i && i + 3 < dna.length) {//diagonales inferiores, siempre que la cantidad de elementos de la diagonal sea > 3
                            if (j == 0) {//primer elemento de la diagonal inferior
                                char[] analizar = {dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (j > 0 && j + 3 <= dna[i].length - 1) {//elementos intermedios de la diagonal
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (j + 3 == dna[i].length) {//ultimo hint de la diagonal inferior
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            }
                        } else if (i + 1 == j && j + 3 < dna[i].length) {//diagonales superiores, siempre que la cantidad de elementos de la diagonal sea > 3
                            if (i == 0) {//primer elemento de la diagonal superior
                                char[] analizar = {dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (i > 0 && i + 3 <= dna.length - 1) {//elementos intermedios de la diagonal
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2], dna[i + 3][j + 3]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            } else if (i + 3 == dna.length) {//ultimo hint de la diagonal inferior
                                char[] analizar = {dna[i - 1][j - 1], dna[i][j], dna[i + 1][j + 1], dna[i + 2][j + 2]};
                                hintDiagonal = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonal) contador++;
                            }
                        }
                    }
                }else{
                    break; //si el contador es 2, rompe el bucle
                }
            }
        }
    }

    public void busqDiagonalInv(char[][] dna){
        boolean hintDiagonalInv = false;

        for(int i = 0; i < dna.length; i++){
            for(int j = dna[i].length-1; j >= 0; j--){
                if(contador < 2) {
                    if (i + 2 < dna.length && j - 2 > 0 && dna[i][j] == dna[i + 2][j - 2]) {
                        if (i + j == dna.length-1) {//recorriendo diagonal inversa principal
                            if (i == 0) {//primer elemento de la diagonal principal inversa
                                char[] analizar = {dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            } else if (j - 2 == 0 && dna.length % 2 == 1) {//ultimo hint de la diagonal principal inversa, en matrices impares
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonalInv) contador++;
                            } else if (i > 0 && j - 2 > 0) {//elementos intermedios de la diagonal
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            }
                        } else if (i + j >= dna.length && i + 3 <= dna.length-1) {//diagonales inversas inferiores, siempre que la cantidad de elementos de la diagonal sea > 3
                            if (j == dna.length-1) {//primer elemento de la diagonal inversa inferior
                                char[] analizar = {dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            } else if (i + 2 == dna.length-1) {//ultimo hint de la diagonal inferior inversa
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonalInv) contador++;
                            } else if (i + 3 <= dna.length-1) {//elementos intermedios de la diagonal inversa inferior
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            }
                        } else if (i + j <= dna.length-2 && j - 3 >= 0) {//diagonales superiores inversas, siempre que la cantidad de elementos de la diagonal sea > 3
                            if (i == 0) {//primer elemento de la diagonal superior
                                char[] analizar = {dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            } else if (j - 3 >= 0) {//elementos intermedios de la diagonal
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2], dna[i + 3][j - 3]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i + 1, j);
                                if (hintDiagonalInv) contador++;
                            } else if (j - 2 == 0) {//ultimo hint de la diagonal inferior
                                char[] analizar = {dna[i - 1][j + 1], dna[i][j], dna[i + 1][j - 1], dna[i + 2][j - 2]};
                                hintDiagonalInv = hintAnalizer.analizarHints(analizar, dna[i][j], i, j);
                                if (hintDiagonalInv) contador++;
                            }
                        }
                    }
                }else{
                    break;//si el contador es 2, rompe el bucle
                }
            }
        }
    }
}
