
import dao.PessoaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.HibernateUtil;
import model.JPAUtil;
import model.Pessoa;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas - Admin
 */
public class TesteListaPessoas {
    
    public static void main(String[] args) throws Exception {
       
        PessoaDAO pd = new PessoaDAO();        
        pd.listaPessoas();
        

    }
            

    
}
