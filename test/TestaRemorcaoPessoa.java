
import dao.PessoaDAO;
import model.HibernateUtil;
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
public class TestaRemorcaoPessoa {
    
    public static void main(String[] args) {
        

        
        
        Session sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();
            
        Object persistenceInstance = sessao.load(Pessoa.class, 2);
        if(persistenceInstance != null){
            sessao.delete(persistenceInstance);
        }             
        sessao.getTransaction().commit();
        sessao.close();

        
        
    }
    
}
