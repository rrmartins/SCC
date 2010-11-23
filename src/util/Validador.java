/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.swing.JOptionPane;


/**
 *
 * @author Tiago Vailant
 */
public class Validador {
    
    public Validador()
    {
        
    }
    
    public String tiraPontosCPF(String cpf)
    {
        String valor = cpf.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll(" ", "");
                //cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11) + cpf.substring(12, 14);
        
        return valor;
    }

    public String tiraPontosTelefone(String tel)
    {
         String valor = tel.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");
                //tel.substring(1, 3) + tel.substring(4, 8) + tel.substring(9, 13);

        return valor;

    }

    public boolean validarCPF(String cpf)
    {
        int digV1 = 0, digV2 = 0;
        int digitoVerificador1 = 0, digitoVerificador2 = 0;
        int resto = 0;

        int[] numCpf = new int[11];
        
        for(int i = 0; i < cpf.length(); i++)
        {
            numCpf[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        for(int i = 0; i < 9; i++)
        {
            digV1 = digV1 + ((10 - i) * numCpf[i]);
        }

        resto = digV1 % 11;


        if(resto < 2)
        {
            digitoVerificador1 = 0;
        }
        else
        {
            digitoVerificador1 = 11 - resto;
        }


        if(digitoVerificador1 != numCpf[9])
        {
            return false;
        }
        else
        {
            for(int i = 0; i < 10; i++)
            {
                digV2 = digV2 + ((11 - i) * numCpf[i]);
            }

            resto = digV2 % 11;


            if(resto < 2)
            {
                digitoVerificador2 = 0;
            }
            else
            {
                digitoVerificador2 = 11 - resto;
            }


            if(digitoVerificador2 != numCpf[10])
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    

    public boolean validarCNPJ(String cnpj)
    {
        int digV1 = 0, digV2 = 0;
        int digitoVerificador1 = 0, digitoVerificador2 = 0;
        int resto = 0;

        int[] numCnpj = new int[14];

        for(int i = 0; i < cnpj.length(); i++)
        {
            numCnpj[i] = Integer.parseInt(cnpj.substring(i, i +1));
        }

        for(int i = 0; i < 4; i++)
        {
            digV1 = digV1 + ((5 - i) * numCnpj[i]);

            if(i == 3)
            {
                for(i = 4; i < 12; i++)
                {
                    digV1 = digV1 + ((13 - i) * numCnpj[i]);
                }
            }
        }

        resto = digV1 % 11;


        if(resto < 2)
        {
            digitoVerificador1 = 0;
        }
        else
        {
            digitoVerificador1 = 11 - resto;
        }


        if(digitoVerificador1 != numCnpj[12])
        {
            JOptionPane.showMessageDialog(null, "CNPJ inválido");
            return false;
        }
        else
        {
            for(int i = 0; i < 4; i++)
            {
                digV2 = digV2 + ((6 - i) * numCnpj[i]);

                if(i == 3)
                {
                    for(i = 4; i < 13; i++)
                    {
                        if(i == 4)
                        {
                            digV2 = digV2 + ((6 - i) * numCnpj[i]);
                        }
                        else
                        {
                            digV2 = digV2 + ((14 - i) * numCnpj[i]);
                        }
                    }
                }
            }

            resto = digV2 % 11;

            if(resto < 2)
            {
                digitoVerificador2 = 0;
            }
            else
            {
                digitoVerificador2 = 11 - resto;
            }


            if(digitoVerificador2 != numCnpj[13])
            {
                JOptionPane.showMessageDialog(null, "CNPJ inválido");
                return false;
            }
            else
            {
                return true;
            }
        }
    }

}
