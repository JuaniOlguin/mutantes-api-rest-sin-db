package com.example.mutantes.matriz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MutantDetector {
    private HintSearch search;

    public boolean isMutant(char[][] dna){
        boolean isMutant = false;

        //llama a la busqueda horizontal
        search.busqHoriz(dna);
        System.out.println("Busqueda horizontal terminada");
        //si en la busqueda horizontal no encuentra mas de una secuencia, procede a la busqueda vertical
        if(search.getContador() < 2){
            search.busqVertical(dna);
            System.out.println("Busqueda vertical terminada");
        }
        //busqueda diagonal
        if(search.getContador() < 2){
            search.busqDiagonal(dna);
            System.out.println("Busqueda diagonal terminada");
        }
        //diagonal inversa////
        if(search.getContador() < 2){
            search.busqDiagonalInv(dna);
            System.out.println("Busqueda diagonal inversa terminada");
        }

        if(search.getContador() == 2) isMutant = true;
        search.setContador(0);

        return isMutant;
    }
}
