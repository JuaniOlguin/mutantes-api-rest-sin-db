package com.example.mutantes.matriz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HintAnalizer {
    public boolean analizarHints(char[] hint, char caracter, int i, int j){

        //analiza si el hint que recibe es un falso positivo o un gen mutante
        String str = String.valueOf(hint);

//        System.out.println("El string es " +str);
//        System.out.println("Posicion ["+i+"]["+j+"]");

        //verifica que el caracter que ingresa se repita cuatro veces seguidas
        boolean hintMatch = (str.contains(""+caracter+caracter+caracter+caracter));

        return hintMatch;
    }
}
