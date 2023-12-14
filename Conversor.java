
/******************************************************************************
Transformar numero binario para decimal e decimal para binario.
*******************************************************************************/
import java.lang.Math;

class Conversor {
    public static void main(String args[]) {

        int opc;

        do {
            System.out.println("\nDigite a opcao desejada: ");
            System.out.println("1- Binario para decimal");
            System.out.println("2- Decimal para binario");
            System.out.println("3- Sair");

            opc = MyIO.readInt();

            if (opc == 1)
                binDeci();
            else if (opc == 2)
                deciBin();

        } while (opc != 3);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void binDeci() {

        System.out.println("\nDigite o numero em binario: ");
        String binario = MyIO.readLine(); // salvar o valor lido (tudo)

        if (!binario.contains(".")) // caso nao tenha ponto, chamar a funcao que trata numeros binarios inteiros
            binDeciInteiro(binario);

        else {

            String[] separaVirgula = binario.split("\\.", -1); // separar o valor inteiro do decimal

            int inteiro = 0; // aqui vai salvar a parte inteira
            float decimal = 0; // aqui vai salvar a parte decimal
            int tamanho = separaVirgula[0].length(); // calculando tamanho da parte inteira
            int tamanho2 = separaVirgula[1].length(); // calculando tamanho da parte decimal

            int j = 0; // usado para ser a potencia (inteiro comeca a elevar a 0)

            for (int i = tamanho - 1; i >= 0; i--, j++) // 1101
            {
                int digito = Character.getNumericValue(separaVirgula[0].charAt(i)); // transformando o digito, que esta
                                                                                    // em string, para int
                inteiro += digito * Math.pow(2, j); // calculando potencia e somando ao que ja existe
            }

            j = -1; // usado para ser potencia (decimal comeca a elevar em -1)

            for (int i = 0; i < tamanho2; i++, j--) // .011
            {
                int digito = Character.getNumericValue(separaVirgula[1].charAt(i));
                decimal += digito * Math.pow(2, j);
            }

            System.out.println("\nNumero binario: " + binario + " = Numero decimal: " + inteiro + decimal);
        }
    }
    ////////////////////////////

    public static void binDeciInteiro(String binario) {
        int decimal = 0;
        int tamanho = binario.length();
        int j = 0;

        for (int i = tamanho - 1; i >= 0; i--) // 1101
        {
            int digito = Character.getNumericValue(binario.charAt(i));
            decimal += digito * Math.pow(2, j);
            j++;
        }

        System.out.println("\nNumero binario: " + binario + " = Numero decimal: " + decimal);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void deciBin() {
        System.out.println("\nDigite o numero em decimal: ");
        String numeroString = MyIO.readLine(); //lendo valor na base 10

        if (!numeroString.contains(".")) {  //caso nao tenha ponto, chamar a funcao que trata numeros binarios inteiros
            deciBinInteiro(numeroString);

        } else {
            String[] separaVirgula = numeroString.split("\\.", -1);     //separando a parte inteira da decimal
            int parteInteira = Integer.parseInt(separaVirgula[0]);
            double parteDecimal = Double.parseDouble("0." + separaVirgula[1]);

            String binarioInteiro = "";   //aqui ficará a string ja em binario, porem invertida
            while (parteInteira / 2 > 0) {
                binarioInteiro += parteInteira % 2;
                parteInteira /= 2;
            }

            binarioInteiro += parteInteira;
            String invertida1 = inverter(binarioInteiro);

            String binarioDecimal = "";
            int i = 0;
            while (parteDecimal > 0 && i < 4) {  //fazer o loop enquanto tiver bits a serem convertidos ou limitar a 4 bits
                parteDecimal *= 2;
                int bit = (int) parteDecimal;
                binarioDecimal += bit;
                parteDecimal -= bit;
                i++;
            }

            System.out.println("\nNumero decimal: " + numeroString + " = Numero binario: " + invertida1 + "."+ binarioDecimal);
        }
    }

                                            ////////////////////////////

    public static void deciBinInteiro(String numeroString) {
        int numero = Integer.parseInt(numeroString); // 102/2
                                                     // 0 51/2
                                                     // 1 25/2
                                                     // 1 12/2
                                                     // 0 6/2
                                                     // 0 3/2
                                                     // 1 1/2
                                                     // 1
        String binario = "";

        while (numero / 2 > 0) {
            binario += numero % 2;
            numero /= 2;
        }

        binario += numero;

        String invertida = inverter(binario);

        System.out.println("\nNumero decimal: " + numero + " = Numero binario: " + invertida);
    }

    public static String inverter(String s){
        String invertida = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            invertida += s.charAt(i);
        }

        return invertida;
    }
}
