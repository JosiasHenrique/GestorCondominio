package com.josias.gestorcondominio;

import com.josias.gestorcondominio.model.MesDevido;
import com.josias.gestorcondominio.model.Morador;
import com.josias.gestorcondominio.model.Proprietario;
import com.josias.gestorcondominio.model.Residencia;

public class GestorCondominio {

    public static void main(String[] args) {
       
        
        Proprietario rubens = new Proprietario(1, 65, "Rubens", "465564", "5454854858");
       
        Residencia residencia1 = new Residencia(1, 520, "Rua das alamedas", "Pontal-SP", rubens);
        
        rubens.addResidencia(residencia1);
        
         Morador vick = new Morador(residencia1, 1, 25, "Vitoria", "5457855", "4578989655");
         Morador aparecida = new Morador(residencia1, 2, 62, "Cida", "8787855", "4521989655");
         
         residencia1.addMorador(vick);
         residencia1.addMorador(aparecida);
         
         residencia1.addMes(new MesDevido(1, 5, 2025, 1000, residencia1));
         
         System.out.println("Residencias \n");
         
         System.out.println("Rua:" + residencia1.getRua());
         System.out.println("Proprietario:" + residencia1.getProprietario().getNome());
         
         System.out.println("Moradores:");
         
         for(Morador x: residencia1.getMoradores()) {
             System.out.println("nome: " + x.getNome() + "\n");
         }
         
          for(MesDevido x: residencia1.getMesesDevidos()) {
             System.out.println("Pagamento atradado: Mes: " + x.getMes() + 
                     " no valor: R$"+ x.getValor()+ "\n");
         }
        
    }
}
